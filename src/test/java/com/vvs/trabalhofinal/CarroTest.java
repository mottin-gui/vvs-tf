package com.vvs.trabalhofinal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.logging.Logger;

import com.vvs.trabalhofinal.modelos.Carro;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Classe que implementa os testes a serem realizados 
 * sobre a classe Carro
 */
public class CarroTest {

    /**
     * Logger para registro do testes
     */
    private static Logger logger = Logger.getLogger("CarroTests");

    /**
     * Uma instância de carro a ser testada
     */
    private static Carro carro;

    /**
     * Uma string para testar entradas acima do limite do campo
     */
    private static String stringCom256Caracteres;

     /**
     * Método que será executado uma única
     * vez ao iniciar os testes.
     */
    @BeforeAll
    public static void inicializa(){
        logger.info("Início CarroTest");
        carro = new Carro();

        for(int i=0;i<=255;i++){
            stringCom256Caracteres+="a";
        }
    }
    
     /**
     * Método que será executado antes de cada método
     * de teste para setar a variável carro em valores
     * válidos.
     */
    @BeforeEach
    public void preparaTeste(){
        logger.info("Reseta o carro mock para o próximo teste");
        carro.setCor("Vermelho");
        carro.setModelo("Fusca");
        carro.setFabricante("Volkswagen");
    }

    /**
     * Teste positivo, feito para passar.
     */
    @Test
    @DisplayName("Teste para passar")
    public void usuarioCamposValidos(){
        logger.info("Teste com dados válidos");
        assertEquals(carro.getCor(),"Vermelho");
        assertEquals(carro.getModelo(),"Fusca");
        assertEquals(carro.getFabricante(),"Volkswagen");
    }    
   
    /**
     * Testa o cenário de inserir string vazia ao definir a cor
     */
    @Test
    @DisplayName("Testa cor vazia")
    public void carroCorVazia(){
        logger.info("Testa cor vazio");
        Exception exception = assertThrows(RuntimeException.class, () -> {
            carro.setCor("");
        });
    
        String mensagemEsperada = "Deve ser informada uma cor";
        String mensagemRecebida = exception.getMessage();
    
        assertTrue(mensagemRecebida.contains(mensagemEsperada));
    }

    /**
     * Testa o cenário de inserir string nula ao definir a cor
     */
    @Test
    @DisplayName("Testa cor nula")
    public void carroCorNula(){
        logger.info("Testa cor nula");
        Exception exception = assertThrows(RuntimeException.class, () -> {
            carro.setCor(null);
        });
    
        String mensagemEsperada = "Deve ser informada uma cor";
        String mensagemRecebida = exception.getMessage();
    
        assertTrue(mensagemRecebida.contains(mensagemEsperada));
    }

    /**
     * Testa o cenário de inserir string com 256 caracteres
     * ao definir a cor
     */
    @Test
    @DisplayName("Testa cor com mais de 255 caracteres")
    public void carroCorMaisDe255Carateres(){
        logger.info("Testa cor com mais de 255 caracteres");
        Exception exception = assertThrows(RuntimeException.class, () -> {
            carro.setCor(stringCom256Caracteres);
        });
    
        String mensagemEsperada = "A cor deve ter até 255 caracteres";
        String mensagemRecebida = exception.getMessage();
    
        assertTrue(mensagemRecebida.contains(mensagemEsperada));
    }

    /**
     * Testa o cenário de inserir string vazia ao definir o modelo
     */
    @Test
    @DisplayName("Testa modelo vazio")
    public void carroModeloVazio(){
        logger.info("Testa modelo vazio");
        Exception exception = assertThrows(RuntimeException.class, () -> {
            carro.setModelo("");
        });
    
        String mensagemEsperada = "Deve ser informado o nome do modelo";
        String mensagemRecebida = exception.getMessage();
    
        assertTrue(mensagemRecebida.contains(mensagemEsperada));
    }

    /**
     * Testa o cenário de inserir string nula ao definir o modelo
     */
    @Test
    @DisplayName("Testa modelo nulo")
    public void carroModeloNulo(){
        logger.info("Testa modelo nulo");
        Exception exception = assertThrows(RuntimeException.class, () -> {
            carro.setModelo(null);
        });
    
        String mensagemEsperada = "Deve ser informado o nome do modelo";
        String mensagemRecebida = exception.getMessage();
    
        assertTrue(mensagemRecebida.contains(mensagemEsperada));
    }
    
    /**
     * Testa o cenário de inserir string com 256 caracteres
     * ao definir o modelo
     */
    @Test
    @DisplayName("Testa modelo com mais de 255 caracteres")
    public void carroModeloMaisDe255Carateres(){
        logger.info("Testa modelo com mais de 255 caracteres");
        Exception exception = assertThrows(RuntimeException.class, () -> {
            carro.setModelo(stringCom256Caracteres);
        });
    
        String mensagemEsperada = "O modelo deve ter até 255 caracteres";
        String mensagemRecebida = exception.getMessage();
    
        assertTrue(mensagemRecebida.contains(mensagemEsperada));
    }

    /**
     * Testa o cenário de inserir string vazia ao definir o frabricante
     */
    @Test
    @DisplayName("Testa fabricante vazio")
    public void carroFabricanteVazio(){
        logger.info("Testa fabricante vazio");
        Exception exception = assertThrows(RuntimeException.class, () -> {
            carro.setFabricante("");
        });
    
        String mensagemEsperada = "Deve ser informado um fabricante";
        String mensagemRecebida = exception.getMessage();
    
        assertTrue(mensagemRecebida.contains(mensagemEsperada));
    }

    /**
     * Testa o cenário de inserir string nula ao definir o fabricante
     */
    @Test
    @DisplayName("Testa fabricante nulo")
    public void carroFabricanteNulo(){
        logger.info("Testa fabricante nulo");
        Exception exception = assertThrows(RuntimeException.class, () -> {
            carro.setFabricante(null);
        });
    
        String mensagemEsperada = "Deve ser informado um fabricante";
        String mensagemRecebida = exception.getMessage();
    
        assertTrue(mensagemRecebida.contains(mensagemEsperada));
    }
    
    /**
     * Testa o cenário de inserir string com 256 caracteres
     * ao definir o fabricante
     */
    @Test
    @DisplayName("Testa fabricante com mais de 255 caracteres")
    public void carroFabricanteMaisDe255Carateres(){
        logger.info("Testa fabrcante com mais de 255 caracteres");
        Exception exception = assertThrows(RuntimeException.class, () -> {
            carro.setFabricante(stringCom256Caracteres);
        });
    
        String mensagemEsperada = "O nome do fabricante deve ter até 255 caracteres";
        String mensagemRecebida = exception.getMessage();
    
        assertTrue(mensagemRecebida.contains(mensagemEsperada));
    }
}
