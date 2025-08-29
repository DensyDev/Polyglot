package com.luminiadev.polyglot.core.provider;

import com.luminiadev.polyglot.api.language.Language;
import com.luminiadev.polyglot.api.provider.TranslationProvider;

import java.util.Map;
import java.util.Set;

/**
 * Empty translation provider.
 */
public final class EmptyProvider implements TranslationProvider {

    @Override
    public Map<Language, Map<String, String>> getTranslations() {
        return Map.of();
    }

    @Override
    public Set<Language> getSupportedLanguages() {
        return Set.of();
    }
}
