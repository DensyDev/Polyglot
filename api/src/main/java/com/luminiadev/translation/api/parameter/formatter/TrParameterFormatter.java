package com.luminiadev.translation.api.parameter.formatter;

import com.luminiadev.translation.api.parameter.TrParameters;

public interface TrParameterFormatter {

    String format(String text, TrParameters parameters);

    Class<? extends TrParameters> getSupportedParameterType();
}
