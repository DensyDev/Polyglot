package com.luminiadev.translation.api;

import com.luminiadev.translation.api.language.Language;
import com.luminiadev.translation.api.parameter.TrParameters;
import com.luminiadev.translation.api.parameter.formatter.TrParameterFormatter;

import java.util.Set;
import java.util.function.Function;

public interface Translation {

    String translate(Language language, String key);

    String translate(Language language, String key, TrParameters parameters);

    String translate(Language language, String key, Object... parameters);

    Language getDefaultLanguage();

    void setDefaultLanguage(Language language);

    void setFallbackStrategy(Function<String, String> fallbackFunction);

    void addTranslation(Language language, String key, String value);

    <T extends TrParameters> void setParameterFormatter(Class<T> parameterType, TrParameterFormatter formatter);

    Set<Language> getAvailableLanguages();


}
