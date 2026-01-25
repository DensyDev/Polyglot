package org.densy.polyglot.api;

import org.densy.polyglot.api.language.Language;
import org.densy.polyglot.api.parameter.TrParameters;
import org.densy.polyglot.api.parameter.formatter.TrParameterFormatter;
import org.densy.polyglot.api.util.FallbackStrategy;
import org.densy.polyglot.api.util.LanguageStrategy;

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
