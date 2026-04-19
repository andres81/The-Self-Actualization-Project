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
import {MdGTranslate} from 'react-icons/md';

class EditorRowWordTranslator extends React.Component {

    render() {

        let elems = this.getTranslationBlock();

        return (
            <div>
                <div className="accordion">
                    <div className="accordion-item">
                        <h2 className="accordion-header"
                            id={"panelsStayOpen-heading" + this.props.id}>
                            <button className="accordion-button collapsed"
                                    type="button" data-bs-toggle="collapse"
                                    data-bs-target={"#word-translations-collapse-" + this.props.id}
                                    aria-expanded="true"
                                    aria-controls="panelsStayOpen-collapseOne">
                                Word Translations
                            </button>
                        </h2>
                        <div id={"word-translations-collapse-" + this.props.id}
                             className="accordion-collapse collapse"
                             aria-labelledby={"panelsStayOpen-heading" + this.props.id}>
                            <div
                                className="accordion-body dialogue-editor-word-translation-block">
                                <h4>Translations</h4>
                                {elems}
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        )
    }

    getTranslationBlock = () => {
        if (!!!this.props.row.wordTranslations) {
            return;
        }
        return this.props.row.wordTranslations.map((elem, index) => {
            return <div className="row dialogue-editor-word-translator-row"
                        key={index}>
                <div className="col-4 text-end">
                    <label className="col-form-label">{elem.left.text}</label>
                    &nbsp;&nbsp;
                    <a target="_blank"
                       href={"https://translate.google.com/?hl=en&sl=auto&tl=en&text=" + elem.left.text + "&op=translate"}
                       rel="noreferrer"><MdGTranslate/></a>
                </div>
                <div className="col-6 text-start">
                    <input className="form-control " value={elem.right.text}
                           onChange={(e) => this.onWordTranslationChange(e.target.value, index)}/>
                </div>
            </div>
        });
    }

    onWordTranslationChange = (value, index) => {
        let row = _.cloneDeep(this.props.row);
        row.wordTranslations[index].right.text = value;
        this.props.updateRow(this.props.id, row);
    }
}

export default EditorRowWordTranslator;