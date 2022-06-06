package com.sanxiang.bank.controller.api;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.OutputStream;
import java.util.Date;

@Controller
@Slf4j
public class LogController {
    @GetMapping(value = "/logging/file")
    public ResponseEntity<Resource> getFile(@RequestParam String date) throws FileNotFoundException {
        String contentDisposition = ContentDisposition
                .builder("attachment")
                .filename("spring.log."+date+".0.gz")
                .build().toString();
        log.info("下载归档的日志[{}]",date);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, contentDisposition)
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(new FileSystemResource("./logs/spring.log."+date+".0.gz"));
    }
}
