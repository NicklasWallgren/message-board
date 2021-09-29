import './App.css';
import {BrowserRouter as Router, Route, Switch} from 'react-router-dom';
import {UserSessionContext, User} from './contexts/UserSessionContext';
import ProtectedRoute from './routes/ProtectedRoute';
import Register from './screens/Register';
import Login from './screens/Login';
import Landing from './screens/Landing';
import Home from './screens/Home';
import NotFound from './screens/NotFound';
import {useState} from "react";
import {useStyles} from "./styles/styles";

function App() {
    const [user, setUser] = useState<User|null>(null);
    const [isLoading, setLoading] = useState(true);

    const [open, setOpen] = useState(false);
    const handleOpen = () => setOpen(true);
    const handleClose = () => setOpen(false);

    const style = useStyles();

    // TODO, theme context?

    return (
        <Router>
            <UserSessionContext.Provider value={{user, setUser}}>
                <Switch>
                    <Route exact path="/" component={Landing}/>
                    <Route path="/register" render={() => <Register style={style}/>}/>
                    <Route path="/login" render={() => <Login style={style}/>}/>
                    <ProtectedRoute path="/home" component={Home}/>
                    <Route component={NotFound}/>
                </Switch>
            </UserSessionContext.Provider>
        </Router>
    );
}

export default App;
