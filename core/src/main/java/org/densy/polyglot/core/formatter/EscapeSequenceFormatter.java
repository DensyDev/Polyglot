package org.densy.polyglot.core.formatter;

import org.densy.polyglot.api.Translation;
import org.densy.polyglot.api.formatter.TranslationFormatter;
import org.densy.polyglot.api.parameter.TranslationParameters;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Post-processor formatter that removes escape sequences.
 * This should be the LAST formatter in the chain.
 * <p>
 * Converts:
 * - \{param} -> {param}
 * - \\{param} -> \{param}
 * - \\\{param} -> \{param}
 * - \\\\{param} -> \\{param}
 * <p>
 * In general: removes one backslash from each pair/sequence of backslashes
 */
public class EscapeSequenceFormatter implements TranslationFormatter {
    private static final Pattern ESCAPE_PATTERN = Pattern.compile("\\\\+");

    @Override
    public String format(String text, Translation translation, TranslationParameters parameters) {
        Matcher matcher = ESCAPE_PATTERN.matcher(text);
        StringBuilder result = new StringBuilder();

        while (matcher.find()) {
            String backslashes = matcher.group(0);
            int count = backslashes.length();

            // Remove one slash from each pair; if the number is odd, round down.
            // \\ -> \
            // \\\ -> \
            // \\\\ -> \\
            int resultCount = count / 2;
            String replacement = "\\".repeat(resultCount);

            matcher.appendReplacement(result, Matcher.quoteReplacement(replacement));
        }

        matcher.appendTail(result);
        return result.toString();
    }
}