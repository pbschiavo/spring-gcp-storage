package br.com.sulamerica.saude.dominio.portas;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface UploadServicePort {

    void upload(MultipartFile file) throws IOException;
}
