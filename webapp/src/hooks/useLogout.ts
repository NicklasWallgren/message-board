import { useHistory } from 'react-router-dom';
import { useContext } from "react";
import { UserSessionContext } from "../contexts/UserSessionContext";

export default function useLogout() {
    const history = useHistory();
    const { setUser } = useContext(UserSessionContext);

    const logoutUser = async (): Promise<void> => {
        // @ts-ignore
        setUser(null)
        history.push('/');
    }

    return { logoutUser }
}