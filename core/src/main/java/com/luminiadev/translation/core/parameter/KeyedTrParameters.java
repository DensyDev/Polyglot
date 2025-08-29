package com.luminiadev.translation.core.parameter;

import com.luminiadev.translation.api.parameter.TrParameters;
import com.luminiadev.translation.api.parameter.formatter.TrParameterFormatter;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
public class KeyedTrParameters implements TrParameters {

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

    @Override
    public String applyTo(String text, TrParameterFormatter formatter) {
        return formatter.format(text, this);
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
