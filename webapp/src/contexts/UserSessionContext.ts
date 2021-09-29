import { createContext, Dispatch, SetStateAction } from 'react';

export interface User {
    id: number
    username: string;
    token?: string
}

export interface UserSession {
    user: User | null
    setUser: Dispatch<SetStateAction<User|null>>
}

export const UserSessionContext = createContext<UserSession>({
    user: null,
    // @ts-ignore
    setUser: (user: User | null): void => {},
})