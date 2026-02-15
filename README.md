# Polyglot
Polyglot is an advanced and multifunctional library for localizing strings.

## Example of usage
```java
import org.densy.polyglot.api.Translation;
import org.densy.polyglot.api.context.TranslationContext;
import org.densy.polyglot.api.util.LanguageStrategy;
import org.densy.polyglot.common.language.SimpleLanguage;
import org.densy.polyglot.core.context.BaseTranslationContext;
import org.densy.polyglot.core.language.SimpleLanguageStandard;
import org.densy.polyglot.core.parameter.KeyedTranslationParameters;
import org.densy.polyglot.core.parameter.TrParameters;
import org.densy.polyglot.core.provider.YamlFileProvider;

import java.io.File;

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
String global = translation.translate(SimpleLanguage.RUS, "global.translation", TrParameters.keyed().put("local", "Parameter"));

System.out.println("Translated local message: " + local);
System.out.println("Translated global message: " + global);
```

> [!NOTE] 
> If you want to work with the library in Kotlin, check out this extension:        
> https://github.com/DensyDev/Polyglot-Kotlin-Extension

## Maven
Adding repository:
```xml
<repositories>
    <repository>
        <id>densy-repository-snapshots</id>
        <url>https://repo.densy.org/snapshots</url>
    </repository>
</repositories>
```

Adding a library api:
```xml
<dependency>
    <groupId>org.densy.polyglot</groupId>
    <artifactId>api</artifactId>
    <vearsion>1.1.3-SNAPSHOT</vearsion>
</dependency>
```

Adding a library implementation:
```xml
<dependency>
    <groupId>org.densy.polyglot</groupId>
    <artifactId>core</artifactId>
    <version>1.1.3-SNAPSHOT</version>
</dependency>
```

## Gradle
Adding repository:
```groovy
maven {
    name = "densyRepositorySnapshots"
    url = uri("https://repo.densy.org/snapshots")
}
```

Adding a library api:
```groovy
implementation "org.densy.polyglot:api:1.1.3-SNAPSHOT"
```

Adding a library implementation:
```groovy
implementation "org.densy.polyglot:core:1.1.3-SNAPSHOT"
```