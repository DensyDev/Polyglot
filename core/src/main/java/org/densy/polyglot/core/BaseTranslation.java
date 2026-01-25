package org.densy.polyglot.core;

import org.densy.polyglot.api.util.FallbackStrategy;
import org.densy.polyglot.api.Translation;
import org.densy.polyglot.api.context.TranslationContext;
import org.densy.polyglot.api.language.Language;
import org.densy.polyglot.api.parameter.TrParameters;
import org.densy.polyglot.api.parameter.formatter.TrParameterFormatter;
import org.densy.polyglot.api.provider.TranslationProvider;
import org.densy.polyglot.api.util.LanguageStrategy;
import org.densy.polyglot.core.parameter.KeyedTrParameters;
import org.densy.polyglot.core.parameter.SimpleTrParameters;
import org.densy.polyglot.core.parameter.formatter.BraceKeyedParameterFormatter;
import org.densy.polyglot.core.parameter.formatter.BracketSimpleParameterFormatter;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Base implementation of the translation.
 */
public class BaseTranslation implements Translation {

    private final TranslationContext context;
    private final Map<Language, Map<String, String>> translations;
    private final Map<Class<? extends TrParameters>, TrParameterFormatter> formatters;

    private Language defaultLanguage;
    private LanguageStrategy languageStrategy;
    private FallbackStrategy fallbackStrategy;

    public BaseTranslation(TranslationContext context, TranslationProvider provider) {
        this.context = context;
        this.translations = new HashMap<>();
        this.formatters = new HashMap<>();

        this.formatters.put(SimpleTrParameters.class, new BracketSimpleParameterFormatter());
        this.formatters.put(KeyedTrParameters.class, new BraceKeyedParameterFormatter());

        if (provider != null) {
            translations.putAll(provider.getTranslations());
        }

        this.defaultLanguage = context.getDefaultLanguage();
    }

    @Override
    public String translate(Language language, String key) {
        return translate(language, key, (TrParameters) null);
    }

    @Override
    public String translate(Language language, String key, TrParameters parameters) {
        Language targetLanguage = resolveLanguage(language);
        String translation = findTranslation(targetLanguage, key);

        if (translation == null) {
            translation = fallbackStrategy != null ? fallbackStrategy.get(key) : key;
        }

        return applyParameters(translation, parameters);
    }

    @Override
    public String translate(Language language, String key, Object... parameters) {
        return translate(language, key, new SimpleTrParameters(parameters));
    }

    private Language resolveLanguage(Language requestedLanguage) {
        if (this.isLanguageAvailable(requestedLanguage)) {
            return requestedLanguage;
        }

        Language candidateLanguage = requestedLanguage;
        while (true) {
            candidateLanguage = this.languageStrategy != null ? this.languageStrategy.get(candidateLanguage) : null;
            if (candidateLanguage == null) {
                break;
            }
            if (this.isLanguageAvailable(candidateLanguage)) {
                return candidateLanguage;
            }
        }

        Language defaultLanguage = this.defaultLanguage != null ? this.defaultLanguage : context.getDefaultLanguage();
        if (this.isLanguageAvailable(defaultLanguage)) {
            return defaultLanguage;
        }

        return requestedLanguage;
    }

    private boolean isLanguageAvailable(Language language) {
        if (language == null) return false;
        if (translations.containsKey(language) || !context.getGlobalTranslations(language).isEmpty()) {
            return true;
        }
        return this.getAvailableLanguages().stream().anyMatch(language::isCompatibleWith);
    }

    private String getTranslationFromMap(Map<String, String> translations, String key) {
        return translations != null ? translations.get(key) : null;
    }

    private String findTranslation(Language language, String key) {
        String result = this.getTranslationFromMap(translations.get(language), key);
        if (result != null) {
            return result;
        }

        result = this.getTranslationFromMap(context.getGlobalTranslations(language), key);
        if (result != null) {
            return result;
        }

        for (Map.Entry<Language, Map<String, String>> entry : translations.entrySet()) {
            if (language.isCompatibleWith(entry.getKey())) {
                result = this.getTranslationFromMap(entry.getValue(), key);
                if (result != null) {
                    return result;
                }
            }
        }

        for (Language availableLang : this.getAvailableLanguages()) {
            if (language.isCompatibleWith(availableLang)) {
                result = this.getTranslationFromMap(context.getGlobalTranslations(availableLang), key);
                if (result != null) {
                    return result;
                }
            }
        }

        return null;
    }

    protected String applyParameters(String text, TrParameters parameters) {
        if (text == null) return null;

        String result = text;

        if (parameters instanceof SimpleTrParameters) {
            TrParameterFormatter simpleFormatter = formatters.get(SimpleTrParameters.class);
            if (simpleFormatter != null) {
                result = simpleFormatter.format(result, parameters);
            }
        }

        KeyedTrParameters keyedParams;

        if (parameters instanceof KeyedTrParameters keyedTrParameters) {
            KeyedTrParameters merged = new KeyedTrParameters(context.getGlobalParameters());
            merged = merged.merge(keyedTrParameters);
            keyedParams = merged;
        } else {
            keyedParams = new KeyedTrParameters(context.getGlobalParameters());
        }

        if (keyedParams != null && !keyedParams.getParameters().isEmpty()) {
            TrParameterFormatter keyedFormatter = formatters.get(KeyedTrParameters.class);
            if (keyedFormatter != null) {
                result = keyedFormatter.format(result, keyedParams);
            }
        }

        return result;
    }

    @Override
    public Language getDefaultLanguage() {
        return defaultLanguage;
    }

    @Override
    public void setDefaultLanguage(Language language) {
        this.defaultLanguage = language;
    }

    @Override
    public void setLanguageStrategy(LanguageStrategy languageStrategy) {
        this.languageStrategy = languageStrategy;
    }

    @Override
    public void setFallbackStrategy(FallbackStrategy fallbackStrategy) {
        this.fallbackStrategy = fallbackStrategy;
    }

    @Override
    public void addTranslation(Language language, String key, String value) {
        if (language == null || key == null || value == null) {
            return;
        }
        translations.computeIfAbsent(language, k -> new HashMap<>()).put(key, value);
    }

    @Override
    public <T extends TrParameters> void setParameterFormatter(Class<T> parameterType, TrParameterFormatter formatter) {
        if (parameterType != null && formatter != null) {
            formatters.put(parameterType, formatter);
        }
    }

    @Override
    public Set<Language> getAvailableLanguages() {
        Set<Language> languages = new HashSet<>(translations.keySet());

        for (Language lang : translations.keySet()) {
            if (!context.getGlobalTranslations(lang).isEmpty()) {
                languages.add(lang);
            }
        }

        return languages;
    }
}
