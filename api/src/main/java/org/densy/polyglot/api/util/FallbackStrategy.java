package org.densy.polyglot.api.util;

/**
 * Fallback strategy interface. Defines how to resolve missing keys.
 */
@FunctionalInterface
public interface FallbackStrategy {

    /**
     * Resolves a value for the given key.
     *
     * @param key the key
     * @return resolved value
     */
    String get(String key);

    /**
     * Returns the key itself.
     *
     * @return strategy returning the key
     */
    static FallbackStrategy keyToKey() {
        return key -> key;
    }

    /**
     * Returns a fixed default value.
     *
     * @param defaultValue default value
     * @return strategy returning the default value
     */
    static FallbackStrategy defaultResult(String defaultValue) {
        return key -> defaultValue;
    }

    /**
     * Returns an empty string.
     *
     * @return strategy returning empty string
     */
    static FallbackStrategy emptyResult() {
        return key -> "";
    }

    /**
     * Returns the key with a prefix.
     *
     * @param prefix prefix to add
     * @return strategy returning prefixed key
     */
    static FallbackStrategy prefix(String prefix) {
        return key -> prefix + key;
    }

    /**
     * Returns the key with a suffix.
     *
     * @param suffix suffix to add
     * @return strategy returning suffixed key
     */
    static FallbackStrategy suffix(String suffix) {
        return key -> key + suffix;
    }
}

