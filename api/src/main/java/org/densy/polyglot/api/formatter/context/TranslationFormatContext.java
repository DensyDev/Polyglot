package org.densy.polyglot.api.formatter.context;

import org.densy.polyglot.api.Translation;
import org.densy.polyglot.api.language.Language;
import org.densy.polyglot.api.parameter.TranslationParameters;

/**
 * Translation formatting context.
 */
public interface TranslationFormatContext {

    /**
     * Translation key.
     *
     * @return the translation key
     */
    String getKey();

    /**
     * Translation language.
     *
     * @return the Language object
     */
    Language getLanguage();

    /**
     * Translation itself.
     *
     * @return the Translation object
     */
    Translation getTranslation();

    /**
     * Translation parameters.
     *
     * @return the TranslationParameters object
     */
    TranslationParameters getParameters();
}
