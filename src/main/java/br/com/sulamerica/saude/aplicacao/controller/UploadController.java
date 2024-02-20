package br.com.sulamerica.saude.aplicacao.controller;

import br.com.sulamerica.saude.dominio.portas.UploadServicePort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/v1/minhaapi")
@RequiredArgsConstructor
@Slf4j
public class UploadController {

    private final UploadServicePort uploadServicePort;

    @PostMapping("/upload")
    public ResponseEntity<String> upload(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body("O arquivo está vazio.");
        }
        try {
            uploadServicePort.upload(file);
            return ResponseEntity.ok("Arquivo enviado com sucesso.");
        } catch (IOException e) {
            log.error(e.toString());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ocorreu um erro ao enviar o arquivo.");
        }
    }
}
