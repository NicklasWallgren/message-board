import { useContext, useState } from 'react';
import { UserSessionContext } from '../contexts/UserSessionContext';
import { createMessage, getMessages, login, register, setToken } from "../api/api";
import {
    ErrorResponse,
    LoginResponse,
    Message,
    MessageResponse,
    PageMessageResponse,
    RegisterResponse
} from "../api/models";

export default function useApi() {
    const [ errorMessage, setErrorMessage ] = useState("");
    const { setUser } = useContext(UserSessionContext);

    const registerUser = async (data: any): Promise<RegisterResponse> => {
        const { username, password } = data;

        return register({ username, password })
            .then(async (response) => {
                return Promise.resolve(response);
            }).catch((err: ErrorResponse) => {
                setErrorMessage(err.message);

                return Promise.reject(err);
            })
    };

    const loginUser = async (data: any): Promise<LoginResponse> => {
        const { username, password } = data;

        return login({ username, password })
            .then(async (response) => {
                setToken(response.token)
                setUser({ id: response.id, username: response.username, token: response.token })

                return Promise.resolve<LoginResponse>(response);
            }).catch((err: ErrorResponse) => {
                setErrorMessage(err.message);

                return Promise.reject(err);
            })
    };

    const getLatestMessages = async (): Promise<PageMessageResponse<Message>> => {
        return getMessages()
            .then(async (response) => {
                return Promise.resolve<PageMessageResponse<Message>>(response);
            }).catch((err: ErrorResponse) => {
                setErrorMessage(err.message);

                return Promise.reject(err);
            })
    };

    const addNewMessage = async (subject: string, text: string): Promise<MessageResponse> => {
        return createMessage(subject, text)
            .then(async (response) => {
                return Promise.resolve<MessageResponse>(response);
            }).catch((err: ErrorResponse) => {
                setErrorMessage(err.message);

                return Promise.reject(err);
            })
    };


    return { registerUser, loginUser, addNewMessage, getLatestMessages, errorMessage }
}
