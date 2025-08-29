package com.luminiadev.polyglot.common.language.converter;

import com.luminiadev.polyglot.api.language.Language;
import com.luminiadev.polyglot.api.language.converter.LanguageConverter;

import java.util.HashMap;
import java.util.Map;

/**
 * Abstract base class for language converters.
 */
public class AbstractLanguageConverter implements LanguageConverter {
    protected final Map<Language, Language> sourceToTarget = new HashMap<>();
    protected final Map<Language, Language> targetToSource = new HashMap<>();

    @Override
    public Language toTarget(Language source) {
        return sourceToTarget.get(source);
    }

    @Override
    public Language toSource(Language target) {
        return targetToSource.get(target);
    }

    /**
     * Adds mapping from the source language to the target language.
     *
     * @param source Source language
     * @param target Target language
     */
    protected void addMapping(Language source, Language target) {
        sourceToTarget.putIfAbsent(source, target);
        targetToSource.putIfAbsent(target, source);
    }
}
