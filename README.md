# Zetic LLM Template for Android

A LLM Android application template built with Kotlin, following current Android development best practices.

## Project Structure

```
zetic-llm-android-kotlin-template/
├── README.md
├── LICENSE
├── .gitignore
├── build.gradle.kts
├── settings.gradle.kts
├── gradle.properties
├── gradle/
│   ├── libs.versions.toml
│   └── wrapper/
│       ├── gradle-wrapper.jar
│       └── gradle-wrapper.properties
└── app/
    ├── build.gradle.kts
    ├── proguard-rules.pro
    ├── .gitignore
    └── src/
        ├── main/
        │   ├── AndroidManifest.xml
        │   ├── java/com/zeticai/zeticmlangellmsample/
        │   │   ├── MainActivity.kt
        │   │   ├── utils/
        │   │   │   └── Constants.kt
        │   │   ├── view/
        │   │   │   └── ChatAdapter.kt
        │   │   └── domain/
        │   │       └── Message.kt
        │   └── res/
        │       ├── layout/
        │       │   ├── activity_main.xml
        │       │   ├── item_message_ai.xml
        │       │   └── item_message_user.xml
        │       ├── values/
        │       │   ├── strings.xml
        │       │   ├── colors.xml
        │       │   └── themes.xml
        │       ├── values-night/
        │       │   └── themes.xml
        │       ├── drawable/
        │       ├── mipmap-*/
        │       └── xml/
        │           |── backup_rules.xml
        │           └── data_extraction_rules.xml
        ├── test/
        │   └── java/com/zeticai/zeticmlangellmsample/
        │       └── ExampleUnitTest.kt
        └── androidTest/
            └── java/com/zeticai/zeticmlangellmsample/
                └── ExampleInstrumentedTest.kt

```

## 🚀 Quick Start

### 1. Clone the Project

```bash
git clone https://github.com/zetic-ai/zetic-llm-android-kotlin-template.git
cd zetic-llm-android-kotlin-template
```

### 2. Configure Credentials

Update your SDK credentials in `app/src/main/java/com/zeticai/zeticmlangellmsample/utils/Constants.kt`:

> If you have no token for SDK, Check [ZeticAI personal settings](https://mlange.zetic.ai/settings?tab=pat)

```kotlin
object Constants {
    // TODO: Replace with your actual Credentials
    const val MLANGE_PERSONAL_ACCESS_TOKEN = "YOUR_PERSONAL_ACCESS_TOKEN"
    const val MODEL_KEY = "YOUR_MODEL_KEY"
}
```

### 3. Customize App Details

Update the following files:

#### `app/build.gradle.kts`
```kotlin
android {
    namespace = "com.zeticai.zeticmlangellmsample" // Change this
    compileSdk = 35
    
    defaultConfig {
        applicationId = "com.zeticai.zeticmlangellmsample" // Change this
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"
    }
}
```

#### `app/src/main/res/values/strings.xml`
```xml
<resources>
    <string name="app_name">ZeticMLangeLLMSample</string> <!-- Change this -->
</resources>
```

### 4. Build and Run

```bash
# Build the project
./gradlew build

# Install debug APK
./gradlew installDebug

# Or open in Android Studio and run
```

## 🔧 Development Setup

### Environment Variables
Create `local.properties` in root directory:
```properties
sdk.dir=/path/to/android/sdk
```

## 📚 Documentation & Support

- [ZeticAI Guide](https://docs.zetic.ai) - Zetic AI Docs
- Feel free to ask us. Create an issue or mail to us([software@zetic.ai](mailto:software@zetic.ai))


## 📄 License

This project is licensed under the MIT License - see the [MIT LICENSE](LICENSE) file for details.
