package com.pddstudio.talking;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.util.Log;

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
