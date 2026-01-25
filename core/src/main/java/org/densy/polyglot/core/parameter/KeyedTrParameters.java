package org.densy.polyglot.core.parameter;

import lombok.Getter;
import org.densy.polyglot.api.parameter.TranslationParameters;

import java.util.HashMap;
import java.util.Map;

/**
 * Key-value translation parameters.
 */
@Getter
public class KeyedTrParameters implements TranslationParameters {

    private final Map<String, Object> parameters;

    public KeyedTrParameters() {
        this.parameters = new HashMap<>();
    }

    public KeyedTrParameters(Map<String, Object> parameters) {
        this.parameters = new HashMap<>(parameters != null ? parameters : Map.of());
    }

    public KeyedTrParameters put(String key, Object value) {
        parameters.put(key, value);
        return this;
    }

    /**
     * Merges the current KeyedTrParameters with another KeyedTrParameters.
     *
     * @param other Another KeyedTrParameters
     * @return Merged KeyedTrParameters
     */
    public KeyedTrParameters merge(KeyedTrParameters other) {
        if (other == null) return this;

        Map<String, Object> merged = new HashMap<>(other.getParameters());
        merged.putAll(this.parameters);
        return new KeyedTrParameters(merged);
    }
}
