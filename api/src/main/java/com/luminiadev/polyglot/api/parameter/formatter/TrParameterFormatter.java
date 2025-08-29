package com.luminiadev.polyglot.api.parameter.formatter;

import com.luminiadev.polyglot.api.parameter.TrParameters;

/**
 * Parameter formatter interface.
 */
public interface TrParameterFormatter {

    /**
     * Formats text by applying parameters.
     *
     * @param text the text to format
     * @param parameters the parameters to apply
     * @return formatted text
     */
    String format(String text, TrParameters parameters);

    /**
     * Gets the parameter type supported by this formatter.
     *
     * @return supported parameter type class
     */
    Class<? extends TrParameters> getSupportedParameterType();
}
