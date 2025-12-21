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

import './style.css'

function ReactModal(props) {

  return (
      <div className="react-modal">
        <i
            onClick={() => props.closeModalCallback()}
            className="fa-regular fa-circle-xmark fa-3x react-modal-close-button"></i>
        {props.children}
      </div>
  );
}

export default ReactModal;
