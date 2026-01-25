package org.densy.polyglot.core.formatter;

import org.densy.polyglot.api.Translation;
import org.densy.polyglot.api.parameter.TranslationParameters;
import org.densy.polyglot.api.formatter.TranslationFormatter;
import org.densy.polyglot.core.parameter.ArrayTrParameters;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Formatter for simple parameters in the format {0}, {1}, {2}...
 * Supports escaping: \{0} - not replaced, \\{0} - backslash before replacement
 */
public class PatternArrayParameterFormatter implements TranslationFormatter {
    public static final Pattern DEFAULT_PATTERN = Pattern.compile("(\\\\*)\\{(\\d+)}");

    private final Pattern pattern;

    public PatternArrayParameterFormatter() {
        this(DEFAULT_PATTERN);
    }

    public PatternArrayParameterFormatter(Pattern pattern) {
        this.pattern = pattern;
    }

    @Override
    public String format(String text, Translation translation, TranslationParameters parameters) {
        if (!(parameters instanceof ArrayTrParameters simpleParams)) {
            return text;
        }

        Object[] params = simpleParams.getParameters();
        Matcher matcher = pattern.matcher(text);

        StringBuilder result = new StringBuilder();
        while (matcher.find()) {
            String fullMatch = matcher.group(0);  // all text found
            String backslashes = matcher.group(1);
            int index = Integer.parseInt(matcher.group(2));
            int backslashCount = backslashes.length();

            int pairs = backslashCount / 2;
            boolean isEscaped = backslashCount % 2 == 1;

            String replacement;
            if (isEscaped) {
                // escape, remove one slash from the beginning
                replacement = "\\".repeat(pairs) + fullMatch.substring(backslashCount);
            } else if (index < 0 || index >= params.length) {
                // index is out of range, so leave it as it is
                replacement = fullMatch;
            } else {
                // replace the parameter
                replacement = "\\".repeat(pairs) + params[index];
            }

            matcher.appendReplacement(result, Matcher.quoteReplacement(replacement));
        }
        matcher.appendTail(result);

        return result.toString();
    }
}