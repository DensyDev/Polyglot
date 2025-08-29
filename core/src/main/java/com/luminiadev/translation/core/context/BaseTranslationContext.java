package com.luminiadev.translation.core.context;

import com.luminiadev.translation.api.Translation;
import com.luminiadev.translation.api.context.TranslationContext;
import com.luminiadev.translation.api.language.Language;
import com.luminiadev.translation.api.language.LanguageStandard;
import com.luminiadev.translation.api.provider.TranslationProvider;
import com.luminiadev.translation.core.BaseTranslation;
import com.luminiadev.translation.core.language.SimpleLanguageStandard;
import com.luminiadev.translation.core.parameter.KeyedTrParameters;
import com.luminiadev.translation.core.provider.EmptyProvider;

import java.util.HashMap;
import java.util.Map;

/**
 * Base implementation of the translation context.
 */
public class BaseTranslationContext implements TranslationContext {

    private final Map<Language, Map<String, String>> globalTranslations;
    private KeyedTrParameters globalParameters;
    private LanguageStandard languageStandard;

    public BaseTranslationContext() {
        this.globalTranslations = new HashMap<>();
        this.globalParameters = new KeyedTrParameters();
        this.languageStandard = new SimpleLanguageStandard();
    }

    @Override
    public Translation createTranslation(TranslationProvider provider) {
        return new BaseTranslation(this, provider);
    }

    @Override
    public Translation createTranslation() {
        return new BaseTranslation(this, new EmptyProvider());
    }

    @Override
    public Map<String, Object> getGlobalParameters() {
        return globalParameters.getParameters();
    }

    @Override
    public void addGlobalParameters(Map<String, Object> parameters) {
        this.globalParameters = this.globalParameters.merge(new KeyedTrParameters(parameters));
    }

    @Override
    public void addGlobalParameter(String key, Object value) {
        this.addGlobalParameters(Map.of(key, value));
    }

    @Override
    public Map<Language, Map<String, String>> getGlobalTranslations() {
        return globalTranslations;
    }

    @Override
    public Map<String, String> getGlobalTranslations(Language language) {
        Map<String, String> translations = globalTranslations.get(language);
        return translations != null ? new HashMap<>(translations) : new HashMap<>();
    }

    @Override
    public void addGlobalTranslations(Language language, Map<String, String> translations) {
        if (language == null || translations == null) {
            return;
        }
        globalTranslations.computeIfAbsent(language, k -> new HashMap<>()).putAll(translations);
    }

    @Override
    public void addGlobalTranslation(Language language, String key, String translation) {
        if (language == null || key == null || translation == null) {
            return;
        }
        this.addGlobalTranslations(language, Map.of(key, translation));
    }

    @Override
    public LanguageStandard getLanguageStandard() {
        return languageStandard;
    }

    @Override
    public void setLanguageStandard(LanguageStandard standard) {
        this.languageStandard = standard != null ? standard : new SimpleLanguageStandard();
    }
}
