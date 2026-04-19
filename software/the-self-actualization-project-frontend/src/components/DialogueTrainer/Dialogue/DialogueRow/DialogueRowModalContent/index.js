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
import React from 'react';

class DialogueRowModalContent extends React.Component {

    render() {

        let translations = this.props.line.wordTranslations;
        let tableRows = [];
        let lineNr = 1;

        if (translations) {
            translations.forEach((pair, index) => {
                tableRows.push(
                    <tr key={index}>
                        <th scope="row">{lineNr}</th>
                        <td>{pair.left.text}</td>
                        <td>{pair.right.text}</td>
                    </tr>);
                ++lineNr;
            });
        }

        return (
            <div className="dialogue-row-modal-content-container">

                <h1>Line breakdown</h1>

                <br/><br/>

                <p className="fw-bold fs-4 dialogue-modal-content-line">{this.props.line.text}</p>

                <p className="fw-bolder fs-4 dialogue-modal-content-line">{this.props.line.translation}</p>

                <br/>

                <h2>Vocabulary</h2>

                <br/>

                <table className="table">
                    <thead>
                    <tr>
                        <th scope="col">#</th>
                        <th scope="col">Line word</th>
                        <th scope="col">Translation</th>
                    </tr>
                    </thead>
                    <tbody>
                    {tableRows}
                    </tbody>
                </table>
            </div>

        )
    }
}

export default DialogueRowModalContent;
