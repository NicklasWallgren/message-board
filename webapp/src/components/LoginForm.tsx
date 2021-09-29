import React, { FormEvent } from 'react';
import TextField from '@material-ui/core/TextField';
import Button from '@material-ui/core/Button';
import useForm from "../hooks/useForm";
import { RegisterFormState } from "../models/RegisterFormState";
import useApi from "../hooks/useApi";
import Error from "../components/Error";

export function LoginForm({ style, loginHandle }: any) {
    const [ values, handleChange ] = useForm<RegisterFormState>(({ username: '', password: '' }));
    const { loginUser, errorMessage } = useApi();

    const handleSubmit = async (e: FormEvent) => {
        e.preventDefault();

        loginUser(values).then(() => {
            loginHandle()
        })
    }

    return (
        <form className={ style.root } onSubmit={ handleSubmit }>
            <TextField
                label="Username"
                name="username"
                variant="filled"
                required
                value={ values.username }
                onChange={ handleChange }
            />
            <TextField
                label="Password"
                name="password"
                variant="filled"
                type="password"
                required
                value={ values.password }
                onChange={ handleChange }
            />
            <div>
                <Button variant="contained" onClick={ loginHandle }>
                    Cancel
                </Button>
                <Button type="submit" variant="contained" color="primary">
                    Login
                </Button>
            </div>
            <div>
                { errorMessage && <Error message={ errorMessage }/> }
            </div>
        </form>
    );
};