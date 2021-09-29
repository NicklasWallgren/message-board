import * as React from 'react';
import { useContext } from 'react';
import MuiAppBar from '@mui/material/AppBar';
import Box from '@mui/material/Box';
import Toolbar from '@mui/material/Toolbar';
import Typography from '@mui/material/Typography';
import Button from '@mui/material/Button';
import { LoginModal } from "./LoginModal";
import { RegisterModal } from "./RegisterModal";
import { UserSessionContext } from "../contexts/UserSessionContext";

export default function AppBar() {
    const [ isLoginModalOpen, setLoginModalAsOpen ] = React.useState(false);
    const [ isRegisterModalOpen, setRegisterModalAsOpen ] = React.useState(false);
    const { user, setUser } = useContext(UserSessionContext);

    return (
        <div>
            <LoginModal open={ isLoginModalOpen } handleClose={ () => setLoginModalAsOpen(false) }/>
            <RegisterModal open={ isRegisterModalOpen } handleClose={ () => setRegisterModalAsOpen(false) }/>

            <Box sx={ { flexGrow: 1 } }>
                <MuiAppBar position="static">
                    <Toolbar>
                        <Typography variant="h6" component="div" sx={ { flexGrow: 1 } }>
                            MessageBoard
                        </Typography>

                        { !user && <Button color="inherit" onClick={ () => setLoginModalAsOpen(true) }>Login</Button> }
                        { !user && <Button color="inherit" onClick={ () => setRegisterModalAsOpen(true) }>Register</Button> }
                        { user && <Button color="inherit" onClick={ (e) => setUser(null) }>Logout</Button> }
                    </Toolbar>
                </MuiAppBar>
            </Box>
        </div>
    );
}
