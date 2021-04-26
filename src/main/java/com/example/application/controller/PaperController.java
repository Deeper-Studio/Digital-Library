package com.example.application.controller;

import com.example.application.enumeration.PaperReviewStateEnum;
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

import java.text.SimpleDateFormat;
import java.util.Date;

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
            // Modify the name of the uploaded file, in order to avoid duplication of name.
            // The naming rule is to add the upload time after the original file name.
            Date date = new Date();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
            String createdDate = simpleDateFormat.format(date);
            String originalFilename = file.getOriginalFilename();
            String originalFilenameWithoutExtension = originalFilename.substring(0, originalFilename.length()-4);
            String destinationFilename = originalFilenameWithoutExtension + createdDate + ".pdf";

            // Store the uploaded file on disk with the modified name.
            paperStorageService.store(file, destinationFilename);

            // Save a new record in database.
            Paper paper = new Paper();
            paper.setTitle(title);
            paper.setFilename(destinationFilename);
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
