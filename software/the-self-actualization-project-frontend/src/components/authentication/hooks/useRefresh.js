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

import {useContext} from "react";

import axios from "axios";

import AuthContext from "../AuthContext";

const useRefresh = () => {

    const {setAuthObject} = useContext(AuthContext);
    const client = axios.create({
        baseURL: process.env.REACT_APP_BACKEND_URL,
        withCredentials: true
    });

    return async () => {
        try {
            const response = await client.get(
                process.env.REACT_APP_BACKEND_AUTH_REFRESH_ENDPOINT_PATH);
            setAuthObject(prev => {
                return {
                    ...prev,
                    accessToken: response.data.accessToken
                }
            });
            return response.data.accessToken;
        } catch (error) {
            console.info('Refreshing failed: ', error.message);
        }
    };
};

export default useRefresh;
