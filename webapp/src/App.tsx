import './App.css';
import { BrowserRouter as Router, Route, Switch } from 'react-router-dom';
import { User, UserSessionContext } from './contexts/UserSessionContext';
import Home from './screens/Home';
import NotFound from './screens/NotFound';
import { useState } from "react";

function App() {
    const [ user, setUser ] = useState<User | null>(null);

    // TODO, theme context?

    return (
        <Router>
            <UserSessionContext.Provider value={ { user, setUser } }>
                <Switch>
                    <Route exact path="/" component={ Home }/>
                    <Route component={ NotFound }/>
                </Switch>
            </UserSessionContext.Provider>
        </Router>
    );
}

export default App;
