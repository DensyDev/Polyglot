# Polyglot
Polyglot is an advanced and multifunctional library for localizing strings for Luminia Development projects.

## Example of usage
```java
import com.luminiadev.polyglot.api.Translation;
import com.luminiadev.polyglot.api.context.TranslationContext;
import com.luminiadev.polyglot.common.language.SimpleLanguage;
import com.luminiadev.polyglot.core.context.BaseTranslationContext;
import com.luminiadev.polyglot.core.language.SimpleLanguageStandard;
import com.luminiadev.polyglot.core.parameter.KeyedTrParameters;
import com.luminiadev.polyglot.core.provider.YamlFileProvider;

// Creating and configuring context
TranslationContext context = new BaseTranslationContext();
// Global parameter visible for all translations
context.addGlobalParameter("global", "Global value");
// Global translation, available in translations if no local translation exists for the key
context.addGlobalTranslation(SimpleLanguage.ENG, "global.translation", "Global message, {global} and {local}");
// Language standard. Built-in: simple (“eng”, “rus”, etc.) and locale (‘en_GB’, “ru_RU”, etc.)
context.setLanguageStandard(new SimpleLanguageStandard());


// Creating a translation from context
Translation translation = context.createTranslation(new YamlFileProvider(
        new File("lang"), context.getLanguageStandard()
));
translation.setDefaultLanguage(SimpleLanguage.ENG); // Default language
translation.setFallbackStrategy(key -> key + "-fallback"); // Fallback strategy for missing localizations
translation.addTranslation(SimpleLanguage.ENG, "local.translation", "Local message with param [0]"); // Adding local translation

// Translating the messages
String local = translation.translate(SimpleLanguage.RUS, "local.translation", 1);
String global = translation.translate(SimpleLanguage.RUS, "local.translation", new KeyedTrParameters().put("local", "Parameter"));

System.out.println("Translated local message: " + local);
System.out.println("Translated global message: " + global);
```