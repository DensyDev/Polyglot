package com.luminiadev.polyglot.core.language;

import com.luminiadev.polyglot.api.language.Language;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * Base implementation of the Language interface.
 */
@EqualsAndHashCode
@ToString
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

    /**
     * Parses the language with the country code if it exists, otherwise without it.
     *
     * @param languageCode Language code to parse
     * @return BaseLanguage class
     */
    public static BaseLanguage parseLanguage(String languageCode) {
        if (languageCode.contains("_")) {
            String[] parts = languageCode.split("_");
            return new BaseLanguage(parts[0], parts[1]);
        } else  {
            return new BaseLanguage(languageCode);
        }
    }
}
