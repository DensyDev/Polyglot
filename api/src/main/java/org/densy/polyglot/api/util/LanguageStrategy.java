package org.densy.polyglot.api.util;

import org.densy.polyglot.api.language.Language;

import java.util.Map;

/**
 * Missing language strategy interface. Defines how to resolve missing language.
 */
@FunctionalInterface
public interface LanguageStrategy {

    /**
     * Resolves a value for the given missing language.
     *
     * @param missingLanguage the missing language
     * @return resolved value
     */
    Language get(Language missingLanguage);

    /**
     * Returns a default language.
     *
     * @param defaultLanguage default language
     * @return strategy returning the specified language
     */
    static LanguageStrategy defaultResult(Language defaultLanguage) {
        return missingLanguage -> defaultLanguage;
    }

    /**
     * Returns a strategy based on passed mappings.
     *
     * @param mappings the map with language mappings
     * @return strategy based on passed mappings
     */
    static LanguageStrategy mappingsOf(Map<Language, Language> mappings) {
        return missingLanguage -> mappings.getOrDefault(missingLanguage, null);
    }

    /**
     * Crates the new language mappings builder.
     *
     * @return crated LanguageMappingBuilder
     */
    static LanguageMappingBuilder mappingsBuilder() {
        return new LanguageMappingBuilder();
    }
}

