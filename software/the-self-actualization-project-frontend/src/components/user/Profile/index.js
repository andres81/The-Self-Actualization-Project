import './style.css'
import {useContext, useState} from "react";
import AuthContext from "../../authentication/AuthContext";
import useAxiosHttpClient from "../../../httpclient/useApiHttpClient";

function updateUsername(httpClient, newUsername, setAuthObject, callback) {
  httpClient.put('/user/update-username/' + newUsername).then(
      (response) => callback(setAuthObject, response.data),
      (error) => console.error("Error: ", error));
}

function updateUsernameLocally(setAuthObject, newUsername) {
  setAuthObject(prev => {
    return {
      ...prev, username: newUsername
    }
  });
}

function Profile() {

  const httpClient = useAxiosHttpClient();
  const {authObject, setAuthObject} = useContext(AuthContext);
  const [username, setUsername] = useState("")

  return (

      <>

        <div className="row">

          <div className="col-lg-6 mb-12">

            <div className="card shadow mb-4">

              <div className="card-header py-3">
                <h6 className="m-0 font-weight-bold text-start profile-header">Profile</h6>
              </div>

              <div className="card-body">

                <label htmlFor="basic-url" className="form-label">Current
                  username:</label>
                <div className="input-group mb-3">
                  <span className="input-group-text"
                        id="basic-addon3">{authObject.username}</span>
                </div>

                <div className="input-group mb-3">
                  <span className="input-group-text" id="basic-addon1">@</span>
                  <input value={username} type="text" className="form-control"
                         placeholder="Username"
                         aria-label="Username" aria-describedby="basic-addon1"
                         onChange={(e) => setUsername(e.target.value)}/>
                  <button onClick={() => updateUsername(httpClient, username,
                      setAuthObject, updateUsernameLocally)}
                          className="btn btn-outline-secondary" type="button"
                          id="button-addon2">Save
                  </button>
                </div>

              </div>

            </div>

          </div>
        </div>
      </>)
}

export default Profile;