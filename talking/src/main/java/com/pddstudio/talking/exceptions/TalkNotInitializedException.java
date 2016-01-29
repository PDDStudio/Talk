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
