package org.densy.polyglot.core.parameter;

import lombok.Getter;
import org.densy.polyglot.api.parameter.TranslationParameters;

/**
 * Simple indexed translation parameters.
 */
@Getter
public class ArrayTranslationParameters implements TranslationParameters {

    private final Object[] parameters;

    public ArrayTranslationParameters(Object... parameters) {
        this.parameters = parameters;
    }
}
