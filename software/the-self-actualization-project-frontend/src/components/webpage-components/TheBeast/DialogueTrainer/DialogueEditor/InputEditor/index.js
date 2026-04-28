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
import _ from "lodash";
import {AiTwotoneFolderOpen} from 'react-icons/ai';
import {v4 as uuidv4} from 'uuid';

import EditorRow from './EditorRow';
import {readFileAsBase64} from '../../../../../../util/FileUtils';

import {DIALOGUE_EDITOR_ROW} from '../../constants.js'

class InputEditor extends React.Component {

    constructor(props) {
        super(props);

        this.fileInputRef = React.createRef();
    }

    render() {
        return (
            <div className="dialogue-editor-edit-section">
                <div className="row dialogue-editor-edit-section-audio-input">
                    <div>
                        <input key={uuidv4()} value={this.props.youtubeEmbedId}
                               onChange={(e) => this.props.onYoutubeEmbedId(e.target.value)}
                               type={"text"} placeholder="Youtube Embed Id"/>
                    </div>
                    <div className="audio-source-file-open-container">
                        <AiTwotoneFolderOpen className="audio-source-file-open"
                                             onClick={() => this.fileInputRef.current.click()}/>
                        <input type='file' ref={this.fileInputRef}
                               className="audio-file-input"
                               onChange={() => readFileAsBase64(this.fileInputRef, (value) => this.props.onAudioChange(value))}/>
                        <textarea rows={1} value={this.props.audioSource}
                                  onChange={(e) => this.props.onAudioChange(e.target.value)}
                                  type={"text"}
                                  placeholder="Dialogue audio (ogg)"/>
                    </div>
                </div>
                {this.createRows()}
            </div>
        )
    }

    createRows = () => {
        let rows = [];
        this.props.lines.forEach((row, index) => {
                rows.push(
                    <EditorRow key={index}
                               id={index}
                               row={row}
                               updateRow={this.updateRow}
                               deleteRow={() => this.deleteRow(index)}
                               addRow={() => this.addRow(index)}/>
                )
            }
        );

        return rows;
    }

    updateRow = (index, row) => {
        let lines = _.cloneDeep(this.props.lines);
        lines[index] = _.cloneDeep(row);
        this.props.updateLines(lines);
    }

    addRow = (index) => {
        let lines = _.cloneDeep(this.props.lines);
        lines.splice(index + 1, 0, _.cloneDeep(DIALOGUE_EDITOR_ROW));
        this.props.updateLines(lines);
    }

    deleteRow = (index) => {
        let lines = _.cloneDeep(this.props.lines);
        lines.splice(index, 1);
        this.props.updateLines(lines);
    }
}

export default InputEditor;