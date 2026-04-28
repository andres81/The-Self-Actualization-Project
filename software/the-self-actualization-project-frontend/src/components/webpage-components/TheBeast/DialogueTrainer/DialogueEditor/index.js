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
import React from 'react';

import InputEditor from './InputEditor';
import AccordionEntry from './AccordionEntry';
import Accordion from './Accordion';
import ManualEditing from './ManualEditing';
import Dialogue from '../Dialogue';
import _ from 'lodash';
import Controls from './Controls';
import {DIALOGUE_EDITOR_ROW} from '../constants.js'
import {DEFAULT_DIALOGUE} from '../dialogue-constants.js'
// import ExercisesEditor from '../../Exercises/ExercisesEditor'
import './style.scss';

class DialogueEditor extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            base64: this.b64EncodeUnicode(DEFAULT_DIALOGUE),
            dialogue: DEFAULT_DIALOGUE
        };
    }

    render() {
        let title = this.state.dialogue && this.state.dialogue.title ? this.state.dialogue.title : "";
        return (
            <div className="container dialogue-editor-container">
                <Controls base64={this.state.base64}
                          updateTitle={this.updateTitle}
                          title={title}
                          dialogue={this.state.dialogue}
                          updateDialogue={this.updateDialogue}
                          addRow={this.addRow}
                          onChangeBase64={this.onBase64Input}/>
                <br/>
                <InputEditor lines={this.state.dialogue.lines}
                             updateLines={this.updateLines}
                             audioSource={this.state.dialogue.audioSource}
                             onAudioChange={this.onAudioChange}
                             youtubeEmbedId={this.state.dialogue.youtubeEmbedId}
                             onYoutubeEmbedId={this.onYoutubeEmbedId}/>
                <br/>
                <Accordion>
                    <AccordionEntry id="1" headerText={"Preview"}
                                    child={<Dialogue showFileControls={false}
                                                     dialogueJson={this.state.dialogue}/>}/>
                    <AccordionEntry id="2" headerText={"Manual editing"}
                                    child={<ManualEditing
                                        updateDialogue={this.updateDialogue}
                                        json={this.state.dialogue}/>}/>
                    {/*<AccordionEntry id="3" headerText={"Exercises editing"}*/}
                    {/*                child={*/}
                    {/*                    <ExercisesEditor*/}
                    {/*                        questions={this.state.dialogue.questions}*/}
                    {/*                        updateQuestions={this.updateQuestions}/>*/}
                    {/*                }/>*/}

                </Accordion>
            </div>
        )
    }

    onYoutubeEmbedId = (embedId) => {
        let dialogue = _.cloneDeep(this.state.dialogue);
        dialogue.youtubeEmbedId = embedId;
        this.setState({dialogue: dialogue});
    }

    onAudioChange = (audioSource) => {
        let dialogue = _.cloneDeep(this.state.dialogue);
        dialogue.audioSource = audioSource;
        this.setState({dialogue: dialogue});
    }

    updateQuestions = (questions) => {
        let dialogue = this.state.dialogue;
        dialogue.questions = _.cloneDeep(questions);
        this.setState({
            dialogue: dialogue
        });
    }

    onBase64Input = (base64) => {
        let dialogue = this.decodeBase64ToJson(base64);
        if (dialogue) {
            this.setState(
                {
                    base64: base64,
                    dialogue: dialogue
                }
            );
        }
    }

    updateTitle = (title) => {
        this.setState({dialogue: Object.assign({}, _.cloneDeep(this.state.dialogue), {title: title})});
    }

    decodeBase64ToJson = (base64Str) => {
        if (!(base64Str && base64Str.length > 0)) {
            return null;
        }
        try {
            let decode = decodeURIComponent(escape(window.atob(base64Str)));
            return JSON.parse(decode);
        } catch (e) {
            console.error("Error decoding json: ", e);
            return null;
        }
    }

    b64EncodeUnicode = (obj) => {
        let str = JSON.stringify(obj);
        let base64Str = btoa(unescape(encodeURIComponent(str)));
        return base64Str;
    }

    updateDialogue = (dialogue) => {
        this.setState({dialogue: _.cloneDeep(dialogue)});
    }

    addRow = () => {
        let lines = this.state.dialogue.lines.slice();
        lines.push(_.cloneDeep(DIALOGUE_EDITOR_ROW));
        this.updateLines(lines);
    }

    updateLines = (lines) => {
        let newDialogue = _.cloneDeep(this.state.dialogue);
        newDialogue.lines = _.cloneDeep(lines);
        this.setState(
            {
                dialogue: newDialogue,
                base64: this.b64EncodeUnicode(newDialogue)
            }
        );
    }

    openCloseToggle = () => {
        this.setState({openClose: !(!!this.state.openClose)});
    }
}

export default DialogueEditor;