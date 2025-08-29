package com.luminiadev.translation.api.provider;

import com.luminiadev.translation.api.language.Language;

import java.util.Map;
import java.util.Set;

public interface TranslationProvider {

    Map<Language, Map<String, String>> getTranslations();

    Set<Language> getSupportedLanguages();
}
