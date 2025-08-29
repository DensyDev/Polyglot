package com.luminiadev.translation.core.provider;

import com.luminiadev.translation.api.language.Language;
import com.luminiadev.translation.api.provider.TranslationProvider;

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
