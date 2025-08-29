package com.luminiadev.translation.core.language;

import com.luminiadev.translation.api.language.Language;
import com.luminiadev.translation.api.language.LanguageStandard;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Locale language standard implementation.
 * Supports format: "ru_RU", "en_US", "en_GB" (language_COUNTRY).
 */
public class LocaleLanguageStandard implements LanguageStandard {
    private static final Pattern PATTERN = Pattern.compile("^([a-z]{2})_([A-Z]{2})$");

    @Override
    public Language parseLanguage(String languageString) {
        Matcher matcher = PATTERN.matcher(languageString);
        if (!matcher.matches()) {
            throw new IllegalArgumentException("Language string doesn't match locale standard: " + languageString);
        }
        return new BaseLanguage(matcher.group(1), matcher.group(2));
    }

    @Override
    public String formatLanguage(Language language) {
        String countryCode = language.getCountryCode();
        if (countryCode == null) {
            throw new IllegalArgumentException("Country code is required for locale standard");
        }
        return language.getLanguageCode() + "_" + countryCode;
    }

    @Override
    public boolean matches(String languageString) {
        return PATTERN.matcher(languageString).matches();
    }
}

