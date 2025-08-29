package com.luminiadev.translation.core.parameter.formatter;

import com.luminiadev.translation.api.parameter.TrParameters;
import com.luminiadev.translation.api.parameter.formatter.TrParameterFormatter;
import com.luminiadev.translation.core.parameter.KeyedParameters;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BraceKeyedParameterFormatter implements TrParameterFormatter {
    private static final Pattern PATTERN = Pattern.compile("\\{([^}]+)}");

    @Override
    public String format(String text, TrParameters parameters) {
        if (!(parameters instanceof KeyedParameters keyedParams)) {
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
        return KeyedParameters.class;
    }
}

