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

import './App.css';
import TopNav from "./components/TopNav";
import {Route, Routes} from "react-router";
import Profile from "./components/user/Profile";
import MainContent from "./components/maincontent";
import Home from "./components/Home";
import Dialogue from "./components/DialogueTrainer/Dialogue";
import Footer from "./components/Footer";
import DialogueEditor from "./components/DialogueTrainer/DialogueEditor";

function App() {
    return (
        <div className="App">
            <TopNav/>
            <MainContent>
                <Routes>

                    <Route path="/" element={<Home/>}/>

                    <Route path="profile" element={<Profile/>}/>

                    <Route path="dialogue-trainer"
                           element={<Dialogue showLoadSaveButtons={true}/>}/>

                    <Route path="dialogue-editor"
                           element={<DialogueEditor showLoadSaveButtons={true}/>}/>

                    {/*<Route index element={<Home />} />*/}
                    {/*<Route path="about" element={<About />} />*/}

                    {/*<Route element={<AuthLayout />}>*/}
                    {/*  <Route path="login" element={<Login />} />*/}
                    {/*  <Route path="register" element={<Register />} />*/}
                    {/*</Route>*/}

                    {/*<Route path="concerts">*/}
                    {/*  <Route index element={<ConcertsHome />} />*/}
                    {/*  <Route path=":city" element={<City />} />*/}
                    {/*  <Route path="trending" element={<Trending />} />*/}
                    {/*</Route>*/}
                </Routes>
            </MainContent>
            <Footer/>
        </div>
    );
}

export default App;
