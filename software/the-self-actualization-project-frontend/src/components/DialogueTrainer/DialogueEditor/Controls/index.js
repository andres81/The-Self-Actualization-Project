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
import ReactModal from 'react-modal';


import {FaTimes} from 'react-icons/fa';
import {FcInfo} from 'react-icons/fc';
import {IconContext} from "react-icons";
import {AiOutlineDownload, AiTwotoneFolderOpen} from 'react-icons/ai';
import {downloadJson, readFile} from '../../../../util/FileUtils';

class Controls extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            base64: "",
            dialogue: {
                title: "",
                lines: []
            }
        };
        this.fileInputRef = React.createRef();
    }

    render() {
        return (
            <div
                className="dialogue-editor-controls dialogue-editor-material-design-hover">
                <div className="row">
                    <div className="col-sm-3 controls-buttons-wrapper">
                        <button onClick={this.props.addRow}
                                type="button"
                                className="btn btn-primary">
                            Add row
                        </button>
                        &nbsp;&nbsp;&nbsp;
                        <button
                            onClick={() => this.fileInputRef.current.click()}
                            type="button"
                            className="btn btn-primary">
                            <AiTwotoneFolderOpen
                                className="dialogue-controls-load-button"/>
                        </button>
                        &nbsp;&nbsp;&nbsp;
                        <button
                            onClick={() => downloadJson(this.props.dialogue.title, this.props.dialogue)}
                            type="button"
                            className="btn btn-primary">
                            <AiOutlineDownload
                                className="dialogue-controls-download-button"/>
                        </button>
                        <input type='file' ref={this.fileInputRef}
                               className="file-input"
                               onChange={() => readFile(this.fileInputRef, this.processFileContents)}/>
                    </div>
                    <div className="col-sm-9">
                        <div className="row">
                            <div className="col-sm-9 controls-input-wrapper">
                                <input
                                    onChange={(e) => this.props.updateTitle(e.target.value)}
                                    type="text"
                                    className="form-control title-input"
                                    placeholder="Title"
                                    value={this.props.title}/>
                                <div className="base64-info-icon-wrapper">
                                    <input
                                        onChange={(e) => this.props.onChangeBase64(e.target.value)}
                                        type="text"
                                        className="form-control"
                                        placeholder="Base64 dialogue"
                                        value={this.props.base64}/>
                                    <IconContext.Provider value={{
                                        size: "25px",
                                        className: "base64-info-icon"
                                    }}>
                                        <FcInfo
                                            onClick={this.onBase64InfoButtonClick}/>
                                    </IconContext.Provider>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                {!!this.state.showBase64InfoModal &&
                    <ReactModal isOpen={this.state.showBase64InfoModal}
                                portalClassName="dialogue-editor-controls-outer"
                                className="dialogue-modal-inner"
                                ariaHideApp={false}>
                        <FaTimes className="dialogue-modal-close-button-icon"
                                 onClick={() => this.setState({showBase64InfoModal: false})}/>
                        <p className="dialogue-editor-controls-modal-innertext">
                            The Base64 string is the JSON object that represents
                            this dialogue you are editing.
                            <br/><br/>
                            This string makes it easy for you to share it with
                            anyone. Anyone having this string can just
                            copy paste into this field and will have your
                            current dialogue.
                        </p>
                    </ReactModal>
                }
            </div>
        )
    }

    onBase64InfoButtonClick = (e) => {
        this.setState({showBase64InfoModal: true});
    }

    processFileContents = (fileText) => {
        try {
            let a = JSON.parse(fileText);
            this.props.updateDialogue(a);
        } catch (e) {
            console.error(e);
        }
    }
}

export default Controls;