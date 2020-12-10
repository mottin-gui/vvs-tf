package com.vvs.trabalhofinal.excecoes;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Classe que trata exceções quando recursos não são encontrados
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {
    /**
     *atributo exigido para controlar a versão da build
     */
    private static final long serialVersionUID = 1L;

    /**
     * Recurso o qual tentativa de acesso gerou a exceção
     */
    private String resourceName;

    /**
     * Nome do campo que gerou a exceção
     */
    private String fieldName;

    /**
     * Valor do campo que gerou a exceção
     */
    private Object fieldValue;

    /**
     * Monta a mensagem de recurso não encontrado
     * @param resourceName
     * @param fieldName
     * @param fieldValue
     */
    public ResourceNotFoundException( String resourceName, String fieldName, Object fieldValue) {
        super(String.format("%s not found with %s : '%s'", resourceName, fieldName, fieldValue));
        this.resourceName = resourceName;
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
    }

    public String getResourceName() {
        return resourceName;
    }

    public String getFieldName() {
        return fieldName;
    }

    public Object getFieldValue() {
        return fieldValue;
    }
}