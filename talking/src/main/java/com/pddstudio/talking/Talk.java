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

package com.pddstudio.talking;

import android.content.Context;
import android.speech.SpeechRecognizer;

import com.pddstudio.talking.exceptions.TalkNotInitializedException;
import com.pddstudio.talking.model.RecognitionModel;
import com.pddstudio.talking.model.SpeechObject;

import java.util.LinkedList;
import java.util.List;

/**
 * This Class was created by Patrick J
 * on 28.01.16. For more Details and Licensing
 * have a look at the README.md
 */
public final class Talk {

    private static Talk talk;

    private final Context context;
    private final Callback callback;
    private final List<SpeechObject> speechObjects;
    private final SpeechRecognizer speechRecognizer;
    private RecognitionModel recognitionModel;

    public interface Callback {
        void onStartListening();
        void onRmsChanged(float rms);
        void onFailedListening(int errorCode);
        void onFinishedListening(SpeechObject speechObject);
    }

    private Talk(Context context, Callback callback) {
        this.context = context;
        this.callback = callback;
        this.speechRecognizer = SpeechRecognizer.createSpeechRecognizer(context);
        this.speechObjects = new LinkedList<>();
    }

    public static void init(Context context, Callback callback) {
        if(talk == null) talk = new Talk(context, callback);
    }

    public static Talk getInstance() {
        if(talk == null) throw new TalkNotInitializedException();
        return talk;
    }

    public void addSpeechObjects(SpeechObject... speechObjects) {
        for(SpeechObject speechObject : speechObjects) {
            this.speechObjects.add(speechObject);
        }
    }

    public void startListening() {
        recognitionModel = new RecognitionModel(callback, speechRecognizer, speechObjects);
        recognitionModel.startListening();
    }

}
