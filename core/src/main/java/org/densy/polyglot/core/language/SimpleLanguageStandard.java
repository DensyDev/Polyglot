package org.densy.polyglot.core.language;

import org.densy.polyglot.api.language.Language;
import org.densy.polyglot.api.language.LanguageStandard;

import java.util.regex.Pattern;

/**
 * Simple language standard implementation.
 * Supports format: "eng", "rus", "ukr" (2-3 letter language codes).
 */
public class SimpleLanguageStandard implements LanguageStandard {
    private static final Pattern PATTERN = Pattern.compile("^[a-z]{2,3}$");

    @Override
    public Language parseLanguage(String languageString) {
        if (!matches(languageString)) {
            throw new IllegalArgumentException("Language string doesn't match simple standard: " + languageString);
        }
        return new BaseLanguage(languageString);
    }

    @Override
    public String formatLanguage(Language language) {
        return language.getLanguageCode();
    }

    @Override
    public boolean matches(String languageString) {
        return PATTERN.matcher(languageString.toLowerCase()).matches();
    }
}

