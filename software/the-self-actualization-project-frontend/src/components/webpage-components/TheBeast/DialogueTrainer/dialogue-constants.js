/*
 * Copyright 2024 - 2026 André Schepers
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
export const PERSON_NAME = "personName";

export const DEFAULT_DIALOGUE = {
    "title": "Greeting a friend",
    "markdown": "A paragraph with *emphasis* and **strong importance**.\n\n> A block quote with ~strikethrough~ and a URL: https://reactjs.org.\n\n* Lists\n* [ ] todo\n* [x] done\n\nA table:\n\n| a | b |\n| - | - |\n|table|tttt|",
    "lines": [
        {
            "type": "dialogue-line",
            "personName": "Jane",
            "text": "Hallo, Tom!",
            "translation": "Hello Tom!",
            "wordTranslations": [
                {
                    "left": {
                        "text": "hallo"
                    },
                    "right": {
                        "text": "hello"
                    }
                }
            ]
        },
        {
            "type": "dialogue-line",
            "personName": "Tom",
            "text": "Hoi Jane!",
            "translation": "Hi Jane!",
            "wordTranslations": [
                {
                    "left": {
                        "text": "hoi"
                    },
                    "right": {
                        "text": "hi"
                    }
                }
            ]
        },
        {
            "type": "dialogue-line",
            "personName": "Jane",
            "text": "Hoe gaat het met je?",
            "translation": "How are you doing?",
            "wordTranslations": [
                {
                    "left": {
                        "text": "hoe"
                    },
                    "right": {
                        "text": "how"
                    }
                },
                {
                    "left": {
                        "text": "gaat"
                    },
                    "right": {
                        "text": "going"
                    }
                },
                {
                    "left": {
                        "text": "het"
                    },
                    "right": {
                        "text": "it"
                    }
                },
                {
                    "left": {
                        "text": "met"
                    },
                    "right": {
                        "text": "with"
                    }
                },
                {
                    "left": {
                        "text": "je"
                    },
                    "right": {
                        "text": "you"
                    }
                }
            ]
        },
        {
            "type": "dialogue-line",
            "personName": "Tom",
            "text": "Met mij gaat het goed dank je wel. En hoe gaat het met jou?",
            "translation": "I'm doing good thank you. And how are you?",
            "wordTranslations": [
                {
                    "left": {
                        "text": "Met"
                    },
                    "right": {
                        "text": "with"
                    }
                },
                {
                    "left": {
                        "text": "mij"
                    },
                    "right": {
                        "text": "me"
                    }
                },
                {
                    "left": {
                        "text": "gaat"
                    },
                    "right": {
                        "text": "going"
                    }
                },
                {
                    "left": {
                        "text": "het"
                    },
                    "right": {
                        "text": "it"
                    }
                },
                {
                    "left": {
                        "text": "goed"
                    },
                    "right": {
                        "text": "good"
                    }
                },
                {
                    "left": {
                        "text": "dank"
                    },
                    "right": {
                        "text": "thank"
                    }
                },
                {
                    "left": {
                        "text": "je"
                    },
                    "right": {
                        "text": "you"
                    }
                },
                {
                    "left": {
                        "text": "wel"
                    },
                    "right": {
                        "text": "-"
                    }
                },
                {
                    "left": {
                        "text": "en"
                    },
                    "right": {
                        "text": "and"
                    }
                },
                {
                    "left": {
                        "text": "hoe"
                    },
                    "right": {
                        "text": "how"
                    }
                },
                {
                    "left": {
                        "text": "gaat"
                    },
                    "right": {
                        "text": "going"
                    }
                },
                {
                    "left": {
                        "text": "het"
                    },
                    "right": {
                        "text": "it"
                    }
                },
                {
                    "left": {
                        "text": "met"
                    },
                    "right": {
                        "text": "with"
                    }
                },
                {
                    "left": {
                        "text": "jou"
                    },
                    "right": {
                        "text": "you"
                    }
                }
            ]
        }
    ],
    "questions": [
        {
            "id": "3b51096e-026a-439c-8e9f-6a8f297f19e0",
            "title": "How did Tom greet Jane?",
            "answerType": "singular",
            "answers": [
                {
                    "id": "4899e43e-c449-40e4-947c-0890ba5b6ecf",
                    "correct": false,
                    "text": "Nobody greeted anybody. There were no greetings in the dialogue",
                    "explanationText": "This is not true. Both parties greeted the other."
                },
                {
                    "id": "a16db2cd-9e8d-4d73-a528-2ba5fcc1b5c4",
                    "correct": false,
                    "text": "Only Jane greeted Tom. Tom did not greet Jane.",
                    "explanationText": "This is not true, also Tom greeted Jane."
                },
                {
                    "id": "340dc53f-acb3-43d8-867c-8b67b27d9dbf",
                    "correct": true,
                    "text": "Hoi Jane!",
                    "explanationText": "Hoi is like \"Hi\" in English. This is the correct answer."
                },
                {
                    "id": "f8df9882-6240-4843-9a1f-56975759e22e",
                    "correct": false,
                    "text": "Hallo",
                    "explanationText": "That is how Jane greeted Tom."
                }
            ]
        },
        {
            "id": "e200ce44-a3d9-4052-a154-b16bc5d256f9",
            "title": "How did Jane ask Tom how he is doing?",
            "answerType": "singular",
            "answers": [
                {
                    "id": "ff2b310c-dba7-40e6-8cec-6c6acd8cd742",
                    "correct": false,
                    "text": "Dank je wel",
                    "explanationText": "This translates to: Thank you"
                },
                {
                    "id": "87968479-52a3-4446-99b1-d3182de0bd29",
                    "correct": false,
                    "text": "En hoe gaat het met jou?",
                    "explanationText": "This is asked by Tom, and translates to: And how are you doing?"
                },
                {
                    "id": "10657e3e-564c-4398-9536-81c59feac0f0",
                    "correct": false,
                    "text": "Met mij gaat het goed",
                    "explanationText": "This translates to: I am doing good"
                },
                {
                    "id": "46359573-4325-4da9-b223-130bd13bd718",
                    "correct": true,
                    "text": "Hoe gaat het met je?",
                    "explanationText": "This is the correct answer. It translates to: How are you doing?"
                }
            ]
        }
    ]
};