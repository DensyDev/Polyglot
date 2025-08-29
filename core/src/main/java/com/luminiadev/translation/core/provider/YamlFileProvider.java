package com.luminiadev.translation.core.provider;

import com.luminiadev.translation.api.language.Language;
import com.luminiadev.translation.api.language.LanguageStandard;
import com.luminiadev.translation.api.provider.TranslationProvider;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

/**
 * YAML file translation provider.
 */
public class YamlFileProvider implements TranslationProvider {
    private final File folder;
    private final LanguageStandard languageStandard;
    private final Map<Language, Map<String, String>> translations;

    public YamlFileProvider(File folder, LanguageStandard languageStandard) {
        this.folder = folder;
        this.languageStandard = languageStandard;
        this.translations = new HashMap<>();
        this.loadTranslationsFromFolder();
    }

    private void loadTranslationsFromFolder() {
        if (!folder.exists() || !folder.isDirectory()) {
            return;
        }

        File[] yamlFiles = folder.listFiles((dir, name) -> name.toLowerCase().endsWith(".yml") || name.toLowerCase().endsWith(".yaml"));
        if (yamlFiles == null) {
            return;
        }

        Yaml yaml = new Yaml();

        for (File file : yamlFiles) {
            try {
                String fileName = file.getName();
                String languageString = fileName.substring(0, fileName.lastIndexOf('.'));

                if (languageStandard.matches(languageString)) {
                    Language language = languageStandard.parseLanguage(languageString);
                    Map<String, Object> data = yaml.load(new FileInputStream(file));
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
