import React, { useContext } from 'react';
import { Redirect, Route } from 'react-router-dom';
import { UserSessionContext } from '../contexts/UserSessionContext';

export default function ProtectedRoute(props: any) {
    const { user } = useContext(UserSessionContext);
    const { component: Component, ...rest } = props;

    if (user) {
        return (<Route { ...rest } render={ (props) => (<Component { ...props }/>) }/>)
    } else {
        return <Redirect to='/login'/>
    }
}




