package com.luminiadev.polyglot.core.parameter.formatter;

import com.luminiadev.polyglot.api.parameter.TrParameters;
import com.luminiadev.polyglot.api.parameter.formatter.TrParameterFormatter;
import com.luminiadev.polyglot.core.parameter.KeyedTrParameters;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Formatter for named parameters in the format {key}
 */
public class BraceKeyedParameterFormatter implements TrParameterFormatter {
    private static final Pattern PATTERN = Pattern.compile("\\{([^}]+)}");

    @Override
    public String format(String text, TrParameters parameters) {
        if (!(parameters instanceof KeyedTrParameters keyedParams)) {
            return text;
        }

        Map<String, Object> params = keyedParams.getParameters();
        Matcher matcher = PATTERN.matcher(text);

        StringBuilder result = new StringBuilder();
        while (matcher.find()) {
            String key = matcher.group(1);
            String replacement = "";
            if (params.containsKey(key)) {
                replacement = String.valueOf(params.get(key));
            }
            matcher.appendReplacement(result, Matcher.quoteReplacement(replacement));
        }
        matcher.appendTail(result);

        return result.toString();
    }

    @Override
    public Class<? extends TrParameters> getSupportedParameterType() {
        return KeyedTrParameters.class;
    }
}

