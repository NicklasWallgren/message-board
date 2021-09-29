import React, { useContext } from 'react';
import { Redirect } from 'react-router-dom';
import { UserSessionContext } from '../contexts/UserSessionContext';
import ButtonAppBar from "../components/ButtonAppBar";
import { LoginModal } from "../components/LoginModal";
import { MessageList } from "../components/MessageList";
import { AddButton } from "../components/AddButton";
import { getMessages } from "../api/api";

export default function Landing() {
    const { user } = useContext(UserSessionContext);

    const [ open, setOpen ] = React.useState(false);

    const handleOpen = () => setOpen(true);
    const handleClose = () => setOpen(false);

    if (user) {
        <Redirect to='/home'/>
    }

    getMessages().then(response => {
        console.log(response.content.values())
    }).catch(err => {
        console.log(err)
    })

    return (
        <div>
            <ButtonAppBar/>
            {/*<Header/>*/}
            <MessageList/>
            <AddButton/>
            <LoginModal open={ open } handleOpen={ handleOpen } handleClose={ handleClose }/>
            <h3>This is the public landing page</h3>
        </div>
    )
}