package br.com.sulamerica.saude.infraestrutura.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class CofreDTO{
    @JsonProperty("id") private String id;
    @JsonProperty("tag") private String tag;
    @JsonProperty("tipo") private String tipo;
    @JsonProperty("conteudo") private String conteudo;
}