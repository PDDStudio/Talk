/*
 * Copyright 2016 Patrick J
 *
 *     Licensed under the Apache License, Version 2.0 (the "License");
 *     you may not use this file except in compliance with the License.
 *     You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *     Unless required by applicable law or agreed to in writing, software
 *     distributed under the License is distributed on an "AS IS" BASIS,
 *     WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *     See the License for the specific language governing permissions and
 *     limitations under the License.
 */

package com.pddstudio.talking.model;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.util.Log;

import com.pddstudio.talking.Talk;

import java.util.ArrayList;
import java.util.List;

/**
 * This Class was created by Patrick J
 * on 28.01.16. For more Details and Licensing
 * have a look at the README.md
 */
public class RecognitionModel implements RecognitionListener {

    private static final String LOG_TAG = "RecognitionModel";

    final Talk.Callback tk;
    final SpeechRecognizer speechRecognizer;
    final List<SpeechObject> speechObjects;

    public RecognitionModel(Talk.Callback callback, SpeechRecognizer speechRecognizer, List<SpeechObject> speechObjectList) {
        this.tk = callback;
        this.speechRecognizer = speechRecognizer;
        this.speechObjects = speechObjectList;
        this.speechRecognizer.setRecognitionListener(this);
    }

    public void startListening() {
        Intent recognizerIntent = new Intent(RecognizerIntent.ACTION_VOICE_SEARCH_HANDS_FREE);
        recognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, "en-US");
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            recognizerIntent.putExtra(RecognizerIntent.EXTRA_PREFER_OFFLINE, true);
        }
        recognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        recognizerIntent.putExtra(RecognizerIntent.EXTRA_PARTIAL_RESULTS, true);
        speechRecognizer.startListening(recognizerIntent);
        tk.onStartListening();
        Log.d(LOG_TAG, "startListening()");
    }

    public void stopListening() {
        speechRecognizer.destroy();
    }

    @Override
    public void onReadyForSpeech(Bundle params) {

    }

    @Override
    public void onBeginningOfSpeech() {

    }

    @Override
    public void onRmsChanged(float rmsdB) {
        tk.onRmsChanged(rmsdB);
    }

    @Override
    public void onBufferReceived(byte[] buffer) {

    }

    @Override
    public void onEndOfSpeech() {

    }

    @Override
    public void onError(int error) {
        tk.onFailedListening(error);
    }

    @Override
    public void onResults(Bundle results) {
        boolean commandReceived = false;
        Log.d(LOG_TAG, "onResults()");
        ArrayList<String> resultsList = results.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
        if(resultsList != null && !resultsList.isEmpty()) {
            for (String s : resultsList) {
                Log.d(LOG_TAG, "Result: " + s);
                for(SpeechObject speechObject : speechObjects) {
                    if(s.toLowerCase().contains(speechObject.getVoiceString().toLowerCase())) {
                        tk.onFinishedListening(speechObject);
                        commandReceived = true;
                    }
                }
            }
            if(!commandReceived) tk.onFailedListening(42);
        } else {
            tk.onFailedListening(42);
        }
    }

    @Override
    public void onPartialResults(Bundle partialResults) {

    }

    @Override
    public void onEvent(int eventType, Bundle params) {

    }
}
