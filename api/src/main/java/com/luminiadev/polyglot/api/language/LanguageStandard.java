package com.luminiadev.polyglot.api.language;

/**
 * Language localization standard interface. Defines how language strings are parsed and formatted.
 */
public interface LanguageStandard {

    /**
     * Parses a language string into a Language object.
     *
     * @param languageString the language string to parse
     * @return parsed Language object
     * @throws IllegalArgumentException if the string doesn't match this standard
     */
    Language parseLanguage(String languageString);

    /**
     * Formats a Language object into a string representation.
     *
     * @param language the language to format
     * @return formatted language string
     */
    String formatLanguage(Language language);

    /**
     * Checks if a language string matches this standard format.
     *
     * @param languageString the language string to check
     * @return true if the string matches this standard
     */
    boolean matches(String languageString);
}
