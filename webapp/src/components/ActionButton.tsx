import * as React from 'react';
import Box from '@mui/material/Box';
import SpeedDial from '@mui/material/SpeedDial';
import SpeedDialIcon from '@mui/material/SpeedDialIcon';
import SpeedDialAction from '@mui/material/SpeedDialAction';
import AddMessageIcon from '@mui/icons-material/Chat';

export function ActionButton({ onAddMessageHandler }: any) {
    return (
        <Box sx={ { transform: 'translateZ(0px)', flexGrow: 1 } }>
            <SpeedDial
                ariaLabel="Action button"
                sx={ { position: 'absolute', bottom: -50, right: 16 } }
                icon={ <SpeedDialIcon/> }
            >
                <SpeedDialAction
                    onClick={ onAddMessageHandler }
                    key={ "Add new message" }
                    icon={ <AddMessageIcon/> }
                    tooltipTitle={ "Add new message" }
                />
            </SpeedDial>
        </Box>
    );
}
