'use client'

import {useEffect, useState} from "react";
import {findAllClients} from "@/api/api";

const Clients = () => {

    const [clients, setClients] = useState();

    console.log(clients);
    useEffect(() => {
        findAllClients().then((data) => {
            setClients(data); //
        })
    }, [])

    return (
        <div>
            Clients
        </div>
    )
}

export default Clients