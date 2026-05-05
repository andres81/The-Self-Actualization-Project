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

import {Link, NavLink} from "react-router";

import './style.scss'

function TopNav() {

    return (<nav
        className="navbar navbar-expand-lg navbar-light bg-light shadow justify-content-start">
        <div className="container-fluid">
            <Link to={'/'} className="navbar-brand">The Self-Actualization
                Project
            </Link>
            <button className="navbar-toggler" type="button"
                    data-bs-toggle="collapse"
                    data-bs-target="#navbarNavDropdown"
                    aria-controls="navbarNavDropdown" aria-expanded="false"
                    aria-label="Toggle navigation">
                <span className="navbar-toggler-icon"></span>
            </button>

            {/*<NavLink to="/dialogue-trainer"*/}
            {/*         className={({isActive}) => (isActive ? 'active' +*/}
            {/*             ' nav-link' : 'nav-link')}>Dialogue*/}
            {/*    Trainer</NavLink>*/}
            {/*<NavLink to="/dialogue-editor"*/}
            {/*         className={({isActive}) => (isActive ? 'active' +*/}
            {/*             ' nav-link' : 'nav-link')}>Dialogue*/}
            {/*    Editor</NavLink>*/}
            {/*<NavLink to="/the-beast"*/}
            {/*         className={({isActive}) => (isActive ? 'active' +*/}
            {/*             ' nav-link' : 'nav-link')}>The B.E.A.S.T.</NavLink>*/}

            <div className="collapse navbar-collapse"
                 id="navbarNavDropdown">
                <ul className="navbar-nav">
                    <li className="nav-item">
                        <NavLink to={'/'} className="nav-link">Home</NavLink>
                    </li>
                    <li className="nav-item dropdown">
                        <a className="nav-link dropdown-toggle" href="/"
                           id="navbarDropdownMenuLink" role="button"
                           data-bs-toggle="dropdown" aria-expanded="false">
                            The B.E.A.S.T.
                        </a>
                        <ul className="dropdown-menu"
                            aria-labelledby="navbarDropdownMenuLink">
                            <li>
                                <NavLink to="/the-beast/about"
                                         className={({isActive}) => ('dropdown-item' + (isActive ? ' active' : ''))}>
                                    About
                                </NavLink>
                            </li>
                            <li>
                                <NavLink to="/the-beast/dialogue-trainer"
                                         className={({isActive}) => ('dropdown-item' + (isActive ? ' active' : ''))}>
                                    Dialogue Trainer
                                </NavLink>
                            </li>
                            <li>
                                <NavLink to="/the-beast/dialogue-editor"
                                         className={({isActive}) => ('dropdown-item' + (isActive ? ' active' : ''))}>
                                    Dialogue Editor
                                </NavLink>
                            </li>
                            <li>
                                <NavLink
                                    to="/the-beast/questionnaire-util"
                                    className={({isActive}) => ('dropdown-item' + (isActive ? ' active' : ''))}>
                                    Questionnaire Util
                                </NavLink>
                            </li>
                        </ul>
                    </li>
                    <li>
                        <NavLink
                            to="/resources"
                            className={({isActive}) => ('nav-link' + (isActive ? ' active' : ''))}>
                            Resources
                        </NavLink>
                    </li>
                    <li className="nav-item">
                        <NavLink to={'/about'}
                                 className="nav-link">About</NavLink>
                    </li>
                </ul>
            </div>

            {/*<button className="navbar-toggler" type="button"*/}
            {/*        data-bs-toggle="collapse"*/}
            {/*        data-bs-target="#navbarSupportedContent"*/}
            {/*        aria-controls="navbarSupportedContent"*/}
            {/*        aria-expanded="false"*/}
            {/*        aria-label="Toggle navigation">*/}
            {/*    <span className="navbar-toggler-icon"></span>*/}
            {/*</button>*/}
            {/*<div className="collapse navbar-collapse"*/}
            {/*     id="navbarSupportedContent">*/}
            {/*    <ul className="navbar-nav ms-auto mb-2 mb-lg-0">*/}
            {/*        <TopNavAccountManagement/>*/}
            {/*    </ul>*/}
            {/*</div>*/}
        </div>
    </nav>)
}

export default TopNav;
