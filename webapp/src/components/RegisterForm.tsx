import React, { FormEvent } from 'react';
import useForm from '../hooks/useForm';
import useApi from '../hooks/useApi';
import { RegisterFormState } from "../models/RegisterFormState";
import TextField from "@material-ui/core/TextField";
import Button from "@material-ui/core/Button";
import Error from "../components/Error";

export function RegisterForm({ style, onClose }: any) {
    const [ values, handleValueChange ] = useForm<RegisterFormState>(({ username: '', password: '' }));
    const { registerUser, errorMessage } = useApi();

    const handleSubmit = async (e: FormEvent) => {
        e.preventDefault();

        registerUser(values).then((response) => {
            onClose();
        });
    }

    return (
        <form className={ style.root } onSubmit={ handleSubmit }>
            <TextField
                label="Username"
                name="username"
                variant="filled"
                required
                value={ values.username }
                onChange={ handleValueChange }
            />
            <TextField
                label="Password"
                name="password"
                variant="filled"
                type="password"
                required
                value={ values.password }
                onChange={ handleValueChange }
            />
            <div>
                <Button variant="contained" onClick={ onClose }>
                    Cancel
                </Button>
                <Button type="submit" variant="contained" color="primary">
                    Register
                </Button>
            </div>
            <div>
                { errorMessage && <Error message={ errorMessage }/> }
            </div>
        </form>
    )
}