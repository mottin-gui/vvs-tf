package com.vvs.trabalhofinal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.UnsupportedEncodingException;

import com.vvs.trabalhofinal.modelos.Carro;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureWebMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

/**
 * Classe que implementa os testes de integração do Sistema Carros,
 * ao testar se o MVC está conseguindo persistir dados no banco
 */
@TestMethodOrder(OrderAnnotation.class)
@SpringBootTest(classes = TrabalhofinalApplication.class, webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureWebMvc
@AutoConfigureMockMvc
public class TrabalhofinalApplicationIT {

    /**
     * Porta em que o Sistema Carros ficará disponível
     */
    @LocalServerPort
    private int porta;

    /**
     * Instância que facilita a construção de requisições HTTP
     */
    @Autowired
    private MockMvc mvc;

    /**
     * Teste para passar
     */
    @Test
    @Order(1)
    public void testaInicializacao() {

        assertEquals(1,1);
    }

    /**
     * Encaminha pra camada de controle uma requisição para que
     * todos os carros do banco sejam listados, verificando na resposta
     * se todos estão contidos.
     * @throws UnsupportedEncodingException
     * @throws Exception
     */
    @Test
    @Order(2)
    public void recuperaTodos() throws UnsupportedEncodingException, Exception {
        
        String resposta = mvc.perform(MockMvcRequestBuilders.get("/api/carros"))
                            .andReturn().getResponse().getContentAsString();

        boolean condicaoSucesso = (resposta.contains("Marea") && resposta.contains("Mustang"));

        assertTrue(condicaoSucesso);
    }

    /**
     * Encaminha uma requisição para a camada de controle inserir
     * um objeto Carro, também encaminhado na requisição. Testa, na 
     * resposta recebida, se houve sucesso.
     * @throws Exception
     */
    @Test
    @Order(3)
    public void criaCarro() throws Exception {
        Carro novoCarro = new Carro();
        novoCarro.setId(null);
        novoCarro.setCor("Azul");
        novoCarro.setModelo("Santana");
        novoCarro.setFabricante("Volkswagen");

        String resposta = mvc.perform(MockMvcRequestBuilders.post("/api/carro").flashAttr("carro", novoCarro))
                .andReturn().getResponse().getContentAsString();

        boolean condicaoSucesso = (resposta.contains("Santana") && resposta.contains("Volkswagen")
                && resposta.contains("Azul"));

        assertTrue(condicaoSucesso);
    }

    /**
     * Encaminha a camada de controle uma requisição para buscar no banco
     * de dados o carro com determinado id, testando se o valor esperado foi
     * retornado na resposta fornecida.
     * @throws UnsupportedEncodingException
     * @throws Exception
     */
    @Test
    @Order(4)
    public void recuperaCarro() throws UnsupportedEncodingException, Exception {

        String resposta = mvc.perform(MockMvcRequestBuilders.get("/api/carro/{id}",2)).
                            andReturn().getResponse().getContentAsString();

        boolean condicaoSucesso = (resposta.contains("Mustang GT") && resposta.contains("Ford")
                    && resposta.contains("creme"));

        assertTrue(condicaoSucesso);
    }

    /**
     * Encaminha para a camada de controle uma requisição para que atualize no banco
     * de dados as informações (passadas na própria requisição) do carro com 
     * determinado id, testando se as alterações foram feitas na resposta recebida.
     * @throws UnsupportedEncodingException
     * @throws Exception
     */
    @Test
    @Order(5)
    public void atualizaCarro() throws UnsupportedEncodingException, Exception 
    {
        Carro carroNovaCor = new Carro();
        carroNovaCor.setId(null);
        carroNovaCor.setCor("amarelo");
        carroNovaCor.setModelo("Marea");
        carroNovaCor.setFabricante("FIAT");

        String resposta= mvc.perform( MockMvcRequestBuilders
        .put("/api/atualizaCarro/{id}","1").flashAttr("carro", carroNovaCor))
        .andReturn().getResponse().getContentAsString();

        boolean condicaoSucesso = (resposta.contains("amarelo") && 
            resposta.contains("Marea") && 
            resposta.contains("FIAT"));

        assertTrue(condicaoSucesso);

    }

    /**
     * Encaminha a camada de controle uma requisição para que o carro com 
     * um determinado id seja deletado do banco, testando se a tela de sucesso
     * apareceu em seguida.
     * @throws UnsupportedEncodingException
     * @throws Exception
     */
    @Test
    @Order(6)
    public void deletaCarro() throws UnsupportedEncodingException, Exception 
    {
        String resposta= mvc.perform( MockMvcRequestBuilders
        .delete("/api/carro/{id}","2")).andReturn().getResponse().getContentAsString();

        boolean condicaoSucesso = (resposta.contains("Sucesso ao deletar o carro!"));

        assertTrue(condicaoSucesso);
    }
}
