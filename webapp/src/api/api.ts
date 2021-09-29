import axios from "axios";
import {
    ErrorResponse,
    LoginResponse,
    Message,
    MessageResponse,
    PageMessageResponse,
    RegisterResponse,
    UserCredentials
} from "./models";

const apiClient = axios.create();

let token: string | undefined;

export const setToken = (newToken: string) => (token = newToken);

apiClient.defaults.baseURL = "http://localhost:8080/api";
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
    return apiClient.get<PageMessageResponse<Message>>(`/messages?sort=id,desc`)
        .then(response => {
            return response.data
        }).catch(error => {
            return Promise.reject(error.response.data as ErrorResponse);
        });
}

export const createMessage = async (subject: string, text: string): Promise<MessageResponse> => {
    return apiClient.post<MessageResponse>(`/messages`, { subject, text })
        .then(response => {
            return response.data
        }).catch(error => {
            return Promise.reject(error.response.data as ErrorResponse);
        });
}

export { apiClient };