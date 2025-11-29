import { createContext, useState } from "react";

const AuthContext = createContext({});

export const AuthContextProvider = ({ children }) => {
  const [authObject, setAuthObject] = useState({
    at: ''
  });

  return (
      <AuthContext value={{ authObject, setAuthObject }}>
        {children}
      </AuthContext>
  )
}

export default AuthContext;