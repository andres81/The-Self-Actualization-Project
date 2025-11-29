import './App.css';
import TestCookies from "./components/test-cookies";
import GoogleLogin from "./components/authentication/GoogleLoginButton";

function App() {
  return (
    <div className="App">
      <br /><br />
      <TestCookies />
      <GoogleLogin/>
    </div>
  );
}

export default App;
