package br.com.sulamerica.saude.infraestrutura.configuracao;

import br.com.sulamerica.saude.dominio.adaptadores.service.UploadServiceImp;
import br.com.sulamerica.saude.dominio.portas.UploadServicePort;
import br.com.sulamerica.saude.infraestrutura.portas.CredencialGCPPort;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Collections;

@Component
@RequiredArgsConstructor
public class ConfiguracaoBean {

    private final CredencialGCPPort credencialGCPPort;

    @Bean
    public Storage autenticacao() throws IOException {

        // Defina suas credenciais manualmente
        String credentialsJson = credencialGCPPort.getCredencial();

        // Inicialize as credenciais de autenticação manualmente
        GoogleCredentials googleCredentials = GoogleCredentials.fromStream(new ByteArrayInputStream(credentialsJson.getBytes()))
                .createScoped(Collections.singleton("https://www.googleapis.com/auth/cloud-platform"));

        // Inicialize o cliente de armazenamento com as credenciais
        return StorageOptions.newBuilder()
                .setCredentials(googleCredentials)
                .build().getService();
    }

    @Bean
    public UploadServicePort service(Storage storage, GcpProperties gcpProperties) {
        return new UploadServiceImp(storage, gcpProperties);
    }

}
