package com.example.application.repository;

import com.example.application.model.Keyword;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface KeywordRepository extends CrudRepository<Keyword, Integer> {
    List<Keyword> findByKeyword(String keyword);
}
