package br.com.oldschool69.rest_with_spring_boot_and_java.dto.wrappers;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class WrapperPersonDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonProperty("_embedded")
    private PersonEmbeddedDTO embedded;

    public WrapperPersonDTO() {
    }

    public PersonEmbeddedDTO getEmbedded() {
        return embedded;
    }

    public void setEmbedded(PersonEmbeddedDTO embedded) {
        this.embedded = embedded;
    }
}
