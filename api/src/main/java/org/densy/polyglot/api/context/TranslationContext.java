package org.densy.polyglot.api.context;

import org.densy.polyglot.api.Translation;
import org.densy.polyglot.api.language.Language;
import org.densy.polyglot.api.language.LanguageStandard;
import org.densy.polyglot.api.provider.TranslationProvider;

import java.util.Map;

/**
 * Translation context interface. Manages global parameters
 * and translations shared across all Translation instances.
 */
public interface TranslationContext {

    /**
     * Creates a new translation from a provider.
     *
     * @param provider The translation provider
     * @return new translation instance
     */
    Translation createTranslation(TranslationProvider provider);

    /**
     * Creates a new translation with empty provider.
     *
     * @return new translation instance
     */
    Translation createTranslation();

    /**
     * Gets current global parameters.
     *
     * @return global parameters
     */
    Map<String, Object> getGlobalParameters();

    /**
     * Adds global parameter available to all translations.
     *
     * @param parameters the parameters to add
     */
    void addGlobalParameters(Map<String, Object> parameters);

    /**
     * Adds global parameter available to all translations.
     *
     * @param key   the parameter key
     * @param value the parameter value to add
     */
    void addGlobalParameter(String key, Object value);

    /**
     * Gets all global translations.
     *
     * @return global translations
     */
    Map<Language, Map<String, String>> getGlobalTranslations();

    /**
     * Gets global translations for language.
     *
     * @return global translations
     */
    Map<String, String> getGlobalTranslations(Language language);

    /**
     * Adds global translations for a language.
     *
     * @param language     the target language
     * @param translations the translations to add
     */
    void addGlobalTranslations(Language language, Map<String, String> translations);

    /**
     * Adds global translation for a language.
     *
     * @param language    the target language
     * @param key         the message key
     * @param translation the translation
     */
    void addGlobalTranslation(Language language, String key, String translation);

    /**
     * Gets the language by default.
     *
     * @return default language
     */
    Language getDefaultLanguage();

    /**
     * Sets the language by default
     *
     * @param language language to set
     */
    void setDefaultLanguage(Language language);

    /**
     * Gets the current language standard.
     *
     * @return current language standard
     */
    LanguageStandard getLanguageStandard();

    /**
     * Sets the language standard for this context.
     *
     * @param standard standard the language standard
     */
    void setLanguageStandard(LanguageStandard standard);
}
