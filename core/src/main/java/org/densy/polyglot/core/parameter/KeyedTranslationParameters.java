package org.densy.polyglot.core.parameter;

import lombok.Getter;
import org.densy.polyglot.api.parameter.TranslationParameters;

import java.util.HashMap;
import java.util.Map;

/**
 * Key-value translation parameters.
 */
@Getter
public class KeyedTranslationParameters implements TranslationParameters {

    private final Map<String, Object> parameters;

    public KeyedTranslationParameters() {
        this.parameters = new HashMap<>();
    }

    public KeyedTranslationParameters(Map<String, Object> parameters) {
        this.parameters = new HashMap<>(parameters != null ? parameters : Map.of());
    }

    /**
     * Sets the value of the parameter by key.
     *
     * @param key   parameter key
     * @param value parameter value
     * @return this object instance
     */
    public KeyedTranslationParameters put(String key, Object value) {
        parameters.put(key, value);
        return this;
    }

    /**
     * Merges the current KeyedTrParameters with another KeyedTrParameters.
     *
     * @param other Another KeyedTrParameters
     * @return Merged KeyedTrParameters
     */
    public KeyedTranslationParameters merge(KeyedTranslationParameters other) {
        if (other == null) return this;

        Map<String, Object> merged = new HashMap<>(other.getParameters());
        merged.putAll(this.parameters);
        return new KeyedTranslationParameters(merged);
    }
}
