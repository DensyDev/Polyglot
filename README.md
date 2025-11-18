# Polyglot
Polyglot is an advanced and multifunctional library for localizing strings.

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

// Strategy for determining the language when the requested language is not available. 
// For example: return Russian when Ukrainian is not available, instead of the default English.
translation.setLanguageStrategy(LanguageStrategy.mappingsBuilder()
                .put(SimpleLanguage.UKR, SimpleLanguage.RUS)
                .build());
// We can also set the default language using `LanguageStrategy.defaultResult(SimpleLanguage.ENG)`.

// Fallback strategy for missing localizations
// You can also do the same thing this way: `translation.setFallbackStrategy(FallbackStrategy.suffix(“-fallback”));`.
// Or you can use `FallbackStrategy.keyToKey()` if you want the result to be the key of the missing locale.
translation.setFallbackStrategy(key -> key + "-fallback");

translation.addTranslation(SimpleLanguage.ENG, "local.translation", "Local message with param [0]"); // Adding local translation

// Translating the messages
String local = translation.translate(SimpleLanguage.RUS, "local.translation", 1);
String global = translation.translate(SimpleLanguage.RUS, "global.translation", new KeyedTrParameters().put("local", "Parameter"));

System.out.println("Translated local message: " + local);
System.out.println("Translated global message: " + global);
```

## Dependency info on Luminia Dev Repo:
https://repo.luminiadev.com/#/private/com/luminiadev/polyglot