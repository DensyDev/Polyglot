package org.densy.polyglot.core.provider;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.densy.polyglot.api.language.Language;
import org.densy.polyglot.api.language.LanguageStandard;
import org.densy.polyglot.api.provider.TranslationProvider;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Json file translation provider.
 */
public class JsonFileProvider implements TranslationProvider {
    private final File folder;
    private final LanguageStandard languageStandard;
    private final Map<Language, Map<String, String>> translations;

    public JsonFileProvider(File folder, LanguageStandard languageStandard) {
        this.folder = folder;
        this.languageStandard = languageStandard;
        this.translations = new HashMap<>();
        this.loadTranslationsFromFolder();
    }

    private void loadTranslationsFromFolder() {
        if (!folder.exists() || !folder.isDirectory()) {
            return;
        }

        File[] jsonFiles = folder.listFiles((dir, name) -> name.toLowerCase().endsWith(".json"));
        if (jsonFiles == null) {
            return;
        }

        Gson gson = new Gson();

        for (File file : jsonFiles) {
            try {
                String fileName = file.getName();
                String languageString = fileName.substring(0, fileName.lastIndexOf('.'));

                if (languageStandard.matches(languageString)) {
                    Language language = languageStandard.parseLanguage(languageString);
                    Map<String, Object> data = gson.fromJson(new FileReader(file), new TypeToken<Map<String, Object>>() {
                    }.getType());
                    Map<String, String> flattenedTranslations = flattenMap(data, "");
                    translations.put(language, flattenedTranslations);
                }
            } catch (IOException e) {
                throw new RuntimeException("Error loading translation file: " + file.getName(), e);
            } catch (Exception e) {
                throw new RuntimeException("Error parsing translation file: " + file.getName(), e);
            }
        }
    }

    @SuppressWarnings("unchecked")
    private Map<String, String> flattenMap(Map<String, Object> map, String prefix) {
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

    @Override
    public Map<Language, Map<String, String>> getTranslations() {
        return new HashMap<>(translations);
    }

    @Override
    public Set<Language> getSupportedLanguages() {
        return new HashSet<>(translations.keySet());
    }
}
