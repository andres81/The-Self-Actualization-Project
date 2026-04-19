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

import './style.css'
import useAxiosHttpClient from "../../../httpclient/useApiHttpClient";
import {useContext, useEffect} from "react";
import {NavLink} from "react-router";
import AuthContext from "../../authentication/AuthContext";

function TopNavUserInformation() {

    const httpClient = useAxiosHttpClient();
    const {authObject, setAuthObject} = useContext(AuthContext);

    useEffect(() => {
        httpClient.get('/user/info').then(
            (response) => {
                if (authObject.username === response.data.username) {
                    return;
                }
                setAuthObject(prev => {
                    return {
                        ...prev,
                        username: response.data.username
                    }
                });
            },
            (error) => console.error("Error: ", error));
    }, [setAuthObject, authObject, httpClient]);

    return (
        <li className="nav-item dropdown no-arrow show top-nav-user-information">
        <span className="nav-link dropdown-toggle" id="navbarDropdown"
              role="button" data-bs-toggle="dropdown" aria-expanded="false">
          {authObject.username}
        </span>
            <ul className="dropdown-menu top-nav-user-information-popup"
                aria-labelledby="navbarDropdown">
                <li>
                    <NavLink className="dropdown-item" activeclassname="active"
                             to="/profile">
                        <i
                            className="fas fa-user fa-sm fa-fw mr-2 faicon"></i> Profile
                    </NavLink>
                </li>
                <li><span className="dropdown-item"><i
                    className="fas fa-cogs fa-sm fa-fw mr-2 faicon"></i> Settings </span>
                </li>
                <li>
                    <hr className="dropdown-divider"/>
                </li>
                <li><span className="dropdown-item"><i
                    className="fas fa-sign-out-alt fa-sm fa-fw mr-2 faicon"></i> Logout </span>
                </li>
            </ul>
        </li>
    )
}

export default TopNavUserInformation;