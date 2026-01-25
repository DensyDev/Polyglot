package org.densy.polyglot.api.formatter;

import org.densy.polyglot.api.Translation;
import org.densy.polyglot.api.parameter.TranslationParameters;

/**
 * Parameter formatter interface.
 */
public interface TranslationFormatter {

    /**
     * Formats text by applying parameters.
     *
     * @param text        the text to format
     * @param translation the translation object
     * @param parameters  the parameters to apply
     * @return formatted text
     */
    String format(String text, Translation translation, TranslationParameters parameters);
}
