package br.com.sulamerica.saude.infraestrutura.configuracao;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "spring.gcp")
public class GcpProperties {

    private String bucketName;
}
