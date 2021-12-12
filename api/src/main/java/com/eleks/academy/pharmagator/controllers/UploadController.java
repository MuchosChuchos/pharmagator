package com.eleks.academy.pharmagator.controllers;

import com.eleks.academy.pharmagator.services.CsvService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/ui/upload")
@RequiredArgsConstructor
public class UploadController {

    private final CsvService csvService;

    @GetMapping
    public String getImportPage() {
        return "importData";
    }

    @PostMapping("/medicines")
    public String uploadCsv(@RequestParam("file") MultipartFile file) {
        csvService.parseAndSave(file);
        return "importResult";
    }

}

