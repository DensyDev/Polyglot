package org.densy.polyglot.core;

import org.densy.polyglot.api.Translation;
import org.densy.polyglot.api.context.TranslationContext;
import org.densy.polyglot.api.formatter.TranslationFormatter;
import org.densy.polyglot.api.language.Language;
import org.densy.polyglot.api.parameter.TranslationParameters;
import org.densy.polyglot.api.provider.TranslationProvider;
import org.densy.polyglot.api.util.FallbackStrategy;
import org.densy.polyglot.api.util.LanguageStrategy;
import org.densy.polyglot.core.formatter.PatternArrayParameterFormatter;
import org.densy.polyglot.core.formatter.PatternKeyedParameterFormatter;
import org.densy.polyglot.core.parameter.ArrayTrParameters;
import org.jetbrains.annotations.UnmodifiableView;

import java.util.*;

/**
 * Base implementation of the translation.
 */
public class BaseTranslation implements Translation {

    private final TranslationContext context;
    private final Map<Language, Map<String, String>> translations;
    private final List<TranslationFormatter> formatters;

    private Language defaultLanguage;
    private LanguageStrategy languageStrategy;
    private FallbackStrategy fallbackStrategy;

    public BaseTranslation(TranslationContext context, TranslationProvider provider) {
        this.context = context;
        this.translations = new HashMap<>();
        this.formatters = new ArrayList<>();

        // Order is important: the array parameter formatter
        // must be before the keyed parameter formatter.
        this.addFormatter(new PatternArrayParameterFormatter());
        this.addFormatter(new PatternKeyedParameterFormatter(context));

        if (provider != null) {
            translations.putAll(provider.getTranslations());
        }

        this.defaultLanguage = context.getDefaultLanguage();
    }

    @Override
    public String translate(Language language, String key) {
        return translate(language, key, (TranslationParameters) null);
    }

    @Override
    public String translate(Language language, String key, TranslationParameters parameters) {
        Language targetLanguage = resolveLanguage(language);
        String translation = findTranslation(targetLanguage, key);

        if (translation == null) {
            translation = fallbackStrategy != null ? fallbackStrategy.get(key) : key;
        }

        return applyParameters(translation, parameters);
    }

    @Override
    public String translate(Language language, String key, Object... parameters) {
        return translate(language, key, new ArrayTrParameters(parameters));
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

    protected String applyParameters(String text, TranslationParameters parameters) {
        if (text == null) return null;

        String result = text;
        for (TranslationFormatter formatter : formatters) {
            result = formatter.format(result, this, parameters);
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
    public @UnmodifiableView Map<Language, Map<String, String>> getTranslations() {
        return Collections.unmodifiableMap(translations);
    }

    @Override
    public void addTranslation(Language language, String key, String value) {
        if (language == null || key == null || value == null) {
            return;
        }
        this.translations.computeIfAbsent(language, k -> new HashMap<>()).put(key, value);
    }

    @Override
    public void addTranslations(Language language, Map<String, String> translations) {
        if (language == null || translations == null) {
            return;
        }
        this.translations.computeIfAbsent(language, k -> new HashMap<>()).putAll(translations);
    }

    @Override
    public void removeTranslation(Language language, String key) {
        if (language == null || key == null) {
            return;
        }
        if (!translations.containsKey(language)) {
            return;
        }
        translations.get(language).remove(key);
        // remove empty language map
        if  (translations.get(language).isEmpty()) {
            translations.remove(language);
        }
    }

    @Override
    public @UnmodifiableView List<TranslationFormatter> getFormatters() {
        return Collections.unmodifiableList(formatters);
    }

    @Override
    public void addFormatter(TranslationFormatter formatter) {
        formatters.add(formatter);
    }

    @Override
    public void removeFormatter(TranslationFormatter formatter) {
        formatters.remove(formatter);
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
