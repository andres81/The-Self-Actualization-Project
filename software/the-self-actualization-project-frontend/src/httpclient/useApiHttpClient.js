import {useContext, useEffect} from "react";

import axios from "axios";

import useRefresh from "../components/authentication/hooks/useRefresh";
import AuthContext from "../components/authentication/AuthContext";

/**
 * Custom HTTP Client using
 * <a href="https://axios-http.com/docs/intro">Axios library</a>.
 *
 * The use case is to utilize a preconfigured http client that knows the API
 * endpoint, plus uses the proper credentials to access said endpoints.
 *
 * The Axios client created, uses the OAuth2 Bearer token logic to authenticate.
 * I.e., adds an Authorization header to each request, and sets the value to:
 * "Bearer " + an access token. This access token is taken from the ReactJS global
 * context component: {@link AuthContext}.
 *
 * @returns {axios.AxiosInstance}
 */
const useApiHttpClient = () => {

  const {authObject} = useContext(AuthContext);
  const refresh = useRefresh();

  const instance = axios.create({
    baseURL: 'http://localhost:8080/api', withCredentials: true
  });

  useEffect(() => {

    const requestIntercept = instance.interceptors.request.use(config => {
      if (!config.headers['Authorization']) {
        config.headers['Authorization'] = `Bearer ${authObject?.accessToken}`;
      }
      return config;
    }, (error) => Promise.reject(error));

    const responseIntercept = instance.interceptors.response.use(
        response => response, async (error) => {
          const prevRequest = error?.config;
          if (error?.response?.status === 401 && !prevRequest?.sent) {
            prevRequest.sent = true;
            const newAccessToken = await refresh();
            prevRequest.headers['Authorization'] = `Bearer ${newAccessToken}`;
            return instance(prevRequest);
          }
          return Promise.reject(error);
        });

    return () => {
      instance.interceptors.request.eject(requestIntercept);
      instance.interceptors.response.eject(responseIntercept);
    }
  }, [instance, refresh, authObject])

  return instance;
}

export default useApiHttpClient;