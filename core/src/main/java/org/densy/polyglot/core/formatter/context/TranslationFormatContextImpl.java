package org.densy.polyglot.core.formatter.context;

import lombok.Data;
import org.densy.polyglot.api.Translation;
import org.densy.polyglot.api.formatter.context.TranslationFormatContext;
import org.densy.polyglot.api.language.Language;
import org.densy.polyglot.api.parameter.TranslationParameters;

/**
 * Implementation of the translation formatting context.
 */
@Data
public class TranslationFormatContextImpl implements TranslationFormatContext {
    private final String key;
    private final Language language;
    private final Translation translation;
    private final TranslationParameters parameters;
}
