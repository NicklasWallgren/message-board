import { ChangeEvent, useState } from 'react';

export const useForm = <T>(initialState: T): [ T, ((event: ChangeEvent<HTMLInputElement>) => void) ] => {
    const [ values, setValues ] = useState<T>(initialState);

    const handleChange = (event: ChangeEvent<HTMLInputElement>): void => {
        const name = event.target.name;
        const value = event.target.value;

        setValues({ ...values, [name]: value });
    };

    return [ values, handleChange ]
}

export default useForm;