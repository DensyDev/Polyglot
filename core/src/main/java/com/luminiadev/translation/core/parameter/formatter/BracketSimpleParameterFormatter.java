package com.luminiadev.translation.core.parameter.formatter;

import com.luminiadev.translation.api.parameter.TrParameters;
import com.luminiadev.translation.api.parameter.formatter.TrParameterFormatter;
import com.luminiadev.translation.core.parameter.SimpleTrParameters;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Formatter for simple parameters in the format [0], [1], [2]...
 */
public class BracketSimpleParameterFormatter implements TrParameterFormatter {
    private static final Pattern PATTERN = Pattern.compile("\\[(\\d+)]");

    @Override
    public String format(String text, TrParameters parameters) {
        if (!(parameters instanceof SimpleTrParameters simpleParams)) {
            return text;
        }

        Object[] params = simpleParams.getParameters();
        Matcher matcher = PATTERN.matcher(text);

        StringBuilder result = new StringBuilder();
        while (matcher.find()) {
            int index = Integer.parseInt(matcher.group(1));
            String replacement = "";
            if (index >= 0 && index < params.length) {
                replacement = String.valueOf(params[index]);
            }
            matcher.appendReplacement(result, Matcher.quoteReplacement(replacement));
        }
        matcher.appendTail(result);

        return result.toString();
    }

    @Override
    public Class<? extends TrParameters> getSupportedParameterType() {
        return SimpleTrParameters.class;
    }
}

