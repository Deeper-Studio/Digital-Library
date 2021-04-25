package com.example.application.repository;

import com.example.application.model.Paper;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PaperRepository extends CrudRepository<Paper, Integer> {
    List<Paper> findByTitleLike(String title);
}
