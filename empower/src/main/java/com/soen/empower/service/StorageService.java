package com.soen.empower.service;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.stream.Stream;

/**
 * Storage service.
 *
 * @version 4.0
 * @see <a href="https://spring.io/guides/gs/uploading-files/">Uploading files</a>
 * @since 4.0
 */
@Service
public class StorageService {
    private Path rootLocation = Paths.get("upload-dir");

    /**
     * Upload method
     *
     * @param file receives a file.
     */
    public void store(MultipartFile file) {
        String filename = StringUtils.cleanPath(file.getOriginalFilename());
        try {
            if (file.isEmpty()) {
                System.out.println("Failed to store empty file " + filename);
            }
            if (filename.contains("..")) {
                // This is a security check
                System.out.println("Cannot store file with relative path outside current directory " + filename);
            }
            try (InputStream inputStream = file.getInputStream()) {
                Files.copy(inputStream, this.rootLocation.resolve(filename),
                        StandardCopyOption.REPLACE_EXISTING);
            }
        } catch (IOException e) {
            System.out.println("Failed to store file " + filename);
        }
    }

    /**
     * Load all the files.
     *
     * @return all the paths in stream.
     */
    public Stream<Path> loadAll() {
        try {
            return Files.walk(this.rootLocation, 1)
                    .filter(path -> !path.equals(this.rootLocation))
                    .map(this.rootLocation::relativize);
        } catch (IOException e) {
            System.out.println("Failed to read stored files");
        }
        return null;
    }

    /**
     * load the file with filename as string
     *
     * @param filename string
     * @return path object
     */
    public Path load(String filename) {
        return rootLocation.resolve(filename);
    }

    /**
     * locate the file and return as a resource
     *
     * @param filename file to fetched
     * @return resource file
     */
    public Resource loadAsResource(String filename) {
        try {
            Path file = load(filename);
            Resource resource = new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                System.out.println("Could not read file: " + filename);

            }
        } catch (MalformedURLException e) {
            System.out.println("Could not read file: " + filename);
        }
        return null;
    }

    /**
     * Do not use this.
     */
    public void deleteAll() {
        FileSystemUtils.deleteRecursively(rootLocation.toFile());
    }

    /**
     * Initialise the storage.
     */
    public void init() {
        try {
            Files.createDirectories(rootLocation);
        } catch (IOException e) {
            System.out.println("Could not initialize storage");
        }
    }

}
