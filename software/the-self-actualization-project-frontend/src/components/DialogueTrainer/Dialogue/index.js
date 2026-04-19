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
import ReactModal from "react-modal";

import {FaPause, FaPlay, FaStop, FaTimes, FaUniversity} from 'react-icons/fa';
import {AiOutlineDownload, AiTwotoneFolderOpen} from 'react-icons/ai';

import DialogueRow from './DialogueRow'
import DialogueInfoModal from './DialogueInfoModal';
import {downloadJson, readFile} from '../../../util/FileUtils';
import {html5AudioIsPlaying} from '../../../util/AudioUtils';

import EmbedYoutube from './EmbedYoutube';

import './style.scss'

class Dialogue extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            dialogueJson: this.props.dialogueJson
        };
        this.fileInputRef = React.createRef();
    }

    componentDidUpdate(prevProps, prevState) {
        if (this.props !== prevProps) {
            if (!!this.props.dialogueJson) {
                let audio = new Audio(this.props.dialogueJson.audioSource);
                audio.addEventListener("ended", this.audioEnded);
                this.setState({audio: audio});
            }
            this.setState({
                dialogueJson: this.props.dialogueJson,
            })
        }
    }

    render() {
        let isLoaded = !!this.state.dialogueJson
        let title = isLoaded ? this.state.dialogueJson.title : "";
        let embedId = isLoaded ? this.state.dialogueJson.youtubeEmbedId : "";

        return (
            <div className="dialogue-container">
                <h1 className="dialogue-title">{title}</h1>
                <br/>
                {embedId &&
                    <EmbedYoutube
                        embedId={this.state.dialogueJson.youtubeEmbedId}/>
                }
                <br/>
                <div className="dialogue-inner-container container">
                    {this.getControls()}
                    {this.getRows()}
                    <div className="dialogue-container-bottom-padding"/>
                </div>
                <ReactModal isOpen={this.state.showDialogueModal}
                            className="modal-inner"
                            portalClassName="dialogue-info-modal-bg"
                            ariaHideApp={false}>
                    <FaTimes className="dialogue-modal-close-button-icon"
                             onClick={() => this.setState({showDialogueModal: false})}/>
                    <DialogueInfoModal dialogue={this.state.dialogueJson}/>
                </ReactModal>
            </div>
        );
    }

    getRows = () => {
        if (!this.state.dialogueJson) {
            return [];
        }
        const dialogueRowElems = [];
        const lines = this.state.dialogueJson.lines;
        const names = new Set();
        lines.forEach((line, index) => {
            let classes = "";
            if (line.type !== "dialogue-textblock") {
                names.add(line.personName);
                classes = 'dialogue-row-' + ([...names].indexOf(line.personName) + 1);
            } else {
                classes = 'dialogue-row-0';
            }
            dialogueRowElems.push(
                <DialogueRow key={index} line={line} classes={classes}
                             translationEnabled={!!this.state.translationEnabled}/>
            );
        });
        return dialogueRowElems;
    }

    getControls = () => {
        let isLoaded = !!this.state.dialogueJson
        let showFileControls = this.props.showFileControls === undefined || !!this.props.fileControls;
        return <div className="dialogue-controls row text-start">
            <div className="col-sm-6 dialogue-controls-inner">
                {this.createAudiobutton()}
                &nbsp;
                {isLoaded && <div
                    className="dialogue-controls-translation form-check form-switch">
                    <label className="form-check-label"
                           htmlFor="flexSwitchCheckChecked">Dialogue
                        translation</label>
                    <input onChange={this.toggleTranslation}
                           className="form-check-input" type="checkbox"
                           id="flexSwitchCheckChecked"/>
                </div>}
                {isLoaded &&
                    <FaUniversity className="dialogue-controls-exercise-button"
                                  onClick={() => this.setState({showDialogueModal: true})}/>}
                {showFileControls && this.props.showLoadSaveButtons &&
                    <AiTwotoneFolderOpen
                        className="dialogue-controls-load-button"
                        onClick={() => this.fileInputRef.current.click()}/>}
                {showFileControls && this.props.showLoadSaveButtons &&
                    <AiOutlineDownload
                        className="dialogue-controls-download-button"
                        onClick={() => downloadJson(this.state.dialogueJson.title, this.state.dialogueJson)}/>}
                {showFileControls && <input type='file' ref={this.fileInputRef}
                                            className="file-input"
                                            onChange={() => readFile(this.fileInputRef, this.processFileContents)}/>}
            </div>
        </div>;
    }

    createAudiobutton = () => {
        if (this.state.dialogueJson && !!this.state.dialogueJson.audioSource) {
            return <span className="dialogue-audio-play-button">
                <FaStop onClick={() => {
                    let audio = this.state.audio;
                    audio.pause();
                    audio.currentTime = 0;
                    this.setState({isAudioPlaying: false});
                }}/>
                &nbsp;&nbsp;
                {!this.state.isAudioPlaying &&
                    <FaPlay onClick={this.audioPlay}/>}
                {this.state.isAudioPlaying &&
                    <FaPause onClick={this.audioPaused}/>}
            </span>
        }
    }

    audioPaused = () => {
        let audio = this.state.audio;
        audio.pause();
        this.setState({isAudioPlaying: false});
    }

    audioEnded = () => {
        this.setState({isAudioPlaying: false});
    }

    audioPlay = () => {
        let audio = this.state.audio;
        if (audio.ended) {
            audio.currentTime = 0;
        }
        this.state.audio.play();
        this.setState({isAudioPlaying: true});
    }

    isAudioPlaying = () => {
        return html5AudioIsPlaying(this.state.audio);
    }

    toggleTranslation = () => {
        this.setState({translationEnabled: !!!this.state.translationEnabled});
    }

    processFileContents = (fileText) => {
        try {
            let a = JSON.parse(fileText);
            let audio = new Audio(a.audioSource);
            audio.addEventListener("ended", this.audioEnded);
            this.setState(
                {
                    dialogueJson: a,
                    audio: audio
                }
            );
        } catch (e) {
            console.error(e);
        }
    }
}

export default Dialogue;