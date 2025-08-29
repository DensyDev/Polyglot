package com.luminiadev.translation.core.parameter;

import com.luminiadev.translation.api.parameter.SimpleTrParameters;
import com.luminiadev.translation.api.parameter.formatter.TrParameterFormatter;

public class SimpleParameters implements SimpleTrParameters {

    private final Object[] parameters;

    public SimpleParameters(Object... parameters) {
        this.parameters = parameters;
    }

    @Override
    public Object[] getParameters() {
        return parameters;
    }

    @Override
    public String applyTo(String text, TrParameterFormatter formatter) {
        return formatter.format(text, this);
    }
}
