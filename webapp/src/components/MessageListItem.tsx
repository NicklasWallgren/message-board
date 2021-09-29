import * as React from 'react';
import ListItem from '@mui/material/ListItem';
import Divider from '@mui/material/Divider';
import ListItemText from '@mui/material/ListItemText';
import ListItemAvatar from '@mui/material/ListItemAvatar';
import Avatar from '@mui/material/Avatar';
import Typography from '@mui/material/Typography';
import { Message } from "../api/models";
import { IconButton } from "@material-ui/core";
import DeleteIcon from '@mui/icons-material/Delete';
import EditIcon from '@mui/icons-material/Edit';

export function MessageListItem({ message }: { message: Message }) {
    const capitalize = (subject: string): string =>
        subject.charAt(0).toUpperCase() + subject.slice(1);

    return (
        <>
            <ListItem alignItems="flex-start"
                      secondaryAction={
                          <>
                          <IconButton edge="end" aria-label="edit">
                                <EditIcon/>
                          </IconButton>
                          <IconButton edge="end" aria-label="delete">
                              <DeleteIcon/>
                          </IconButton>
                          </>
                      }>
                <ListItemAvatar>
                    <Avatar alt={ capitalize(message.user.username) } src="/static/images/avatar/1.jpg"/>
                </ListItemAvatar>
                <ListItemText
                    primary={ capitalize(message.subject) }
                    secondary={
                        <React.Fragment>
                            <Typography
                                sx={ { display: 'inline' } }
                                component="span"
                                variant="body2"
                                color="text.primary"
                            >
                                { capitalize(message.user.username) }
                            </Typography>
                            { " — " } { capitalize(message.text) }
                        </React.Fragment>
                    }
                />
            </ListItem>
            <Divider variant="inset" component="li"/>
        </>
    );
}
