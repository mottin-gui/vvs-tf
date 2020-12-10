package com.vvs.trabalhofinal.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

import com.vvs.trabalhofinal.excecoes.ResourceNotFoundException;
import com.vvs.trabalhofinal.modelos.Carro;
import com.vvs.trabalhofinal.repositorios.CarroRepository;

/**
 * Classe que implementa a camada de Controle do sistema.
 */
@Controller
@RequestMapping("/api")
public class CarroController {

    /**
     * Injeta a classe responsável pela interface com o banco de dados mySQL
     */
    @Autowired
    CarroRepository carroRepository;

    /**
     * Retorna todos os carros cadastrados
     * @return
     */
    @GetMapping("/carros")
    public String exibeTodosCarros(Model model) {
        //return carroRepository.findAll();
        List<Carro> listaCarros = carroRepository.findAll();
        if (listaCarros != null) {
            model.addAttribute("carros", listaCarros);
        }
        return "listaCarros";
    }

    /**
     * Retorna a view para cadastro de carro.
     * @param model
     * @return
     */
    @GetMapping("/formAdicionaCarro")
    public String exibeFormAdicionaCarro(Model model) 
    {
        model.addAttribute("carro", new Carro());
        return "formAdicionaCarro";
    }    

    /**
     * Retorna a view do formulário de alteração de dados
     * com os campos referentes ao alvo já preenchido.
     * @param model
     * @param carroId
     * @return
     */
    @GetMapping("/formAlteraCarro/{id}")
    public String exibeFormAlteraCarro(Model model,@PathVariable(value = "id") Long carroId) 
    {
        if (carroId != null) {
            Carro carroAlvo = carroRepository.findById(carroId).
                orElseThrow(() -> new ResourceNotFoundException("Carro", "id", carroId));
            model.addAttribute("carro", carroAlvo);
        }

        return "formAlteraCarro";
    }   

    /**
     * Insere um carro no banco.
     * Retorna o usuário para uma tela exibindo o carro adicionado.
     * @param carro
     * @return
     */
    @PostMapping("/carro")
    public String criaCarro(Model model,@ModelAttribute Carro carro) {
        
        if (carro != null) {
            Carro carroSalvo = carroRepository.save(carro);
            model.addAttribute("carro", carroSalvo);
        }
        return "exibeCarro";
        //return carroRepository.save(carro);
    }

    /**
     * Recupera o carro pelo Id no banco
     * Retorna o usuário para a tela de dados do carro
     * @param carroId
     * @return
     */
    @GetMapping("/carro/{id}")
    public String buscaCarroPorId(Model model,@PathVariable(value = "id") Long carroId) {

        if (carroId != null) {
            Carro carroAlvo = carroRepository.findById(carroId).
                orElseThrow(() -> new ResourceNotFoundException("Carro", "id", carroId));
            model.addAttribute("carro", carroAlvo);
        }

        return "exibeCarro";
    }
    
    /**
     * Atualiza os dados do carro com um determinado id.
     * Retorna o usuário para uma tela exibindo o carro atualizado.
     * @param carroId
     * @param novosDados
     * @return
     */
    @PutMapping("/atualizaCarro/{id}")
    public String atualizaCarro(Model model,@PathVariable(value = "id") Long carroId, @ModelAttribute Carro novosDados) {

        if(carroId!=null){
            Carro carro = carroRepository.findById(carroId).orElseThrow(() -> new ResourceNotFoundException("Carro", "id", carroId));

            carro.setCor(novosDados.getCor());
            carro.setFabricante(novosDados.getFabricante());
            carro.setModelo(novosDados.getModelo());
        
            Carro carroAtualizado = carroRepository.save(carro);
            model.addAttribute("carro", carroAtualizado);

             return "exibeCarro";
        }

    return "listaCarros";
}

    /**
     * Deleta carro por ID.
     * Retorna o usuário para tela com mensagem de sucesso.
     * @param carroId
     * @return
     */
    @DeleteMapping("/carro/{id}")
    public String deletaCarroPorId(@PathVariable(value = "id") Long carroId) {
        
        if(carroId!=null){
            Carro carro = carroRepository.findById(carroId)
            .orElseThrow(() -> new ResourceNotFoundException("Carro", "id", carroId));

            carroRepository.delete(carro);

        }
        return "sucessoDelete";
    }

}
