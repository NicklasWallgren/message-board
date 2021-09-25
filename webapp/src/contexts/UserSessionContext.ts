import { createContext } from 'react';

export interface User {
    id: number
    username: string;
    token?: string
}

export interface UserSession {
    user: User | null
    setUser: React.Dispatch<React.SetStateAction<User|null>>
}

export const UserSessionContext = createContext<UserSession>({
    user: null,
    // eslint-disable-next-line
    setUser: value => {},
})