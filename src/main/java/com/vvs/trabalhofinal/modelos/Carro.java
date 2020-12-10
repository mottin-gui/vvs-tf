package com.vvs.trabalhofinal.modelos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import javax.persistence.*;

/**
 * Classe para representar instâncias de Carros
 */
@Entity
@Table(name = "carros")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"createdAt", "updatedAt"}, allowGetters = true)
public class Carro{

    /**
     * Identificador único do carro no banco de dados
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Descrição da cor do carro
     */
    private String cor;

    /**
     * Nome do modelo
     */
    private String modelo;

    /**
     * Nome da montadora que fabricou o veículo
     */
    private String fabricante;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCor() {
        return this.cor;
    }

    /**
     * Define a cor do carro.
     * @param cor Obrigatório
     */
    public void setCor(String cor) {
        if(cor==null || cor.isEmpty()){
            throw new RuntimeException("Deve ser informada uma cor");
        }else if(cor.length()>255){
            throw new RuntimeException("A cor deve ter até 255 caracteres");
        }else{
            this.cor = cor;
        }
    }

    public String getModelo() {
        return this.modelo;
    }

    /**
     * Define o modelo do carro
     * @param modelo Obrigatório
     */
    public void setModelo(String modelo) {
        if(modelo==null || modelo.isEmpty()){
            throw new RuntimeException("Deve ser informado o nome do modelo");
        }else if(modelo.length()>255){
            throw new RuntimeException("O modelo deve ter até 255 caracteres");
        }else{
            this.modelo = modelo;
        }
    }

    public String getFabricante() {
        return this.fabricante;
    }

    /**
     * Define o fabricante
     * @param fabricante Obrigatório
     */
    public void setFabricante(String fabricante) {
        if(fabricante==null || fabricante.isEmpty()){
            throw new RuntimeException("Deve ser informado um fabricante");
        }else if(fabricante.length()>255){
            throw new RuntimeException("O nome do fabricante deve ter até 255 caracteres");
        }else{
            this.fabricante = fabricante;
        }
    }

}

