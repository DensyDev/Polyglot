package org.densy.polyglot.api.formatter;

import org.densy.polyglot.api.formatter.context.TranslationFormatContext;

/**
 * Translation post formatter interface.
 */
public interface TranslationFormatter {

    /**
     * Formats translation text and by applies parameters if it needs.
     *
     * @param text    the text to format
     * @param context the translation format context
     * @return formatted text
     */
    String format(String text, TranslationFormatContext context);
}
