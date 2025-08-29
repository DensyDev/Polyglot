package com.luminiadev.translation.api.parameter;

import com.luminiadev.translation.api.parameter.formatter.TrParameterFormatter;

/**
 * Base interface for translation parameters.
 */
public interface TrParameters {

    /**
     * Applies parameters to text using the specified formatter.
     *
     * @param text the text to format
     * @param formatter the parameter formatter
     * @return formatted text
     */
    String applyTo(String text, TrParameterFormatter formatter);
}
