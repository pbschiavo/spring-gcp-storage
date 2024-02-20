package br.com.sulamerica.saude.infraestrutura.portas;

import java.io.IOException;

public interface CredencialGCPPort {

    String getCredencial() throws IOException;
}
