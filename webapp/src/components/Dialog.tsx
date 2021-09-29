import React from 'react';
import Dialog from '@material-ui/core/Dialog';
import Register from "../screens/Register";

const ModalDialog = ({ open, handleClose }: any) => {
    return (
        <Dialog open={ open } onClose={ handleClose }>
            <Register handleClose={ handleClose }/>
        </Dialog>
    );
};

export default ModalDialog;