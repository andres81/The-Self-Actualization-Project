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
import {FaPlus, FaTrashAlt} from 'react-icons/fa';
import _ from "lodash";
import {splitSentenceUpInWords} from "../../../../../../util/StringUtils";
import TextareaAutosize from 'react-textarea-autosize';
import {AiTwotoneFolderOpen} from 'react-icons/ai';
import {v4 as uuidv4} from 'uuid';

import {readFileAsBase64} from '../../../../../../util/FileUtils';

class EditorRowLineEditor extends React.Component {

    constructor(props) {
        super(props);
        this.fileInputRef = React.createRef();
    }

    render() {

        const {row} = this.props;

        let radioDialogueLineId = uuidv4();
        let radioTextLineId = uuidv4();

        return (
            <div>
                <div className="row dialogue-editor-row-line-editor">
                    <div className="col-sm-4 dialogue-editor-row-input">
                        <input
                            type="text" {...this.getInputAttributes("Person name", row.personName, 'personName')} />
                        <div className="form-radio-container">
                            {this.createLineTypeInput(row, radioDialogueLineId, "dialogue-line")}
                            <label className="form-check-label"
                                   htmlFor={radioDialogueLineId}>
                                Dialogue line
                            </label>
                        </div>
                        <div className="form-radio-container">
                            {this.createLineTypeInput(row, radioTextLineId, "dialogue-textblock")}
                            <label className="form-check-label"
                                   htmlFor={radioTextLineId}>
                                Text line
                            </label>
                        </div>
                    </div>
                    <div className="col text-start dialogue-editor-row-input">
                        <div>
                            <TextareaAutosize {...this.getInputAttributes('Text', row.text, "text")} />
                        </div>
                        <div
                            className="dialogue-editor-audio-source-file-open-container">
                            <AiTwotoneFolderOpen
                                className="dialogue-editor-audio-source-file-open"
                                onClick={() => this.fileInputRef.current.click()}/>
                            <input type='file'
                                   ref={this.fileInputRef}
                                   className="audio-file-input"
                                   onChange={() => readFileAsBase64(this.fileInputRef, (value) => this.updateOnChange("audioSource", value))}/>
                            <textarea
                                rows={1} {...this.getInputAttributes('Audio source (ogg) as URL or chosen file', row.audioSource, "audioSource")} />
                        </div>
                        <div>
                            <TextareaAutosize {...this.getInputAttributes('Translation', row.translation, "translation")} />
                        </div>
                    </div>
                    <div className="col-sm-1 dialogue-editor-row-controls">
                        <span className="dialogue-editor-row-controls-button">
                            <FaPlus className="fa-plus"
                                    onClick={this.props.addRow}/>
                            &nbsp;&nbsp;
                            <FaTrashAlt className="fa-trash-alt"
                                        onClick={this.props.deleteRow}/>
                        </span>
                    </div>
                </div>
            </div>
        )
    }

    createLineTypeInput = (row, radioLineId, type) => {
        return <input className="form-check-input"
                      type="radio"
                      name={uuidv4()}
                      id={radioLineId}
                      checked={row.type === type}
                      onChange={(e) => {
                          this.updateOnChange("type", type);
                      }}/>
    }

    getInputAttributes(placeholderText, value, fieldName) {
        return {
            onChange: (e) => this.updateOnChange(fieldName, e.target.value),
            className: "form-control dialogue-editor-row-input",
            placeholder: placeholderText,
            value: value,
        };
    }

    updateOnChange = (fieldName, value) => {
        let row = _.cloneDeep(this.props.row);
        row[fieldName] = value;
        this.updateWordTranslations(this.props.row, row);
        this.props.updateRow(this.props.id, row);
    }

    updateWordTranslations = (oldRow, row) => {
        let splitText = splitSentenceUpInWords(row.text);
        let translations = this.recycleOldTranslations(splitText, row.wordTranslations);

        if (splitText.length > translations.length) {
            let extraPairs = [];
            for (let i = 0; i < splitText.length - translations.length; ++i) {
                extraPairs.push({left: {text: ""}, right: {text: ""}});
            }
            translations = translations.concat(extraPairs);
        } else if (splitText.length < translations.length) {
            translations = translations.slice(0, splitText.length);
        }
        translations.forEach((translation, index) => {
            translation.left.text = splitText[index];
        });
        row.wordTranslations = translations;
    }

    recycleOldTranslations = (words, translations) => {
        if (!!!translations) {
            translations = [];
        }
        let leftTranslations = [];
        for (let i = 0; i < words.length && i < translations.length; ++i) {
            if (words[i].toUpperCase() === translations[i].left.text.toUpperCase()) {
                leftTranslations.push(translations[i]);
            } else {
                break;
            }
        }

        let rightTranslations = [];
        let splitTextLastIndex = words.length - 1;
        let translationsLastIndex = translations.length - 1;
        for (let i = 0; i < words.length && i < translations.length; ++i) {
            if (words[splitTextLastIndex - i].toUpperCase() === translations[translationsLastIndex - i].left.text.toUpperCase()) {
                rightTranslations.push(translations[translations.length - 1 - i]);
            } else {
                break;
            }
        }
        rightTranslations = rightTranslations.reverse();
        if (leftTranslations.length > 0 && rightTranslations.length === 0) {
            return leftTranslations;
        } else if (leftTranslations.length === 0 && rightTranslations.length > 0) {
            return this.createArrayEmptyTranslations(words.length - rightTranslations.length).concat(rightTranslations);
        }

        let leftLen = leftTranslations.length;
        let rightLen = rightTranslations.length;
        let diffLen = words.length - (leftLen + rightLen);
        let newArray = leftTranslations.concat(this.createArrayEmptyTranslations(diffLen).concat(rightTranslations));
        return newArray;
    }

    createArrayEmptyTranslations = (nofTranslations) => {
        let translations = [];
        for (let i = 0; i < nofTranslations; ++i) {
            translations.push({left: {text: ""}, right: {text: ""}});
        }
        return translations;
    }
}

export default EditorRowLineEditor;
