package com.example.application.service;

import com.example.application.exception.StorageException;
import com.example.application.exception.StorageFileNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Objects;
import java.util.stream.Stream;

@Service
public class PaperStorageService implements StorageService {

    private final Path paperLocation;

    @Autowired
    public PaperStorageService() {
        this.paperLocation = Paths.get("src/main/resources/static/paper");
    }

    @Override
    public void init() {
        try {
            Files.createDirectories(paperLocation);
        } catch (IOException e) {
            throw new StorageException("Could not initialize storage.", e);
        }
    }

    @Override
    public void store(MultipartFile file) {
        try {
            if (file.isEmpty()) {
                throw new StorageException("Failed to store empty file.");
            }
            Path destinationFile = this.paperLocation.resolve(
                    Paths.get(Objects.requireNonNull(file.getOriginalFilename()))
            ).normalize().toAbsolutePath();
            if (!destinationFile.getParent().equals(this.paperLocation.toAbsolutePath())) {
                throw new StorageException("Cannot store file outside current directory.");
            }
            try (InputStream inputStream = file.getInputStream()) {
                Files.copy(
                        inputStream,
                        destinationFile,
                        StandardCopyOption.REPLACE_EXISTING
                );
            }
        } catch (IOException e) {
            throw new StorageException("Failed to store file.", e);
        }
    }

    @Override
    public Stream<Path> loadAll() {
        return null;
    }

    @Override
    public Path load(String filename) {
        return paperLocation.resolve(filename);
    }

    @Override
    public Resource loadAsResource(String filename) {
        try {
            Path file = load(filename);
            Resource resource = new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            }
            else {
                throw new StorageFileNotFoundException("Could not read file: " + filename);
            }
        } catch (MalformedURLException e) {
            throw new StorageFileNotFoundException("Could not read file: " + filename, e);
        }
    }

    @Override
    public void deleteAll() {

    }
}
