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

import TextareaAutosize from 'react-textarea-autosize';

import './_style.scss';

class ManualEditing extends React.Component {

    constructor(props) {
        super(props);
        this.state = {buffer: ""};
    }

    render() {

        let json = JSON.stringify(this.props.json, null, 4);
        let colorClass = "dialogue-manual-editor";
        if (this.state.useBuffer) {
            json = this.state.buffer;
            colorClass += " dialogue-manual-editor-error";
        }

        return (
            <div className="container">
                <TextareaAutosize className={colorClass} value={json}
                                  onChange={this.onChange}/>
            </div>
        )
    }

    onChange = (textareaEvent) => {
        try {
            let dialogue = JSON.parse(textareaEvent.target.value);
            this.props.updateDialogue(dialogue);
            this.setState({useBuffer: false});
        } catch (e) {
            this.setState({
                useBuffer: true,
                buffer: textareaEvent.target.value
            });
        }

    }
}

export default ManualEditing;