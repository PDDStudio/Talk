#Talk
An easy to use Android library with a fluent interface to integrate voice interaction into your Application.

##Demo Application
###Screenshot
![](https://raw.githubusercontent.com/PDDStudio/Talk/master/gfx/preview.png) 
###Download

##Getting Started
###Add the library as dependency
Add the library as dependency to your `build.gradle` file.

```java
dependencies {
	//other dependencies...
	compile 'com.pddstudio:talk:1.0.0'
}
```

###Initialize the library
Implement the `Talk.Callback` interface and initialize `Talk` in your Activities `onCreate()` method.

```java
@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //initialize Talk lib
        Talk.init(this, this);
}
```
###Create and add your voice commands
Each voice command is represented as a `SpeechObject` in the library.
You can create and add as much SpeechObjects as you like.

Assuming you want to let your application react on the word `Hello` you have to create a `SpeechObject` which will represent this word.

```java
private SpeechObject helloObject = new SpeechObject() {
        @Override
        public void onSpeechObjectIdentified() {
            //handle action here in case the word is recognized
        }

        @Override
        public String getVoiceString() {
            return "hello";
        }
    };
```

After creating your `SpeechObject` instances you can add them to `Talk` to let it react on them

```java
//add the speech objects (you can add one or more at once)
        Talk.getInstance().addSpeechObjects(helloObject);
```

###Start Listening
Assuming you have a button on which you want to start listening for voice input you simply trigger the `startListening()` method in the button's `onClickListener`.

```java
listenBtn = (Button) findViewById(R.id.listenBtn);
        listenBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
		//make sure to check permission on Android 6.0
                if(hasPermission()) {
                    Talk.getInstance().startListening();
                } else {
                    requestPermission();
                }
            }
        });
```

###Notes
- Make sure that permission `RECORD_AUDIO` is added & granted:  `<uses-permission android:name="android.permission.RECORD_AUDIO" />` 
- You can find some more details in the demo application on how to add voice integration into your layout
- Offline Mode is automatically available on SDK > 23
- Default language is English, you can change this by yourself in case you want to
##About & Contact
- In case you've a question feel free to hit me up via E-Mail (patrick.pddstudio@googlemail.com) 
- or [Google+](http://plus.google.com/+PatrickJung42)

##License
    Copyright 2015 Patrick J

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.