package org.densy.polyglot.api;

import org.densy.polyglot.api.formatter.TranslationFormatterAware;
import org.densy.polyglot.api.language.Language;
import org.densy.polyglot.api.parameter.TranslationParameters;
import org.densy.polyglot.api.util.FallbackStrategy;
import org.densy.polyglot.api.util.LanguageStrategy;

import java.util.Set;

/**
 * Translation interface. Provides methods for translating keys into localized strings
 * with support for parameters, formatters, and fallback strategies.
 */
public interface Translation extends TranslationFormatterAware, TranslationsAware {

    /**
     * Translates a key into the target language.
     *
     * @param language the target language
     * @param key      the translation key
     * @return translated string, or the key itself if translation not found
     */
    String translate(Language language, String key);

    /**
     * Translates a key into the target language with parameters.
     *
     * @param language   the target language
     * @param key        the translation key
     * @param parameters the translation parameters
     * @return translated and formatted string
     */
    String translate(Language language, String key, TranslationParameters parameters);

    /**
     * Translates a key into the target language with array parameters.
     * Parameters are accessible as {0}, {1}, {2}, etc.
     *
     * @param language   the target language
     * @param key        the translation key
     * @param parameters the array parameters
     * @return translated and formatted string
     */
    String translate(Language language, String key, Object... parameters);

    /**
     * Gets the default language for this translation.
     *
     * @return default language
     */
    Language getDefaultLanguage();

    /**
     * Sets the default language for this translation.
     *
     * @param language the language to set as default
     */
    void setDefaultLanguage(Language language);

    /**
     * Sets the language strategy that determines which language to use
     * when the requested language is not available.
     *
     * @param languageStrategy the language strategy
     */
    void setLanguageStrategy(LanguageStrategy languageStrategy);

    /**
     * Sets the fallback strategy that determines what to return
     * when a translation key is not found.
     *
     * @param fallbackStrategy the fallback strategy
     */
    void setFallbackStrategy(FallbackStrategy fallbackStrategy);

    /**
     * Gets all languages that have at least one translation in this instance.
     *
     * @return set of available languages
     */
    Set<Language> getAvailableLanguages();
}