import * as React from 'react';
import AppBar from '@mui/material/AppBar';
import Box from '@mui/material/Box';
import Toolbar from '@mui/material/Toolbar';
import Typography from '@mui/material/Typography';
import Button from '@mui/material/Button';
import IconButton from '@mui/material/IconButton';
import MenuIcon from '@mui/icons-material/Menu';
import { LoginModal } from "./LoginModal";
import { RegisterModal } from "./RegisterModal";

export default function ButtonAppBar() {
    const [ openLoginModel, setLoginModelOpen ] = React.useState(false);
    const [ openRegisterModel, setRegisterModelOpen ] = React.useState(false);

    const handleOpenLoginModal = () => setLoginModelOpen(true);
    const handleCloseLoginModal = () => setLoginModelOpen(false);

    const handleOpenRegisterModal = () => setRegisterModelOpen(true);
    const handleCloseRegisterModal = () => setRegisterModelOpen(false);

    return (

        <div>
            <LoginModal open={ openLoginModel } handleClose={ handleCloseLoginModal }/>
            <RegisterModal open={ openRegisterModel } handleClose={ handleCloseRegisterModal }/>

            <Box sx={ { flexGrow: 1 } }>
                <AppBar position="static">
                    <Toolbar>
                        <IconButton
                            size="large"
                            edge="start"
                            color="inherit"
                            aria-label="menu"
                            sx={ { mr: 2 } }
                        >
                            <MenuIcon/>
                        </IconButton>
                        <Typography variant="h6" component="div" sx={ { flexGrow: 1 } }>
                            MessageBoard
                        </Typography>
                        <Button color="inherit" onClick={ handleOpenLoginModal }>Login</Button>
                        <Button color="inherit" onClick={ handleOpenRegisterModal }>Register</Button>
                    </Toolbar>
                </AppBar>
            </Box>
        </div>
    );
}
