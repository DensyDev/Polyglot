package com.luminiadev.translation.api.language;

public interface LanguageStandard {

    Language parseLanguage(String languageString);

    String formatLanguage(Language language);

    boolean matches(String languageString);
}
