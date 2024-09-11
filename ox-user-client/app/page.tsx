'use client'

import React from "react";
import {useRouter} from "next/navigation";


const Home = () => {
    const router = useRouter();
    return (
        <div style={{display:'flex',flexDirection:'column',width:'250px'}}>
            Home page
            <button onClick={()=>router.push('/clients')}>Clients</button>
            <button>Contacts</button>
            <button>Tasks</button>
        </div>
    )
}

export default Home;
