import {useContext, useState} from "react";
import {AuthContext} from "../hook/authContextProvider"

const Login = () => {
    const {login} = useContext(AuthContext);
    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");

    function handleSubmit() {
        if(username !== "" && password !== "") {

        }else {
            login(username,password);
        }
    }

    return (<div className="login-container">
        <div>
            <input type="text"
                   name="username"></input>
        </div>
        <div>
            <input type="text"
                   name="username"></input>
        </div>
        <div>
            <button onClick={handleSubmit}>Submit</button>
        </div>
    </div>)
}

export default Login;