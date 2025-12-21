import './style.css'
import useAxiosHttpClient from "../../../httpclient/useApiHttpClient";
import {useContext, useEffect, useState} from "react";
import {NavLink} from "react-router";
import AuthContext from "../../authentication/AuthContext";

function TopNavUserInformation() {

  const httpClient = useAxiosHttpClient();
  const {authObject, setAuthObject} = useContext(AuthContext);

  useEffect(() => {
    httpClient.get('/user/info').then(
        (response) => {
          if (authObject.username === response.data.username) {
            return;
          }
          setAuthObject(prev => {
            return {
              ...prev,
              username: response.data.username
            }
          });
        },
        (error) => console.error("Error: ", error));
  }, [setAuthObject, authObject, httpClient]);

  return (
      <li className="nav-item dropdown no-arrow show top-nav-user-information">
        <span className="nav-link dropdown-toggle" id="navbarDropdown"
              role="button" data-bs-toggle="dropdown" aria-expanded="false">
          {authObject.username}
        </span>
        <ul className="dropdown-menu top-nav-user-information-popup"
            aria-labelledby="navbarDropdown">
          <li>
            <NavLink className="dropdown-item" activeClassName="active"
                     to="/profile">
              <i
                  className="fas fa-user fa-sm fa-fw mr-2 faicon"></i> Profile
            </NavLink>
          </li>
          <li><span className="dropdown-item"><i
              className="fas fa-cogs fa-sm fa-fw mr-2 faicon"></i> Settings </span>
          </li>
          <li>
            <hr className="dropdown-divider"/>
          </li>
          <li><span className="dropdown-item"><i
              className="fas fa-sign-out-alt fa-sm fa-fw mr-2 faicon"></i> Logout </span>
          </li>
        </ul>
      </li>
  )
}

export default TopNavUserInformation;