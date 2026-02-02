package org.densy.polyglot.core.provider;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.densy.polyglot.api.language.Language;
import org.densy.polyglot.api.language.LanguageStandard;
import org.densy.polyglot.api.provider.TranslationProvider;
import org.densy.polyglot.core.util.ProviderUtils;

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
                    Map<String, Object> translations = gson.fromJson(new FileReader(file), new TypeToken<Map<String, Object>>() {
                    }.getType());
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
