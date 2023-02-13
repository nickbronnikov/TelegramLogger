# TelegramLogger
A Simple Android Library for sending message to Telegram channels using bot
### Step 1. Add the JitPack repository to your build file 
Add it in your root build.gradle at the end of repositories:
```Kotlin
allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}
```

### Step 2. Add the dependency
```Kotlin
dependencies {
    implementation 'com.github.nickbronnikov:TelegramLogger:0.1.0'
}
```

### Usage
#### Sending message to Telegram channel
```kotlin
TelegramLogger.logToChannel(
    "bot_token",
    "some_channel_name_or_chat_id",
    "Some message",
    { response ->
      //Using telegram response
    }
)
```
## License
The contents of this repository are licensed under the
[MIT License](https://github.com/nickbronnikov/TelegramLogger/blob/main/LICENSE)
