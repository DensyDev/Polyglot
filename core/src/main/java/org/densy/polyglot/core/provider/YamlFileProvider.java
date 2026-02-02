package org.densy.polyglot.core.provider;

import org.densy.polyglot.api.language.Language;
import org.densy.polyglot.api.language.LanguageStandard;
import org.densy.polyglot.api.provider.TranslationProvider;
import org.densy.polyglot.core.util.ProviderUtils;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

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
                    Map<String, Object> translations = yaml.load(new FileInputStream(file));
                    Map<String, String> flattenedTranslations = ProviderUtils.flattenMap(translations, "");
                    this.translations.put(language, flattenedTranslations);
                }
            } catch (IOException e) {
                throw new RuntimeException("Error loading translation file: " + file.getName(), e);
            } catch (Exception e) {
                throw new RuntimeException("Error parsing translation file: " + file.getName(), e);
            }
        }
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
