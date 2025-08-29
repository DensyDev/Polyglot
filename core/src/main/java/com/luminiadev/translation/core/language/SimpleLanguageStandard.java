package com.luminiadev.translation.core.language;

import com.luminiadev.translation.api.language.Language;
import com.luminiadev.translation.api.language.LanguageStandard;

import java.util.regex.Pattern;

public class SimpleLanguageStandard implements LanguageStandard {
    private static final Pattern PATTERN = Pattern.compile("^[a-z]{2,3}$");

    @Override
    public String getName() {
        return "simple";
    }

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

