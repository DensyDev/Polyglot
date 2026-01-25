package org.densy.polyglot.api;

import org.densy.polyglot.api.language.Language;
import org.jetbrains.annotations.UnmodifiableView;

import java.util.Map;

public interface TranslationsAware {

    @UnmodifiableView
    Map<Language, Map<String, String>> getTranslations();

    void addTranslation(Language language, String key, String value);

    void addTranslations(Language language, Map<String, String> translations);

    void removeTranslation(Language language, String key);
}
