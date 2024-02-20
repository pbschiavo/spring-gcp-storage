package br.com.sulamerica.saude.dominio.adaptadores.service;

import br.com.sulamerica.saude.dominio.portas.UploadServicePort;
import br.com.sulamerica.saude.infraestrutura.configuracao.GcpProperties;
import com.google.cloud.storage.Blob;
import com.google.cloud.storage.Bucket;
import com.google.cloud.storage.Storage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
public class UploadServiceImp implements UploadServicePort {

    private final Storage storage;
    private final GcpProperties gcpProperties;
    @Override
    public void upload(MultipartFile file) throws IOException {

        // Nome do arquivo no bucket
        String blobName = file.getOriginalFilename();

        // Obtenha uma referência para o bucket
        Bucket bucket = storage.get(gcpProperties.getBucketName());
        if (bucket == null) {
            log.error("Bucket não encontrado");
            return;
        }

        // Crie um Blob no bucket com o arquivo recebido
        Blob blob = bucket.create(blobName, file.getInputStream(), file.getContentType());

        log.info("Arquivo enviado com sucesso para: " + blob.getName());
    }
}
