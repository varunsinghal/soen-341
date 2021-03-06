package com.soen.empower.controller;

import com.soen.empower.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * FileController to handle file services.
 */
@Controller
@RequestMapping("/files")
public class FileController {

    @Autowired
    private StorageService storageService;

    /**
     * Service to render the files from the 'upload-dir' folder of file system.
     *
     * @param filename string based file name with extension
     * @return Resource file as downloadable media.
     */
    @RequestMapping("fetch/{filename:.+}")
    public ResponseEntity<Resource> serveFile(@PathVariable String filename) {

        Resource file = storageService.loadAsResource(filename);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }

}
