import * as React from 'react';
import { useContext } from 'react';
import MuiAppBar from '@mui/material/AppBar';
import Box from '@mui/material/Box';
import Toolbar from '@mui/material/Toolbar';
import Typography from '@mui/material/Typography';
import Button from '@mui/material/Button';
import { UserSessionContext } from "../contexts/UserSessionContext";
import { Modal } from "./Modal";
import { LoginForm } from "./LoginForm";
import { SxProps } from "@mui/system";
import { Theme } from "@mui/material/styles";
import { useStyles } from "../styles/styles";
import { RegisterForm } from "./RegisterForm";

const style: SxProps<Theme> = {
    position: 'absolute',
    top: '50%',
    left: '50%',
    transform: 'translate(-50%, -50%)',
    width: 400,
    bgcolor: 'background.paper',
    border: '2px solid #000',
    boxShadow: 24,
    p: 4,
};

export default function AppBar() {
    const [ isLoginModalOpen, setLoginModalAsOpen ] = React.useState(false);
    const [ isRegisterModalOpen, setRegisterModalAsOpen ] = React.useState(false);
    const { user, setUser } = useContext(UserSessionContext);

    const theme = useStyles();

    return (
        <div>

            <Modal title="Login"
                   style={ style }
                   open={ isLoginModalOpen }
                   component={ <LoginForm style={ theme } onClose={ () => setLoginModalAsOpen(false) }/> }
                   onClose={ () => setLoginModalAsOpen(false) }/>

            <Modal title="Register"
                   style={ style }
                   open={ isRegisterModalOpen }
                   component={ <RegisterForm style={ theme } onClose={ () => setRegisterModalAsOpen(false) }/> }
                   onClose={ () => setRegisterModalAsOpen(false) }/>

            <Box sx={ { flexGrow: 1 } }>
                <MuiAppBar position="static">
                    <Toolbar>
                        <Typography variant="h6" component="div" sx={ { flexGrow: 1 } }>
                            MessageBoard
                        </Typography>

                        { !user && <Button color="inherit" onClick={ () => setLoginModalAsOpen(true) }>Login</Button> }
                        { !user &&
                        <Button color="inherit" onClick={ () => setRegisterModalAsOpen(true) }>Register</Button> }
                        { user && <Button color="inherit" onClick={ (e) => setUser(null) }>Logout</Button> }
                    </Toolbar>
                </MuiAppBar>
            </Box>
        </div>
    );
}
