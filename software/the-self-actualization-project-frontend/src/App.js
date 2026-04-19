import './App.css';
import TopNav from "./components/TopNav";
import {Route, Routes} from "react-router";
import Profile from "./components/user/Profile";
import MainContent from "./components/maincontent";
import Home from "./components/Home";
import Dialogue from "./components/DialogueTrainer/Dialogue";
import Footer from "./components/Footer";
import Disclaimer from "./components/Footer/Disclaimer";

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
