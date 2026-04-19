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

import {useContext, useEffect, useRef} from "react";
import axios from "axios";
import AuthContext from "../AuthContext";

import './style.css';

export default function GoogleLoginButton(props) {

    let GoogleLoginRef = useRef(null);
    const {
        setAuthObject
    } = useContext(AuthContext);

    function handleCredentialResponse(response) {

        var googleCallbackUri = process.env.REACT_APP_BACKEND_URL
            + process.env.REACT_APP_BACKEND_AUTH_GOOGLE_CALLBACK_PATH;
        axios.post(googleCallbackUri,
            {'googleIdToken': response.credential}, {withCredentials: true}).then(
            response => {
                setAuthObject(prev => {
                    return {...prev, accessToken: response.data.accessToken}
                });
            });
        if (props?.afterLoginCallback) {
            props?.afterLoginCallback();
        }
    }

    function loadGoogle() {

        window.google.accounts.id.initialize({
            client_id: process.env.REACT_APP_GOOGLE_CLIENT_ID,
            callback: handleCredentialResponse
        });

        window.google.accounts.id.renderButton(
            GoogleLoginRef.current,
            {theme: "outline", size: "large"}  // customization attributes
        );
        // window.google.accounts.id.prompt(); // also display the One Tap dialog
    }

    useEffect(() => {
        const script = document.createElement('script');
        script.src = 'https://accounts.google.com/gsi/client';
        script.onload = loadGoogle;
        document.body.appendChild(script);

        return () => {
            document.body.removeChild(script);
        };
    });

    return (
        <div className="google-login-button-wrapper" ref={GoogleLoginRef}></div>
    )
};
