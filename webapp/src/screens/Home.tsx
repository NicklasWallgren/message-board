import React from 'react';
import Header from './../components/Header';

export default function Home() {
    return (
        <div className="page">
            <Header/>
            <h3 className="page__body">Welcome to the { `<ProtectedRoute/>` } component</h3>
        </div>
    )
};