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


### 3. Customize Quant Type
Update Quantization in `app/src/main/java/com/zeticai/zeticmlangellmsample/MainActivity.kt`

```kotlin
// ...
private val model: ZeticMLangeLLMModel by lazy {
    ZeticMLangeLLMModel(
        this,
        Constants.MLANGE_PERSONAL_ACCESS_TOKEN,
        Constants.MODEL_KEY,
        LLMTarget.LLAMA_CPP,
        LLMQuantType.GGUF_QUANT_Q4_K_M // Change Quant type what you want
    ) {
    // ...
```

#### Available Quant Types
- LLMQuantType.GGUF_QUANT_F16
- LLMQuantType.GGUF_QUANT_BF16
- LLMQuantType.GGUF_QUANT_Q8_0
- LLMQuantType.GGUF_QUANT_Q6_K
- LLMQuantType.GGUF_QUANT_Q4_K_M
- LLMQuantType.GGUF_QUANT_Q3_K_M
- LLMQuantType.GGUF_QUANT_Q2_K
- LLMQuantType.GGUF_QUANT_Q6_K

Check it out [Zetic Model Hub](https://mlange.zetic.ai/dashboard).

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
