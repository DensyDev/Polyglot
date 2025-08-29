package com.luminiadev.polyglot.api.language.converter;

import com.luminiadev.polyglot.api.language.Language;

/**
 * Interface for converting between two language formats.
 */
public interface LanguageConverter {

    /**
     * Converts language from source to target.
     *
     * @param source Source language
     * @return Target language
     */
    Language toTarget(Language source);

    /**
     * Converts language from target to source.
     *
     * @param target Target language
     * @return Source language
     */
    Language toSource(Language target);
}
