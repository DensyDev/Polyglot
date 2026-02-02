package org.densy.polyglot.core.util;

import lombok.experimental.UtilityClass;

import java.util.HashMap;
import java.util.Map;

/**
 * General provider utility.
 */
@UtilityClass
public class ProviderUtils {

    /**
     * Flattens a nested map into a single-level map.
     *
     * @param map source map
     * @param prefix key prefix
     * @return flattened map
     */
    @SuppressWarnings("unchecked")
    public Map<String, String> flattenMap(Map<String, Object> map, String prefix) {
        Map<String, String> flattened = new HashMap<>();

        for (Map.Entry<String, Object> entry : map.entrySet()) {
            String key = prefix.isEmpty() ? entry.getKey() : prefix + "." + entry.getKey();
            Object value = entry.getValue();

            if (value instanceof Map) {
                flattened.putAll(flattenMap((Map<String, Object>) value, key));
            } else {
                flattened.put(key, String.valueOf(value));
            }
        }

        return flattened;
    }
}
