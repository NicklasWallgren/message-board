import { Pageable, Sort } from "../../test";

export type OnError = (errorMessage: string) => void
export type OnUnauthorized = () => void

export interface UserCredentials {
    username: string;
    password: string;
}

export interface RegisterResponse {
    id: number,
    username: string;
}

export interface LoginResponse {
    id: number,
    username: string;
    token: string;
}

export interface ErrorResponse {
    message: string
    status: number
}

export interface EntityResponse {
    id: number
}

export interface Message {
    id: number,
    text: string,
    user: any
}

export interface PageMessageResponse<T extends EntityResponse> {
    totalPages: number;
    totalElements: number;
    size: number;
    content: Array<T>;
    number: number;
    sort: Sort;
    numberOfElements: number;
    first: boolean;
    last: boolean;
    pageable: Pageable;
    empty: boolean;
}