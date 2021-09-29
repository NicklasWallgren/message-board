import { Pageable, Sort } from "../../test";

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

export interface User {
    id: number,
    username: string
}

export interface Message {
    id: number,
    subject: string,
    text: string,
    user: User
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

export interface MessageResponse {
    id: number,
    text: string,
    user: any
}