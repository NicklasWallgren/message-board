import * as React from 'react';
import List from '@mui/material/List';
import { Message } from "../api/models";
import { MessageListItem } from "./MessageListItem";

export function MessageList({ messages }: { messages: Message[] }) {
    return (
        <>
            <List sx={ { width: '100%', bgcolor: 'background.paper' } }>
                { messages && messages.map(message =>
                    <MessageListItem message={ message }/>
                ) }
            </List>
        </>
    );
}
