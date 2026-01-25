package org.densy.polyglot.api.util;

import org.densy.polyglot.api.language.Language;

import java.util.HashMap;
import java.util.Map;

public final class LanguageMappingBuilder {

    private final Map<Language, Language> mappings = new HashMap<>();

    public LanguageMappingBuilder put(Language missingLanguage, Language mappedLanguage) {
        this.mappings.put(missingLanguage, mappedLanguage);
        return this;
    }

    public LanguageStrategy build() {
        return LanguageStrategy.mappingsOf(mappings);
    }
}
