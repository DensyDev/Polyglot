package com.luminiadev.translation.api.context;

import com.luminiadev.translation.api.Translation;
import com.luminiadev.translation.api.language.Language;
import com.luminiadev.translation.api.language.LanguageStandard;
import com.luminiadev.translation.api.provider.TranslationProvider;

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
     * @param key the parameter key
     * @param value the parameter value to add
     */
    void addGlobalParameters(String key, Object value);

    /**
     * Gets current global parameters.
     *
     * @return global parameters
     */
    Map<String, String> getGlobalTranslations(Language language);

    /**
     * Adds global translations for a language.
     *
     * @param language the target language
     * @param translations the translations to add
     */
    void addGlobalTranslations(Language language, Map<String, String> translations);

    /**
     * Adds global translation for a language.
     *
     * @param language the target language
     * @param key the message key
     * @param translation the translation
     */
    void addGlobalTranslation(Language language, String key, String translation);

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
