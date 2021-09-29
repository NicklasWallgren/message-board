import React, { useContext } from 'react';
import Header from '../components/Header';
import { Redirect } from 'react-router-dom';
import { UserSessionContext } from '../contexts/UserSessionContext';

export default function Landing() {
    const { user } = useContext(UserSessionContext);
    if (user) {
        <Redirect to='/home'/>
    }

    return (
        <div className="page">
            <Header/>
            <h3>This is the public landing page</h3>
        </div>
    )
}