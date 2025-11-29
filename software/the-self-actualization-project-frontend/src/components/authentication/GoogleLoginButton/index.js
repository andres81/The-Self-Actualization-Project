import {useContext, useEffect, useRef} from "react";
import axios from "axios";
import AuthContext from "../AuthContext";

export default function GoogleLoginButton() {

  let GoogleLoginRef = useRef(null);
  const {
    setAuthObject
  } = useContext(AuthContext);

  function handleCredentialResponse(response) {

    var googleCallbackUri = process.env.REACT_APP_BACKEND_URL
        + process.env.REACT_APP_BACKEND_AUTH_GOOGLE_CALLBACK_PATH;
    axios.post(googleCallbackUri,
        {'googleIdToken': response.credential}, {withCredentials: true}).then(
        response => {
          setAuthObject(prev => {
            return {...prev, accessToken: response.data.accessToken}
          });
        });
  }

  function loadGoogle() {

    window.google.accounts.id.initialize({
      client_id: process.env.REACT_APP_GOOGLE_CLIENT_ID,
      callback: handleCredentialResponse
    });

    window.google.accounts.id.renderButton(
        GoogleLoginRef.current,
        {theme: "outline", size: "large"}  // customization attributes
    );
    // window.google.accounts.id.prompt(); // also display the One Tap dialog
  }

  useEffect(() => {
    const script = document.createElement('script');
    script.src = 'https://accounts.google.com/gsi/client';
    script.onload = loadGoogle;
    document.body.appendChild(script);

    return () => {
      document.body.removeChild(script);
    };
  });

  return (
      <div ref={GoogleLoginRef}></div>
  )
};
