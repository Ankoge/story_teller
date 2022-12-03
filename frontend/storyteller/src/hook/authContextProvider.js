import {createContext, useContext, useState} from "react";
import fetchPost from "../fetch/fetchPost";

export const AuthContext = createContext(null);
export const useAuth = () => useContext(AuthContext);

export const AuthProvider = ({children}) => {
    const [token, setToken] = useState(localStorage.getItem(token));

    const login = (username, password) => {
        fetchPost("/login", {
            username: username,
            password: password
        }).then(response => {
            localStorage.setItem("token", response.headers.get("Authorization"));
            localStorage.setItem("username", username)
        }).catch(reason => console.log(reason))
    }
    return (
        <AuthContext.Provider value={{token, setToken, login, }}>
            {children}
        </AuthContext.Provider>
    )
}