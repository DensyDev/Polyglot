package org.densy.polyglot.api;

import org.densy.polyglot.api.language.Language;
import org.jetbrains.annotations.UnmodifiableView;

import java.util.Map;

/**
 * Interface for managing translation storage.
 */
public interface TranslationsAware {

    /**
     * Gets all translations organized by language and key.
     *
     * @return unmodifiable map of translations (Language -> Key -> Translation)
     */
    @UnmodifiableView
    Map<Language, Map<String, String>> getTranslations();

    /**
     * Adds a single translation for a specific language and key.
     *
     * @param language the target language
     * @param key the translation key
     * @param value the translation value
     */
    void addTranslation(Language language, String key, String value);

    /**
     * Adds multiple translations for a specific language.
     *
     * @param language the target language
     * @param translations map of translation keys to values
     */
    void addTranslations(Language language, Map<String, String> translations);

    /**
     * Removes a translation for a specific language and key.
     *
     * @param language the target language
     * @param key the translation key to remove
     */
    void removeTranslation(Language language, String key);
}