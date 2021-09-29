import * as React from 'react';
import Box from '@mui/material/Box';
import Typography from '@mui/material/Typography';
import Modal from '@mui/material/Modal';
import { SxProps } from "@mui/system";
import { Theme } from "@mui/material/styles";
import { useStyles } from "../styles/styles";
import { CreateMessageForm } from "./CreateMessageForm";

const style: SxProps<Theme> = {
    position: 'absolute',
    top: '50%',
    left: '50%',
    transform: 'translate(-50%, -50%)',
    width: 600,
    height: 385,
    bgcolor: 'background.paper',
    border: '2px solid #000',
    boxShadow: 24,
    p: 4,
};

export function CreateMessageModal({ open: isOpen, handleClose, initialMessage }: any) {
    const theme = useStyles();

    return (
        <div>
            <Modal
                open={ isOpen }
                onClose={ handleClose }
                aria-labelledby="modal-modal-title"
                aria-describedby="modal-modal-description"
            >
                <Box sx={ style }>
                    <Typography id="modal-modal-title" variant="h6" component="h2">
                        Add a new message
                    </Typography>
                    <CreateMessageForm style={ theme } handleClose={ handleClose } initialMessage={ null }/>
                </Box>
            </Modal>
        </div>
    );
}
