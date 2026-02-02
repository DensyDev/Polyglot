package org.densy.polyglot.core.provider;

import org.densy.polyglot.api.language.Language;
import org.densy.polyglot.api.language.LanguageStandard;
import org.densy.polyglot.api.provider.TranslationProvider;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

/**
 * Properties file translation provider.
 */
public class PropertiesFileProvider implements TranslationProvider {
    public static final Set<String> ALLOWED_EXTENSIONS = Set.of("properties", "props", "ini", "cfg", "conf", "env", "lang", "txt");

    private final File folder;
    private final LanguageStandard languageStandard;
    private final Map<Language, Map<String, String>> translations;
    private final Set<String> allowedExtensions;

    public PropertiesFileProvider(File folder, LanguageStandard languageStandard) {
        this(folder, languageStandard, ALLOWED_EXTENSIONS);
    }

    public PropertiesFileProvider(File folder, LanguageStandard languageStandard, Set<String> allowedExtensions) {
        this.folder = folder;
        this.languageStandard = languageStandard;
        this.allowedExtensions = new HashSet<>(allowedExtensions);
        this.translations = new HashMap<>();
        this.loadTranslationsFromFolder();
    }

    private void loadTranslationsFromFolder() {
        if (!folder.exists() || !folder.isDirectory()) {
            return;
        }

        File[] propertiesFiles = folder.listFiles((dir, name) -> {
            String lowerName = name.toLowerCase();
            int dotIndex = lowerName.lastIndexOf('.');
            if (dotIndex == -1) {
                return false;
            }
            String extension = lowerName.substring(dotIndex + 1);
            return allowedExtensions.contains(extension);
        });

        if (propertiesFiles == null) {
            return;
        }

        for (File file : propertiesFiles) {
            try {
                String fileName = file.getName();
                String languageString = fileName.substring(0, fileName.lastIndexOf('.'));

                if (languageStandard.matches(languageString)) {
                    Language language = languageStandard.parseLanguage(languageString);
                    Properties properties = new Properties();

                    try (InputStreamReader reader = new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8)) {
                        properties.load(reader);
                    }

                    Map<String, String> translationMap = new HashMap<>();
                    for (String key : properties.stringPropertyNames()) {
                        translationMap.put(key, properties.getProperty(key));
                    }

                    this.translations.put(language, translationMap);
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