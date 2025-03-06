# 🗣️ Speech-to-Text Android App  

Speech-to-Text Android App is a **real-time voice recognition application** that converts spoken words into text. It uses **Google’s Speech API** to provide **live transcription**, updating the text dynamically as the user speaks. This app is designed for users who need a **hands-free, efficient, and accessible** way to interact with their device using voice commands.  

## ✨ Features  

- 🎤 **Real-Time Speech Recognition**: Displays words as you speak (live transcription).  
- ✅ **Final Recognized Text**: Replaces partial text once speech ends for better accuracy.  
- 🚀 **Error Handling**: Displays appropriate messages when recognition fails.  
- 🔄 **Retry Mechanism**: If an error occurs, the button switches to **"Try Again"** for seamless retry.  
- 🎙 **Microphone Permissions**: Automatically requests runtime permissions for smooth operation.  

## 📥 Installation  

### 1️⃣ Clone the repository  
```sh
git clone https://github.com/yourusername/speech-to-text-app.git
cd speech-to-text-app
```

## 2️⃣ Open the Project in Android Studio  
- **File → Open → Select `speech-to-text-app` folder.**  
- Wait for **Gradle Sync** to complete.  

## 3️⃣ Run the App on a Real Device  
📌 **Note:** Speech recognition may not work properly on an emulator. **Use a real Android device.**  

## 🚀 Usage  

- 🏆 **Tap "Speak"**: Starts speech recognition.  
- 📝 **Live Transcription**: Words appear as you speak.  
- 🎯 **Final Text Display**: Once speech ends, the final recognized text replaces partial results.  
- ❌ **Error Handling**: If recognition fails, the button changes to **"Try Again"** for easy retry.  

## 🛠️ Technologies Used  

- 📱 **Android SDK**: Native Android development.  
- 🗣 **SpeechRecognizer API**: Enables real-time speech-to-text functionality.  
- 🎨 **XML Layouts**: Used for designing UI components.  
- 📏 **ConstraintLayout & LinearLayout**: Ensures a responsive and structured interface.  
- 🔐 **Runtime Permissions**: Handles microphone access requests for better security.  

## 🔮 Future Improvements  

- 🔄 **Continuous Listening Mode**: Keep the app listening indefinitely for real-time dictation.  
- 🌍 **Multi-Language Support**: Enable speech recognition for multiple languages.  
- ⚙ **User Settings**: Customize speech recognition preferences.  

## 🔧 Troubleshooting  

### ❌ `"bind to recognition service failed"` error?  
👉 Install **Google Speech Services** from the Play Store.  

### ❌ Live text not appearing?  
👉 Ensure `EXTRA_PARTIAL_RESULTS` is enabled in `startListening()`.  

### ❌ App crashes on some devices?  
👉 Check if **microphone permissions** are enabled in system settings.  

## 📜 License  

This project is licensed under the **MIT License**. Feel free to contribute and improve the application!  
