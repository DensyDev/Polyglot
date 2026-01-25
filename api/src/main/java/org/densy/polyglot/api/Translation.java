package org.densy.polyglot.api;

import org.densy.polyglot.api.formatter.TranslationFormatterAware;
import org.densy.polyglot.api.language.Language;
import org.densy.polyglot.api.parameter.TranslationParameters;
import org.densy.polyglot.api.util.FallbackStrategy;
import org.densy.polyglot.api.util.LanguageStrategy;

import java.util.Set;

public interface Translation extends TranslationFormatterAware, TranslationsAware {

    String translate(Language language, String key);

    String translate(Language language, String key, TranslationParameters parameters);

    String translate(Language language, String key, Object... parameters);

    Language getDefaultLanguage();

    void setDefaultLanguage(Language language);

    void setLanguageStrategy(LanguageStrategy languageStrategy);

    void setFallbackStrategy(FallbackStrategy fallbackStrategy);

    Set<Language> getAvailableLanguages();
}
