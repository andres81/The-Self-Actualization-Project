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

import './style.scss'
import {Link} from "react-router";

function Footer() {
    return <div className="container footer-container">
        <footer className="py-3 my-4">
            <ul className="nav justify-content-center border-bottom pb-3 mb-3">
                <li className="nav-item">
                    <Link to={'/'}
                          className="nav-link px-2 text-muted">
                        Home</Link>
                </li>
                <li className="nav-item">
                    <Link to={'/features'}
                          className="nav-link px-2 text-muted">
                        Features</Link>
                </li>
                <li className="nav-item">
                    <Link to={'/faqs'}
                          className="nav-link px-2 text-muted">
                        FAQs</Link>
                </li>
                <li className="nav-item">
                    <Link to={'/about'}
                          className="nav-link px-2 text-muted">
                        About</Link>
                </li>
                <li className="nav-item">
                    <a href={'/disclaimer.html'}
                       className="nav-link px-2 text-muted">
                        Disclaimer</a>
                </li>

            </ul>
            <p className="text-center text-muted">© 2026 <Link
                target="_blank" rel="noopener noreferrer"
                to={"https://www.andreschepers.nl"}>André Schepers</Link></p>
        </footer>
    </div>
}

export default Footer;