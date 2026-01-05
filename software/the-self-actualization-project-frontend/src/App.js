import './App.css';
import TopNav from "./components/TopNav";
import {Route, Routes} from "react-router";
import Profile from "./components/user/Profile";
import Maincontent from "./components/maincontent";
import Home from "./components/Home";

function App() {
  return (
      <div className="App">
        <TopNav/>
        <Maincontent>
          <Routes>

            <Route path="/" element={<Home/>}/>

            <Route path="profile" element={<Profile/>}/>


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
        </Maincontent>
      </div>
  );
}

export default App;
