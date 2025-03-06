package com.example.placenciaspeak;


import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private TextView txtSpeechInput;
    private Button btnSpeak;
    private SpeechRecognizer speechRecognizer;
    private boolean isRetry = false; // Tracks button state

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtSpeechInput = findViewById(R.id.txtSpeechInput);
        btnSpeak = findViewById(R.id.btnSpeak);

        // Request microphone permission
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.RECORD_AUDIO}, 1);
        }

        // Initialize SpeechRecognizer
        speechRecognizer = SpeechRecognizer.createSpeechRecognizer(this);
        speechRecognizer.setRecognitionListener(new RecognitionListener() {
            @Override
            public void onReadyForSpeech(Bundle params) {
                txtSpeechInput.setText("Listening...");
            }

            @Override
            public void onBeginningOfSpeech() {}

            @Override
            public void onRmsChanged(float rmsdB) {}

            @Override
            public void onBufferReceived(byte[] buffer) {}

            @Override
            public void onEndOfSpeech() {
                txtSpeechInput.append("\n[End of Speech]");
            }

            // ✅ Show live speech-to-text while speaking
            @Override
            public void onPartialResults(Bundle partialResults) {
                ArrayList<String> matches = partialResults.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
                if (matches != null && matches.size() > 0) {
                    txtSpeechInput.setText(matches.get(0)); // Show words live
                }
            }

            // ✅ Final recognized speech result
            @Override
            public void onResults(Bundle results) {
                ArrayList<String> matches = results.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
                if (matches != null && matches.size() > 0) {
                    txtSpeechInput.setText(matches.get(0)); // Final text after speech ends
                }
                btnSpeak.setText("Speak");
                isRetry = false;
            }

            // ✅ Handle errors and allow retry
            @Override
            public void onError(int error) {
                String errorMessage;
                switch (error) {
                    case SpeechRecognizer.ERROR_AUDIO:
                        errorMessage = "Audio recording error";
                        break;
                    case SpeechRecognizer.ERROR_CLIENT:
                        errorMessage = "Client side error";
                        break;
                    case SpeechRecognizer.ERROR_INSUFFICIENT_PERMISSIONS:
                        errorMessage = "Insufficient permissions";
                        break;
                    case SpeechRecognizer.ERROR_NETWORK:
                        errorMessage = "Network error";
                        break;
                    case SpeechRecognizer.ERROR_NO_MATCH:
                        errorMessage = "No match found";
                        break;
                    case SpeechRecognizer.ERROR_RECOGNIZER_BUSY:
                        errorMessage = "Recognition service busy";
                        break;
                    case SpeechRecognizer.ERROR_SERVER:
                        errorMessage = "Server error";
                        break;
                    case SpeechRecognizer.ERROR_SPEECH_TIMEOUT:
                        errorMessage = "No speech input";
                        break;
                    default:
                        errorMessage = "Error recognizing speech";
                        break;
                }
                txtSpeechInput.setText(errorMessage);
                btnSpeak.setText("Try Again");
                isRetry = true;
            }

            @Override
            public void onEvent(int eventType, Bundle params) {}
        });

        // ✅ Button Click Listener - Handles "Try Again" State
        btnSpeak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isRetry) {
                    // ✅ Reset button state before restarting listening
                    btnSpeak.setText("Speak");
                    txtSpeechInput.setText("Tap Speak and say something...");
                    isRetry = false;

                    // ✅ Small delay before restarting listening
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            startListening();
                        }
                    }, 500);
                } else {
                    startListening();
                }
            }
        });
    }

    private void startListening() {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_PARTIAL_RESULTS, true); // ✅ Enable live speech results
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Say something...");
        speechRecognizer.startListening(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (speechRecognizer != null) {
            speechRecognizer.destroy();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1 && grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(this, "Microphone permission granted", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Microphone permission is required!", Toast.LENGTH_LONG).show();
        }
    }
}
