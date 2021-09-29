import React from 'react';

export default function Error(props: { message: string }) {
    return (
        <div>
            <h5>{ props.message }</h5>
        </div>
    )
}