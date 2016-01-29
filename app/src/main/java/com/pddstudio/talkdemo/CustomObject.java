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

package com.pddstudio.talkdemo;

import android.app.Activity;
import android.widget.TextView;

import com.pddstudio.talking.model.SimpleSpeechObject;

/**
 * This Class was created by Patrick J
 * on 29.01.16. For more Details and Licensing
 * have a look at the README.md
 */
public class CustomObject extends SimpleSpeechObject {

    final Activity activity;
    final String voice;

    public CustomObject(Activity activity, String voice) {
        super();
        super.setVoiceString(voice);
        this.activity = activity;
        this.voice = voice;
    }

    @Override
    public void onSpeechObjectIdentified() {
        ((TextView) activity.findViewById(R.id.textLabel)).setText("Recognized: " + voice);
    }

}
