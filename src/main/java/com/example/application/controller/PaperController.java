package com.example.application.controller;

import com.example.application.exception.StorageFileNotFoundException;
import com.example.application.model.Paper;
import com.example.application.repository.PaperRepository;
import com.example.application.service.PaperStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping(path = "/paper")
public class PaperController {

    @Autowired
    private PaperRepository paperRepository;

    private final PaperStorageService paperStorageService;

    @Autowired
    public PaperController(PaperStorageService paperStorageService) {
        this.paperStorageService = paperStorageService;
    }

    @PostMapping(path = "/analysis")
    @ResponseBody
    public Paper analysisPaper(
            @RequestParam("file") MultipartFile file
    ) {
        String filename = file.getOriginalFilename();

        Paper paper = new Paper();
        paper.setFilename(filename);

        return paper;
    }

    @PostMapping(path = "/upload")
    @ResponseBody
    public ResponseEntity<?> uploadPaper(
            @RequestParam("file") MultipartFile file,
            @RequestParam String title
    ) {
        try {
            paperStorageService.store(file);
            String filename = file.getOriginalFilename();

            Paper paper = new Paper();
            paper.setTitle(title);
            paper.setFilename(filename);
            paperRepository.save(paper);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping(path = "/all")
    public @ResponseBody Iterable<Paper> getAllPapers() {
        return paperRepository.findAll();
    }

    @GetMapping(path = "/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> downloadPaper(@PathVariable String filename) {
        Resource file = paperStorageService.loadAsResource(filename);
        return ResponseEntity.ok().header(
                HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + file.getFilename() + "\""
        ).body(file);
    }

    @ExceptionHandler(StorageFileNotFoundException.class)
    public ResponseEntity<?> handlePaperNotFound(StorageFileNotFoundException e) {
        return ResponseEntity.notFound().build();
    }
}
