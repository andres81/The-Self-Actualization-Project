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

import EditorRowWordTranslator from './EditorRowWordTranslator';
import EditorRowLineEditor from './EditorRowLineEditor';

class EditorRow extends React.Component {

    render() {
        return (
            <div
                className="row dialogue-editor-row dialogue-editor-material-design-hover">
                <EditorRowLineEditor {...this.props} />
                {this.props.row.text && this.props.row.text !== "" &&
                    <EditorRowWordTranslator
                        id={this.props.id}
                        row={this.props.row}
                        updateRow={this.props.updateRow}/>}
                <br/><br/><br/>
            </div>
        )
    }

    updateWordTranslations = (value) => {
        this.props.updateWordTranslations(value);
    }
}

export default EditorRow;
