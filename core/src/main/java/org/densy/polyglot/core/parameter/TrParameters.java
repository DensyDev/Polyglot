package org.densy.polyglot.core.parameter;

import lombok.experimental.UtilityClass;

import java.util.Map;

/**
 * A utility class containing basic parameters for translations.
 */
@UtilityClass
public class TrParameters {

    /**
     * Creates an array parameter.
     */
    public ArrayTranslationParameters array(Object... parameters) {
        return new ArrayTranslationParameters(parameters);
    }

    /**
     * Creates a keyed parameters from map.
     */
    public KeyedTranslationParameters keyed(Map<String, Object> parameters) {
        return new KeyedTranslationParameters(parameters);
    }

    /**
     * Creates an empty keyed parameters.
     */
    public KeyedTranslationParameters keyed() {
        return new KeyedTranslationParameters();
    }
}
