package com.translation.documenttranslation.controller;

import com.translation.documenttranslation.service.PdfTextExtractorService;
import com.translation.documenttranslation.service.TranslationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/documents")
public class TestController {

    @Autowired
    private PdfTextExtractorService pdfTextExtractorService;

    @Autowired
    private TranslationService translationService;

    @PostMapping(
            value = "/upload",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE
    )
    public String uploadDocument(
            @RequestParam("file") MultipartFile file,
            @RequestParam("targetLanguage") String targetLanguage) {

        try {

            if (file == null || file.isEmpty()) {
                return "Please select a PDF file.";
            }

            // Step 1: Extract text from PDF
            String extractedText =
                    pdfTextExtractorService.extractText(file);

            // Step 2: Translate extracted text
            String translatedText =
                    translationService.translate(
                            extractedText,
                            targetLanguage
                    );

            // Step 3: Return result
            return "File uploaded successfully: "
                    + file.getOriginalFilename()
                    + "\n\n"
                    + "Target Language: "
                    + targetLanguage
                    + "\n\n"
                    + "==============================\n"
                    + "TRANSLATED TEXT\n"
                    + "==============================\n\n"
                    + translatedText;

        } catch (Exception e) {

            e.printStackTrace();

            return "Error processing document: "
                    + e.getMessage();
        }
    }
}