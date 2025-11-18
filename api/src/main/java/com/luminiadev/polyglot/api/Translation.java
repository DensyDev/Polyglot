package com.luminiadev.polyglot.api;

import com.luminiadev.polyglot.api.language.Language;
import com.luminiadev.polyglot.api.parameter.TrParameters;
import com.luminiadev.polyglot.api.parameter.formatter.TrParameterFormatter;
import com.luminiadev.polyglot.api.util.FallbackStrategy;
import com.luminiadev.polyglot.api.util.LanguageStrategy;

import java.util.Set;

public interface Translation {

    String translate(Language language, String key);

    String translate(Language language, String key, TrParameters parameters);

    String translate(Language language, String key, Object... parameters);

    Language getDefaultLanguage();

    void setDefaultLanguage(Language language);

    void setLanguageStrategy(LanguageStrategy languageStrategy);

    void setFallbackStrategy(FallbackStrategy fallbackStrategy);

    void addTranslation(Language language, String key, String value);

    <T extends TrParameters> void setParameterFormatter(Class<T> parameterType, TrParameterFormatter formatter);

    Set<Language> getAvailableLanguages();
}
