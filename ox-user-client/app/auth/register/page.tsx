'use client'

import {useState} from "react";
import {handleLogin, handleRegister} from "@/api/api";

const Register = () => {
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');

    return (
        <div style={{width: "250px"}}>
            <div style={{display: "flex", flexDirection: "column"}}>
                <text>Email</text>
                <input value={email} onChange={e => setEmail(e.target.value)} type="text"/>
                <text>Password</text>
                <input value={password} onChange={e => setPassword(e.target.value)} type="password"/>
                <button onClick={() => handleRegister(email, password)}>Register</button>
            </div>
        </div>
    )
}

export default Register;