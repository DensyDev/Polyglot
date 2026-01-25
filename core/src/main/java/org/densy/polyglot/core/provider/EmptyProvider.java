package org.densy.polyglot.core.provider;

import org.densy.polyglot.api.language.Language;
import org.densy.polyglot.api.provider.TranslationProvider;

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
