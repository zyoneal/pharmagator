package com.eleks.academy.pharmagator.controllers;

import com.eleks.academy.pharmagator.services.CsvHelper;
import com.eleks.academy.pharmagator.services.CsvService;
import com.eleks.academy.pharmagator.view.ResponseMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("medicines/csv")
@RequiredArgsConstructor
public class UploadController {

    private final CsvService csvService;

    @PostMapping("/upload")
    public ResponseEntity uploadCSVFile(@RequestParam("file") MultipartFile file) {
        String message = "";
        if (CsvHelper.hasCSVFormat(file)) {
            try {
                csvService.save(file);
                message = "Uploaded the file successfully" + file.getOriginalFilename();
                return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
            } catch (Exception e) {
                message = "Could not upload the file: " + file.getOriginalFilename() + "!";
                return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
            }
        }
        message = "Please upload a csv file!";
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(message));
    }

}
