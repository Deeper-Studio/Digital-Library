package com.example.application.controller;

import com.example.application.model.Keyword;
import com.example.application.model.Paper;
import com.example.application.repository.KeywordRepository;
import com.example.application.repository.PaperRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(path = "/search")
public class SearchController {

    @Autowired
    private PaperRepository paperRepository;

    @Autowired
    private KeywordRepository keywordRepository;

    @GetMapping(path = "/keyword/{keyword}")
    @ResponseBody
    public List<Keyword> searchByKeyword(@PathVariable String keyword) {
        return keywordRepository.findByKeyword(keyword);
    }

    @GetMapping(path = "/paper")
    @ResponseBody
    public List<Paper> search(String title) {
        return paperRepository.findByTitleLike(title);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<?> handleIllegalArgument(IllegalArgumentException e) {
        return ResponseEntity.badRequest().build();
    }
}
