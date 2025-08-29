package com.luminiadev.translation.core.parameter;

import com.luminiadev.translation.api.parameter.TrParameters;
import com.luminiadev.translation.api.parameter.formatter.TrParameterFormatter;
import lombok.Getter;

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
