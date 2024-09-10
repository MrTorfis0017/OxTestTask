'use server'

import axios from "axios";
import {cookies} from "next/headers";

export const handleRegister = async (email: string, password: string) => {
    const response = await axios.post('http://localhost:9071/api/auth/public/register', {
        email,
        password
    }, {
        headers: {
            'Content-Type': 'application/json'
        }
    })
    cookies().set({
        name: 'Authorization',
        value: 'Bearer:' + response.data.accessToken,
    })
}

export const handleLogin = async (email: string, password: string) => {
    const response = await axios.post('http://localhost:9071/api/auth/public/login', {
        email,
        password
    }, {
        headers: {
            'Content-Type': 'application/json'
        }
    })
    cookies().set({
        name: 'Authorization',
        value: 'Bearer:' + response.data.accessToken,
    })
}