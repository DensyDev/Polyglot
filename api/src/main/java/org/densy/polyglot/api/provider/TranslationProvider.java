package org.densy.polyglot.api.provider;

import org.densy.polyglot.api.language.Language;

import java.util.Map;
import java.util.Set;

/**
 * Translation provider interface. Loads translations from various sources.
 */
public interface TranslationProvider {

    /**
     * Loads all translations.
     *
     * @return map of languages to their translation maps
     */
    Map<Language, Map<String, String>> getTranslations();

    /**
     * Gets all supported languages.
     *
     * @return set of supported languages
     */
    Set<Language> getSupportedLanguages();
}
