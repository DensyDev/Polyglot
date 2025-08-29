package com.luminiadev.translation.core.parameter;

import com.luminiadev.translation.api.parameter.KeyedTrParameters;
import com.luminiadev.translation.api.parameter.formatter.TrParameterFormatter;

import java.util.HashMap;
import java.util.Map;

public class KeyedParameters implements KeyedTrParameters {

    private final Map<String, Object> parameters;

    public KeyedParameters() {
        this.parameters = new HashMap<>();
    }

    public KeyedParameters(Map<String, Object> parameters) {
        this.parameters = new HashMap<>(parameters != null ? parameters : Map.of());
    }

    public KeyedParameters put(String key, Object value) {
        parameters.put(key, value);
        return this;
    }

    @Override
    public Map<String, Object> getParameters() {
        return parameters;
    }

    @Override
    public String applyTo(String text, TrParameterFormatter formatter) {
        return formatter.format(text, this);
    }

    public KeyedParameters merge(KeyedTrParameters other) {
        if (other == null) return this;

        Map<String, Object> merged = new HashMap<>(other.getParameters());
        merged.putAll(this.parameters);
        return new KeyedParameters(merged);
    }
}
