package com.example.application.repository;

import com.example.application.model.Paper;
import org.springframework.data.repository.CrudRepository;

public interface PaperRepository extends CrudRepository<Paper, Integer> {
}
