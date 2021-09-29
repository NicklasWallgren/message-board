import React, { useCallback, useContext, useEffect, useState } from 'react';
import { UserSessionContext } from '../contexts/UserSessionContext';
import AppBar from "../components/AppBar";
import { MessageList } from "../components/MessageList";
import { ActionButton } from "../components/ActionButton";
import { CreateMessageModal } from "../components/CreateMessageModal";
import { Message } from "../api/models";
import { getMessages } from "../api/api";

export default function Home() {
    const { user } = useContext(UserSessionContext);
    const [ isModalOpen, setModalAsOpen ] = React.useState(false);
    const [ messages, messagesSet ] = useState<Message[]>();

    const loadMessages = useCallback(async () => {
        const response = await getMessages();

        messagesSet(response.content)
    }, [ user ])

    useEffect(() => {
        loadMessages()
    }, [ loadMessages ])

    return (
        <div>
            <AppBar/>
            { messages && <MessageList messages={ messages }/> }
            { user && <ActionButton onCreateHandler={ () => setModalAsOpen(true) }/> }
            <CreateMessageModal open={ isModalOpen } handleClose={ () => setModalAsOpen(false) }/>
        </div>
    )
}