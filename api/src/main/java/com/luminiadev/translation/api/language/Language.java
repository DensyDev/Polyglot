package com.luminiadev.translation.api.language;

/**
 * Represents a language.
 */
public interface Language {

    /**
     * Gets the language code (e.g. "en", "ru").
     *
     * @return language code
     */
    String getLanguageCode();

    /**
     * Gets the country code (may be null for simple format).
     *
     * @return country code or null
     */
    String getCountryCode();

    /**
     * Gets the full language representation.
     *
     * @return full language code
     */
    String getFullCode();

    /**
     * Checks compatibility with another language.
     *
     * @param other the other language to check
     * @return true if languages are compatible
     */
    boolean isCompatibleWith(Language other);
}
