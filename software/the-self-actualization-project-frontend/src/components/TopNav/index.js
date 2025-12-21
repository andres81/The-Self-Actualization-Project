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

import TopNavAccountManagement from "./TopNavAccountManagement";
import {Link} from "react-router";

import './style.css'

function TopNav() {

  return (
      <nav className="navbar navbar-expand-lg navbar-light bg-light shadow">
        <div className="container-fluid">
          <Link to={'/'} className="navbar-brand">The Self-Actualization
            Project
          </Link>
          <button className="navbar-toggler" type="button"
                  data-bs-toggle="collapse"
                  data-bs-target="#navbarSupportedContent"
                  aria-controls="navbarSupportedContent" aria-expanded="false"
                  aria-label="Toggle navigation">
            <span className="navbar-toggler-icon"></span>
          </button>
          <div className="collapse navbar-collapse" id="navbarSupportedContent">
            <ul className="navbar-nav ms-auto mb-2 mb-lg-0">
              <TopNavAccountManagement />
            </ul>
          </div>
        </div>
      </nav>
  )
}

export default TopNav;
