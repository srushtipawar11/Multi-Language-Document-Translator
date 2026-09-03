package com.translation.documenttranslation.service;

import org.springframework.stereotype.Service;

@Service
public class TranslationService {

    public String translate(String text, String targetLanguage) {

        if (text == null || text.isBlank()) {
            return "No text available for translation.";
        }

        if (targetLanguage == null || targetLanguage.isBlank()) {
            return "Please select a target language.";
        }

        // Temporary translation logic
        // Real translation API will be connected in the next step.

        return "TRANSLATED TEXT (" + targetLanguage + ")\n\n"
                + text;
    }
}