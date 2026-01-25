package org.densy.polyglot.core.formatter;

import org.densy.polyglot.api.context.TranslationContext;
import org.densy.polyglot.api.formatter.context.TranslationFormatContext;
import org.densy.polyglot.api.formatter.TranslationFormatter;
import org.densy.polyglot.core.parameter.KeyedTranslationParameters;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Formatter for named parameters in the format {key}
 */
public class PatternKeyedParameterFormatter implements TranslationFormatter {
    public static final Pattern DEFAULT_PATTERN = Pattern.compile("(\\\\*)\\{([^}]+)}");

    private final TranslationContext context;
    private final Pattern pattern;

    public PatternKeyedParameterFormatter(TranslationContext context) {
        this(context, DEFAULT_PATTERN);
    }

    public PatternKeyedParameterFormatter(TranslationContext context, Pattern pattern) {
        this.context = context;
        this.pattern = pattern;
    }

    @Override
    public String format(String text, TranslationFormatContext formatContext) {
        KeyedTranslationParameters keyedParams;
        if (formatContext.getParameters() instanceof KeyedTranslationParameters keyedTrParameters) {
            KeyedTranslationParameters merged = new KeyedTranslationParameters(context.getGlobalParameters());
            merged = merged.merge(keyedTrParameters);
            keyedParams = merged;
        } else {
            keyedParams = new KeyedTranslationParameters(context.getGlobalParameters());
        }

        Map<String, Object> params = keyedParams.getParameters();
        Matcher matcher = pattern.matcher(text);
        StringBuilder result = new StringBuilder();

        while (matcher.find()) {
            String fullMatch = matcher.group(0);
            String backslashes = matcher.group(1);
            String key = matcher.group(2);
            int backslashCount = backslashes.length();

            boolean isEscaped = backslashCount % 2 == 1;

            String replacement;
            if (isEscaped || !params.containsKey(key)) {
                // if escaped or parameter not found, so leave it as it is
                replacement = fullMatch;
            } else {
                // replace the parameter
                replacement = backslashes + params.get(key);
            }

            matcher.appendReplacement(result, Matcher.quoteReplacement(replacement));
        }

        matcher.appendTail(result);
        return result.toString();
    }
}