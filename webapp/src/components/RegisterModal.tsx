import * as React from 'react';
import Box from '@mui/material/Box';
import Button from '@mui/material/Button';
import Typography from '@mui/material/Typography';
import Modal from '@mui/material/Modal';
import { SxProps } from "@mui/system";
import { Theme } from "@mui/material/styles";
import { LoginForm } from "./LoginForm";
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

export function RegisterModal({ open, handleOpen, handleClose }: any) {
    const theme = useStyles();

    return (
        <div>
            <Modal
                open={open}
                onClose={handleClose}
                aria-labelledby="modal-modal-title"
                aria-describedby="modal-modal-description"
            >
                <Box sx={style}>
                    <Typography id="modal-modal-title" variant="h6" component="h2">
                        Register
                    </Typography>
                    <RegisterForm style={theme}/>
                </Box>
            </Modal>
        </div>
    );
}
