import { useContext, useState } from 'react';
import { useHistory } from 'react-router-dom';
import { UserSessionContext } from '../contexts/UserSessionContext';
import { login, register } from "../api/api";
import { ErrorResponse } from "../api/models";

export default function useApi() {
    const history = useHistory();
    const [ errorMessage, setErrorMessage ] = useState("");
    const { setUser } = useContext(UserSessionContext);

    const registerUser = async (data: any) => {
        const { username, password } = data;

        return register({ username, password })
            .then(async (response) => {
                history.push('/home');
            }).catch((err: ErrorResponse) => {
                setErrorMessage(err.message);
            })
    };

    const loginUser = async (data: any) => {
        const { username, password } = data;

        return login({ username, password })
            .then(async (response) => {
                setUser({ id: response.id, username: response.username, token: response.token })
                history.push('/home');
            }).catch((err: ErrorResponse) => {
                setErrorMessage(err.message);
            })
    };

    return { registerUser, loginUser, errorMessage }
}
