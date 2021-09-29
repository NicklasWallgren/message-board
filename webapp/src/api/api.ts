import axios from "axios";
import {
    ErrorResponse,
    LoginResponse,
    Message,
    OnError,
    OnUnauthorized,
    PageMessageResponse,
    RegisterResponse,
    UserCredentials
} from "./models";

const apiClient = axios.create();

let token: string | undefined;
let onError: OnError | undefined;
let onUnauthorized: OnUnauthorized | undefined;

apiClient.defaults.baseURL = "http://localhost:8080/api"; // TODO
apiClient.defaults.headers.common["Content-Type"] = "application/json";
apiClient.defaults.headers.common["Access-Control-Allow-Origin"] = "*";
apiClient.interceptors.request.use((config) => {
    if (token) {
        config.headers.Authorization = `Bearer ${ token }`;
    }

    return Promise.resolve(config);
});

export const register = async ({ username, password }: UserCredentials): Promise<RegisterResponse> => {
    return apiClient.post<RegisterResponse>(`/auth/register`, { username, password })
        .then(response => {
            return response.data
        }).catch(error => {
            return Promise.reject(error.response.data as ErrorResponse);
        });
}

export const login = async ({ username, password }: UserCredentials): Promise<LoginResponse> => {
    return apiClient.post<LoginResponse>(`/auth/login`, { username, password })
        .then(response => {
            return response.data
        }).catch(error => {
            return Promise.reject(error.response.data as ErrorResponse);
        });
}

export const getMessages = async (): Promise<PageMessageResponse<Message>> => {
    return apiClient.get<PageMessageResponse<Message>>(`/messages`)
        .then(response => {
            return response.data
        }).catch(error => {
            return Promise.reject(error.response.data as ErrorResponse);
        });
}

export { apiClient };