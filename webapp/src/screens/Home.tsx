import React, { useCallback, useContext, useEffect, useState } from 'react';
import { UserSessionContext } from '../contexts/UserSessionContext';
import AppBar from "../components/AppBar";
import { MessageList } from "../components/MessageList";
import { ActionButton } from "../components/ActionButton";
import { Message } from "../api/models";
import { getMessages } from "../api/api";
import { Modal } from "../components/Modal";
import { CreateMessageForm } from "../components/CreateMessageForm";
import { useStyles } from "../styles/styles";
import { SxProps } from "@mui/system";
import { Theme } from "@mui/material/styles";

const style: SxProps<Theme> = {
    position: 'absolute',
    top: '50%',
    left: '50%',
    transform: 'translate(-50%, -50%)',
    width: 600,
    height: 385,
    bgcolor: 'background.paper',
    border: '2px solid #000',
    boxShadow: 24,
    p: 4,
};

export default function Home() {
    const { user } = useContext(UserSessionContext);
    const [ isModalOpen, setModalAsOpen ] = React.useState(false);
    const [ date, setDate ] = useState(Date.now().toString());
    const [ messages, messagesSet ] = useState<Message[]>();
    const theme = useStyles();

    const handleCloseModal = async () => {
        setModalAsOpen(false);
        setDate(Date.now().toString())
    }

    const loadMessages = useCallback(() => {
        getMessages().then(response => {
            messagesSet(response.content)
        });
        // eslint-disable-next-line
    }, [ date ])

    useEffect(() => {
        loadMessages()
        // eslint-disable-next-line
    }, [ date ])

    return (
        <div>
            <AppBar/>
            { messages && <MessageList messages={ messages }/> }
            { user && <ActionButton onAddMessageHandler={ () => setModalAsOpen(true) }/> }

            <Modal title="Add a new message"
                   style={ style }
                   open={ isModalOpen }
                   component={ <CreateMessageForm style={ theme } handleClose={ handleCloseModal }
                                                  initialMessage={ null }/> }
                   onClose={ handleCloseModal }/>
        </div>
    )
}