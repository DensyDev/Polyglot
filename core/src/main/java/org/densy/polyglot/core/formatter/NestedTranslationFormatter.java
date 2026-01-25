package org.densy.polyglot.core.formatter;

import org.densy.polyglot.api.formatter.context.TranslationFormatContext;
import org.densy.polyglot.api.formatter.TranslationFormatter;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Formatter for nested translations in the format {translation-key}
 * Allows embedding other translations within a translation string.
 * <p>
 * Example: "Welcome message: {welcome.message}" will be replaced with the translation of "welcome.message"
 * <p>
 * Supports escaping: \{key} will not be replaced
 */
public class NestedTranslationFormatter implements TranslationFormatter {

    public static final Pattern DEFAULT_PATTERN = Pattern.compile("(\\\\*)\\{([^}]+)}");

    private final Pattern pattern;
    private final int maxDepth;

    public NestedTranslationFormatter() {
        this(DEFAULT_PATTERN);
    }

    public NestedTranslationFormatter(Pattern pattern) {
        this(pattern, 5);
    }

    public NestedTranslationFormatter(Pattern pattern, int maxDepth) {
        this.pattern = pattern;
        this.maxDepth = maxDepth;
    }

    @Override
    public String format(String text, TranslationFormatContext context) {
        return formatRecursive(text, context, 0);
    }

    private String formatRecursive(String text, TranslationFormatContext context, int depth) {
        // Protection against infinite recursion
        if (depth >= maxDepth) {
            return text;
        }

        Matcher matcher = pattern.matcher(text);
        StringBuilder result = new StringBuilder();

        while (matcher.find()) {
            String fullMatch = matcher.group(0);
            String backslashes = matcher.group(1);
            String key = matcher.group(2);
            int backslashCount = backslashes.length();

            boolean isEscaped = backslashCount % 2 == 1;

            String replacement;
            if (isEscaped) {
                // escaped, so we leave it as it is
                replacement = fullMatch;
            } else {
                String nestedTranslation = context.getTranslation().translate(context.getLanguage(), key, context.getParameters());

                if (nestedTranslation != null && !nestedTranslation.equals(key)) {
                    // recursively process nested translations
                    String processedNested = formatRecursive(nestedTranslation, context, depth + 1);
                    replacement = backslashes + processedNested;
                } else {
                    // no translation found, so we'll leave it as is.
                    replacement = fullMatch;
                }
            }

            matcher.appendReplacement(result, Matcher.quoteReplacement(replacement));
        }

        matcher.appendTail(result);
        return result.toString();
    }
}