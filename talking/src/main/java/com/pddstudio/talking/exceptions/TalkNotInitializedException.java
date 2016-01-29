package com.pddstudio.talking.exceptions;

/**
 * This Class was created by Patrick J
 * on 28.01.16. For more Details and Licensing
 * have a look at the README.md
 */
public class TalkNotInitializedException extends RuntimeException {

    public TalkNotInitializedException() {
        super("Did you initialize Talk ?!");
    }

}
