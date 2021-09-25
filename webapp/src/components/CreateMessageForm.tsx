import React, { FormEvent, useState } from 'react';
import Button from '@material-ui/core/Button';
import useApi from "../hooks/useApi";
import Error from "../components/Error";
import { TextareaAutosize } from "@material-ui/core";
import TextField from "@material-ui/core/TextField";

export function CreateMessageForm({ style, handleClose, initialMessage }: any) {
    const [ subject, setSubject ] = useState("");
    const [ message, setMessage ] = useState("");
    const { addNewMessage, errorMessage } = useApi();

    const handleSubmit = async (e: FormEvent) => {
        e.preventDefault();

        addNewMessage(subject, message).then(() => {
            handleClose()
        })
    }

    return (
        <form className={ style.root } onSubmit={ handleSubmit }>
            <TextField
                style={ { width: 600 } }
                label="Subject"
                name="subject"
                variant="filled"
                required
                value={ subject }
                onChange={ (e) => setSubject(e.target.value) }
            />
            <TextareaAutosize
                style={ { width: 595, height: 200 } }
                name="message"
                required
                value={ message }
                onChange={ (e) => setMessage(e.target.value) }
            />
            <div>
                <Button variant="contained" onClick={ handleClose }>
                    Cancel
                </Button>
                <Button type="submit" variant="contained" color="primary">
                    Add
                </Button>
            </div>
            <div>
                { errorMessage && <Error message={ errorMessage }/> }
            </div>
        </form>
    );
}