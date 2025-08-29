package com.luminiadev.translation.api.context;

import com.luminiadev.translation.api.Translation;
import com.luminiadev.translation.api.language.Language;
import com.luminiadev.translation.api.language.LanguageStandard;
import com.luminiadev.translation.api.provider.TranslationProvider;

import java.util.Map;

public interface TranslationContext {

    Translation createTranslation(TranslationProvider provider);

    Translation createTranslation();

    Map<String, Object> getGlobalParameters();

    void addGlobalParameters(String key, Object value);

    Map<String, String> getGlobalTranslations(Language language);

    void addGlobalTranslations(Language language, Map<String, String> translations);

    void addGlobalTranslation(Language language, String key, String translation);

    LanguageStandard getLanguageStandard();

    void setLanguageStandard(LanguageStandard standard);
}
