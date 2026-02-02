package org.densy.polyglot.api.util;

import org.densy.polyglot.api.language.Language;

import java.util.HashMap;
import java.util.Map;

/**
 * Language mapping strategy builder.
 */
public final class LanguageMappingBuilder {

    private final Map<Language, Language> mappings = new HashMap<>();

    /**
     * Sets the fallback language for a missing language.
     *
     * @param missingLanguage missing language
     * @param mappedLanguage  fallback language
     * @return the builder itself
     */
    public LanguageMappingBuilder put(Language missingLanguage, Language mappedLanguage) {
        this.mappings.put(missingLanguage, mappedLanguage);
        return this;
    }

    /**
     * Builds language strategy.
     *
     * @return LanguageStrategy
     */
    public LanguageStrategy build() {
        return LanguageStrategy.mappingsOf(mappings);
    }
}
