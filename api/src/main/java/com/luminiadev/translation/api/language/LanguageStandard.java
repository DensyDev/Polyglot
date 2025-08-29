package com.luminiadev.translation.api.language;

public interface LanguageStandard {

    String getName();

    Language parseLanguage(String languageString);

    String formatLanguage(Language language);

    boolean matches(String languageString);
}
