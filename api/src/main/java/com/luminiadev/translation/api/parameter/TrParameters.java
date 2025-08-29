package com.luminiadev.translation.api.parameter;

import com.luminiadev.translation.api.parameter.formatter.TrParameterFormatter;

public interface TrParameters {

    String applyTo(String text, TrParameterFormatter formatter);
}
