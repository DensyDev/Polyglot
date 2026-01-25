package org.densy.polyglot.core.formatter;

import org.densy.polyglot.api.Translation;
import org.densy.polyglot.api.context.TranslationContext;
import org.densy.polyglot.api.formatter.TranslationFormatter;
import org.densy.polyglot.api.parameter.TranslationParameters;
import org.densy.polyglot.core.parameter.KeyedTrParameters;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Formatter for named parameters in the format {key}
 * Supports escaping: \{key} - not replaced, \\{key} - backslash before replacement
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
    public String format(String text, Translation translation, TranslationParameters parameters) {
        KeyedTrParameters keyedParams;
        if (parameters instanceof KeyedTrParameters keyedTrParameters) {
            KeyedTrParameters merged = new KeyedTrParameters(context.getGlobalParameters());
            merged = merged.merge(keyedTrParameters);
            keyedParams = merged;
        } else {
            keyedParams = new KeyedTrParameters(context.getGlobalParameters());
        }

        Map<String, Object> params = keyedParams.getParameters();
        Matcher matcher = pattern.matcher(text);
        StringBuilder result = new StringBuilder();

        while (matcher.find()) {
            String fullMatch = matcher.group(0); // all text found
            String backslashes = matcher.group(1);
            String key = matcher.group(2);
            int backslashCount = backslashes.length();

            int pairs = backslashCount / 2;
            boolean isEscaped = backslashCount % 2 == 1;

            String replacement;
            if (isEscaped) {
                // escape, remove one slash from the beginning
                replacement = "\\".repeat(pairs) + fullMatch.substring(backslashCount);
            } else if (!params.containsKey(key)) {
                // parameter not found, so leave it as it is
                replacement = fullMatch;
            } else {
                // replace the parameter
                replacement = "\\".repeat(pairs) + params.get(key);
            }

            matcher.appendReplacement(result, Matcher.quoteReplacement(replacement));
        }

        matcher.appendTail(result);
        return result.toString();
    }
}