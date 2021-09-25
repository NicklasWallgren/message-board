import * as React from 'react';
import Box from '@mui/material/Box';
import Typography from '@mui/material/Typography';
import MuiModal from '@mui/material/Modal';

export function Modal({ title, style, component, open, onClose }: any) {
    return (
        <div>
            <MuiModal
                open={ open }
                onClose={ onClose }
                aria-labelledby="modal-modal-title"
                aria-describedby="modal-modal-description"
            >
                <Box sx={ style }>
                    <Typography id="modal-modal-title" variant="h6" component="h2">
                        { title }
                    </Typography>
                    { component }
                </Box>
            </MuiModal>
        </div>
    );
}
