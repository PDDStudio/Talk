package com.pddstudio.talking.model;

/**
 * This Class was created by Patrick J
 * on 28.01.16. For more Details and Licensing
 * have a look at the README.md
 */
public abstract class SpeechObject implements SpeechInterface {

    public SpeechObject() {}

    public abstract void onSpeechObjectIdentified();

    public abstract String getVoiceString();

}
