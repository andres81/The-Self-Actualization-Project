/*
 * Copyright 2024 - 2025 André Schepers
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
import React from 'react'

import {isNonBlank} from "../../../../util/StringUtils"

import './style.scss'

class DialogueInfoModal extends React.Component {

    constructor(props) {
        super(props);

        this.state = {};
    }

    componentDidCatch = (error, info) => {
        this.setState({uiError: true});
    }

    render() {

        let pairs = [
            {"left": {"text": "oneLeft"}, "right": {"text": "oneRight"}},
            {"left": {"text": "twoLeft"}, "right": {"text": "twoRight"}},
            {"left": {"text": "threeLeft"}, "right": {"text": "threeRight"}},
            {"left": {"text": "fourLeft"}, "right": {"text": "fourRight"}},
            {"left": {"text": "fiveLeft"}, "right": {"text": "fiveRight"}}
        ];

        let exercise = {pairs: []};
        let lines = this.props.dialogue.lines;
        lines.forEach((line, index) => {
            let wordPairs = line.wordTranslations;
            if (wordPairs) {
                exercise.pairs = exercise.pairs.concat(wordPairs);
            }
        });

        if (exercise.pairs.length < 5) {
            for (let i = exercise.pairs.length; i < 5; i++) {
                exercise.pairs.push(pairs[i]);
            }
        }

        exercise.pairs = exercise.pairs.filter(pair => isNonBlank(pair.left.text, pair.right.text));

        let uniqueWords = [];
        exercise.pairs = exercise.pairs.filter(pair => {
            let id = (pair.left.text + pair.right.text).toLowerCase();
            if (uniqueWords.indexOf(id) > -1) {
                return false;
            }
            uniqueWords.push(id);
            return true;
        });

        let isError = this.state.uiError;

        return (
            <div className="dialogue-info-modal">
                <br/><br/>
                <h1 className="dialogue-info-modal-title">{this.props.dialogue.title}</h1>
                <br/>
                <hr style={{"color": "white"}}/>
                <br/>
                <h3 className="dialogue-info-modal-header">Tags</h3>
                <br/>
                <br/>
                <hr style={{"color": "white"}}/>
                <br/>
                <h3 className="dialogue-info-modal-header">Addendum</h3>
                <br/>
                <div className="dialogue-info-modal-addendum">
                    {/*<ReactMarkdown children={this.props.dialogue.markdown} remarkPlugins={[remarkGfm]} />*/}
                </div>
                <br/>
                <hr style={{"color": "white"}}/>
                <br/>
                <h3 className="dialogue-info-modal-header">Vocabulary</h3>
                <br/>

                {isError &&
                    <h3 className="text-center">Cannot show VocabularyTrainer:
                        Not in a valid state!</h3>
                }

                <br/>
                <hr style={{"color": "white"}}/>
                <br/>
            </div>
        )
    }
}

export default DialogueInfoModal;