package com.luminiadev.polyglot.core.parameter;

import com.luminiadev.polyglot.api.parameter.TrParameters;
import com.luminiadev.polyglot.api.parameter.formatter.TrParameterFormatter;
import lombok.Getter;

/**
 * Simple indexed translation parameters.
 */
@Getter
public class SimpleTrParameters implements TrParameters {

    private final Object[] parameters;

    public SimpleTrParameters(Object... parameters) {
        this.parameters = parameters;
    }

    @Override
    public String applyTo(String text, TrParameterFormatter formatter) {
        return formatter.format(text, this);
    }
}
