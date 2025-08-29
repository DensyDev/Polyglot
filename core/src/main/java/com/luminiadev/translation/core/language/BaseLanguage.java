package com.luminiadev.translation.core.language;

import com.luminiadev.translation.api.language.Language;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public class BaseLanguage implements Language {
    private final String languageCode;
    private final String countryCode;

    public BaseLanguage(String languageCode) {
        this(languageCode, null);
    }

    public BaseLanguage(String languageCode, String countryCode) {
        this.languageCode = languageCode.toLowerCase();
        this.countryCode = countryCode != null ? countryCode.toUpperCase() : null;
    }

    @Override
    public String getLanguageCode() {
        return languageCode;
    }

    @Override
    public String getCountryCode() {
        return countryCode;
    }

    @Override
    public String getFullCode() {
        return countryCode != null ? languageCode + "_" + countryCode : languageCode;
    }

    @Override
    public boolean isCompatibleWith(Language other) {
        if (other == null) return false;

        if (this.countryCode != null && other.getCountryCode() == null) {
            return this.languageCode.equals(other.getLanguageCode());
        }

        if (this.countryCode == null && other.getCountryCode() != null) {
            return this.languageCode.equals(other.getLanguageCode());
        }

        return this.equals(other);
    }
}
