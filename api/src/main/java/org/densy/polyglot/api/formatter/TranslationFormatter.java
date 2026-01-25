package org.densy.polyglot.api.formatter;

import org.densy.polyglot.api.Translation;
import org.densy.polyglot.api.parameter.TranslationParameters;

/**
 * Translation post formatter interface.
 */
public interface TranslationFormatter {

    /**
     * Formats translation text and by applies parameters if it needs.
     *
     * @param text        the text to format
     * @param translation the translation object
     * @param parameters  the translation parameters
     * @return formatted text
     */
    String format(String text, Translation translation, TranslationParameters parameters);
}
