'use client'
import {useState} from "react";
import {handleLogin} from "@/api/api";

const Login = () => {
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');

    return (
        <div style={{width: "250px"}}>
            <div style={{display: "flex", flexDirection: "column"}}>
                <text>Email</text>
                <input value={email} onChange={e => setEmail(e.target.value)} type="text"/>
                <text>Password</text>
                <input value={password} onChange={e => setPassword(e.target.value)} type="password"/>
                <button onClick={() => handleLogin(email, password)}>Login</button>
            </div>
        </div>
    )
}

export default Login;