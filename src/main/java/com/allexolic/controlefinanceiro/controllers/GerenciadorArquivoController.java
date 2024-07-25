package com.allexolic.controlefinanceiro.controllers;

import com.allexolic.controlefinanceiro.awsservices.AwsS3Client;
import com.allexolic.controlefinanceiro.entities.GerenciadorArquivo;
import lombok.val;
import org.joda.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping(path = "/api/gerenciador-arquivo")
public class GerenciadorArquivoController {
    @Autowired
    AwsS3Client awsS3Client;
    final String BUCKET_PREFIX = "nota-fiscal/";

    @PostMapping("/upload")
    public ResponseEntity<GerenciadorArquivo> enviarArquivo(@RequestParam("file") MultipartFile file) throws IOException {
        if(file == null)
            throw new RuntimeException("Selecione um arquivo para upload.");
        val arquivo = enviarArquivoS3(file);
        return new ResponseEntity<>(arquivo, HttpStatus.OK);
    }

    private GerenciadorArquivo enviarArquivoS3(MultipartFile file) throws IOException {
        GerenciadorArquivo arquivo = new GerenciadorArquivo();
        val keyName = BUCKET_PREFIX + UUID.randomUUID();
        awsS3Client.saveS3(keyName, file);
        arquivo.setDataUpload(LocalDateTime.now().toDate());
        arquivo.setPath(keyName);
        return arquivo;
    }

}
