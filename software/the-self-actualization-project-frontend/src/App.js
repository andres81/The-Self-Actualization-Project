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
import TopNav from "./components/webpage-components/TopNav";
import {Route, Routes} from "react-router";
import Profile from "./components/user/Profile";
import MainContent from "./components/webpage-components/maincontent";
import Home from "./components/webpage-components/Home";
import Dialogue
    from "./components/webpage-components/TheBeast/DialogueTrainer/Dialogue";
import Footer from "./components/webpage-components/Footer";
import DialogueEditor
    from "./components/webpage-components/TheBeast/DialogueTrainer/DialogueEditor";
import TheBeast from "./components/webpage-components/TheBeast";
import About from "./components/webpage-components/About";
import QuestionnaireUtil
    from "./components/webpage-components/TheBeast/QuestionnaireUtil";
import QUAbout
    from "./components/webpage-components/TheBeast/QuestionnaireUtil/QUAbout";
import QuestionnaireTrainer
    from "./components/webpage-components/TheBeast/QuestionnaireUtil/QuestionnaireTrainer";

function App() {
    return (
        <div className="App">
            <TopNav/>
            <MainContent>
                <Routes>

                    <Route path="/" element={<Home/>}/>

                    <Route path="profile" element={<Profile/>}/>

                    <Route path="about" element={<About/>}/>

                    <Route path="the-beast/about"
                           element={<TheBeast/>}/>

                    <Route path="the-beast/dialogue-trainer"
                           element={<Dialogue showLoadSaveButtons={true}/>}/>

                    <Route path="the-beast/dialogue-editor"
                           element={<DialogueEditor
                               showLoadSaveButtons={true}/>}/>

                    <Route path="the-beast/questionnaire-util"
                           element={<QuestionnaireUtil/>}>
                        <Route index element={<QUAbout />} />
                        <Route path="trainer" element={<QuestionnaireTrainer />} />
                    </Route>

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
            {/*<TTSComponentTester/>*/}
            <Footer/>
        </div>
    );
}

export default App;
