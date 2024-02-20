package br.com.sulamerica.saude.infraestrutura.adaptadores;

import br.com.sulamerica.saude.infraestrutura.dto.CofreDTO;
import br.com.sulamerica.saude.infraestrutura.portas.CredencialGCPPort;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Slf4j
@Component
public class CredencialGCPImp implements CredencialGCPPort {

    public String getCredencial() throws IOException {
        return getCredencialMockHml();
    }

    public String getCredencialGCP() throws IOException {
        String retornoCofre = System.getenv("CREDENTIAL_CONTA_SERVICO");
        CofreDTO cofreContaServico = new Gson().fromJson(retornoCofre, CofreDTO.class);
        return cofreContaServico.getConteudo();
    }

    public String getCredencialMockHml() {
        String jsonCredentials = System.getenv("credencialGCPhml");
        return jsonCredentials;

    }

    public String getCredencialMockProd() {
        String jsonCredentials = System.getenv("credencialGCPPrd");
        return jsonCredentials;
    }
}