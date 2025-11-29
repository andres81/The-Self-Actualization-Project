import {useContext} from "react";

import axios from "axios";

import AuthContext from "../AuthContext";

const useRefresh = () => {

  const {setAuthObject} = useContext(AuthContext);
  const client = axios.create({
    baseURL: process.env.REACT_APP_BACKEND_URL,
    withCredentials: true
  });

  return async () => {
    try {
      const response = await client.get(
          process.env.REACT_APP_BACKEND_AUTH_REFRESH_ENDPOINT_PATH);
      setAuthObject(prev => {
        return {
          ...prev,
          accessToken: response.data.accessToken
        }
      });
      return response.data.accessToken;
    } catch (error) {
      return Promise.reject(error);
    }
  };
};

export default useRefresh;
