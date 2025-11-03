package com.rr.lazylist.ui.services

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.speech.RecognitionListener
import android.speech.RecognizerIntent
import android.speech.SpeechRecognizer
import androidx.core.content.ContextCompat
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class VoiceRecognitionService(
    private val context: Context
) {
    private var speechRecognizer: SpeechRecognizer? = null
    private var isCurrentlyListening = false

    private val _voiceResult = MutableStateFlow<String?>(null)
    val voiceResult: StateFlow<String?> = _voiceResult.asStateFlow()

    private val _isListening = MutableStateFlow(false)
    val isListening: StateFlow<Boolean> = _isListening.asStateFlow()

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error.asStateFlow()

    private val recognitionListener = object : RecognitionListener {
        override fun onReadyForSpeech(params: android.os.Bundle?) {
            _isListening.value = true
        }

        override fun onBeginningOfSpeech() {
            // User started speaking
        }

        override fun onRmsChanged(rmsdB: Float) {
            // Audio level changed
        }

        override fun onBufferReceived(buffer: ByteArray?) {
            // Partial results received
        }

        override fun onEndOfSpeech() {
            _isListening.value = false
        }

        override fun onError(error: Int) {
            _isListening.value = false
            val errorMessage = when (error) {
                SpeechRecognizer.ERROR_AUDIO -> "Audio recording error"
                SpeechRecognizer.ERROR_CLIENT -> "Client side error"
                SpeechRecognizer.ERROR_INSUFFICIENT_PERMISSIONS -> "Insufficient permissions"
                SpeechRecognizer.ERROR_NETWORK -> "Network error"
                SpeechRecognizer.ERROR_NETWORK_TIMEOUT -> "Network timeout"
                SpeechRecognizer.ERROR_NO_MATCH -> "No match found"
                SpeechRecognizer.ERROR_RECOGNIZER_BUSY -> "Recognizer service busy"
                SpeechRecognizer.ERROR_SERVER -> "Server error"
                SpeechRecognizer.ERROR_SPEECH_TIMEOUT -> "No speech input"
                else -> "Unknown error occurred"
            }
            _error.value = errorMessage
        }

        override fun onResults(results: android.os.Bundle?) {
            val matches = results?.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION)
            if (!matches.isNullOrEmpty()) {
                _voiceResult.value = matches[0]
            }
        }

        override fun onPartialResults(partialResults: android.os.Bundle?) {
            // Partial results received
        }

        override fun onEvent(eventType: Int, params: android.os.Bundle?) {
            // Event occurred
        }
    }

    fun checkPermission(): Boolean {
        return ContextCompat.checkSelfPermission(
            context,
            Manifest.permission.RECORD_AUDIO
        ) == PackageManager.PERMISSION_GRANTED
    }

    fun startListening() {
        if (!checkPermission()) {
            _error.value = "Microphone permission not granted"
            return
        }

        if (isCurrentlyListening) {
            stopListening()
        }

        try {
            speechRecognizer = SpeechRecognizer.createSpeechRecognizer(context)
            speechRecognizer?.setRecognitionListener(recognitionListener)
            val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH).apply {
                putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
                putExtra(RecognizerIntent.EXTRA_PROMPT, "Say something lazy...")
            }
            speechRecognizer?.startListening(intent)
            isCurrentlyListening = true
        } catch (e: Exception) {
            _error.value = "Speech recognition not available: ${e.message}"
        }
    }

    fun stopListening() {
        speechRecognizer?.stopListening()
        speechRecognizer?.cancel()
        speechRecognizer?.destroy()
        speechRecognizer = null
        isCurrentlyListening = false
        _isListening.value = false
    }

    fun clearResult() {
        _voiceResult.value = null
        _error.value = null
    }

    fun destroy() {
        stopListening()
    }
}

