'use server'

import axios from "axios";
import {cookies} from "next/headers";
import {getCookieFromBrowser} from "@/functions/utils";

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
        value: 'Bearer ' + response.data.accessToken,
        path: '/',
        httpOnly: true,
        sameSite: 'none',
        secure: true
    })
}

export const findAllClients = async () => {
    const token = getServerCookie("Authorization");
    console.log(token)
    const response = await axios.get('/api/client/find-all', {
        headers: {
            Authorization: token,
        },
        withCredentials: true
    });
    return response.data;
}

export const getServerCookie = (name: string): string | null => {
    const cookieStore = cookies();
    const cookie = cookieStore.get(name);
    return cookie ? cookie.value : null;
};