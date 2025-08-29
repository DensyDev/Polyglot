package com.luminiadev.translation.api.language;

public interface Language {

    String getLanguageCode();

    String getCountryCode();

    String getFullCode();

    boolean isCompatibleWith(Language other);
}
