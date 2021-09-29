import React from 'react';

export default function InlineButton(props: any) {
    return (
        // @ts-ignore
        <h4 type={ props.type } onClick={ props.handleClick }>
            { props.name }
        </h4>
    )
}