package org.densy.polyglot.api.formatter;

import org.jetbrains.annotations.UnmodifiableView;

import java.util.List;

public interface TranslationFormatterAware {

    @UnmodifiableView
    List<TranslationFormatter> getFormatters();

    void addFormatter(TranslationFormatter formatter);

    void removeFormatter(TranslationFormatter formatter);
}
