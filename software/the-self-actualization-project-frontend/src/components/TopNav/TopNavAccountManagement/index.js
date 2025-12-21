/*
 * Copyright 2025 André Schepers
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

import ReactModal from "../../ReactModal";
import {useContext, useState} from "react";

import './style.css'
import GoogleLoginButton from "../../authentication/GoogleLoginButton";
import AuthContext from "../../authentication/AuthContext";
import TopNavUserInformation from "../TopNavUserInformation";
import useRefresh from "../../authentication/hooks/useRefresh";

function TopNavAccountManagement() {

  const [modalOpenened, setModalOpenened] = useState(false);
  const {authObject} = useContext(AuthContext);

  const refresh = useRefresh();

  var accessToken = authObject?.accessToken;
  if (!!!accessToken) {
    refresh();
  }

  return (<>
    {!authObject?.accessToken &&
        <li className="nav-item">
          <button onClick={() => setModalOpenened(true)} type="button"
                  className="btn btn-warning">Login
          </button>
        </li>
    }
    {authObject?.accessToken &&
        <TopNavUserInformation />
    }
    {modalOpenened && <ReactModal modalOpenened={modalOpenened}
                                  closeModalCallback={() => setModalOpenened(
                                      false)}>
      <div className="top-nav-account-modal-login-buttons-container">
        <GoogleLoginButton afterLoginCallback={() => setModalOpenened(false)}/>
      </div>
    </ReactModal>}
  </>);
}

export default TopNavAccountManagement;
