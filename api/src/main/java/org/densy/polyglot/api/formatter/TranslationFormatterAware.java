package org.densy.polyglot.api.formatter;

import org.jetbrains.annotations.UnmodifiableView;

import java.util.List;

/**
 * Interface for managing translation formatters.
 */
public interface TranslationFormatterAware {

    /**
     * Gets the list of registered formatters.
     * Formatters are applied in the order they appear in this list.
     *
     * @return unmodifiable list of formatters
     */
    @UnmodifiableView
    List<TranslationFormatter> getFormatters();

    /**
     * Adds a formatter to the end of the formatter chain.
     *
     * @param formatter the formatter to add
     */
    void addFormatter(TranslationFormatter formatter);

    /**
     * Removes a formatter from the formatter chain.
     *
     * @param formatter the formatter to remove
     */
    void removeFormatter(TranslationFormatter formatter);
}