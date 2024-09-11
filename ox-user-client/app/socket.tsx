'use client'

import React, {useEffect, useState} from "react";
import Stomp, {Client} from 'stompjs'
import SockJS from 'sockjs-client'
import {Avatar, IconButton, List, ListItem, ListItemAvatar, ListItemText, TextField, Typography} from "@mui/material";


const Home = () => {
    const [messages, setMessages] = useState<string[]>([]);
    const [message, setMessage] = useState('');
    const [nickname, setNickname] = useState('');
    const [stompClient, setStompClient] = useState<Client | null>(null);
    useEffect(() => {
        const socket = new SockJS(`http://localhost:9071/ws`);
        const client = Stomp.over(socket);
        client.connect({}, () => {
            client.subscribe('/topic/messages', (message) => {
                const receivedMessage = JSON.parse(message.body);
                setMessages((prevMessages) => [...prevMessages, receivedMessage]);
            });
        });
        setStompClient(client);
        return () => {
            client.disconnect(() => {
                console.log('Disconnected');
            });
        }
    }, []);

    const handleNicknameChange = (e: any) => {
        setNickname(e.target.value);
    }

    const handleMessageChange = (e: any) => {
        setMessage(e.target.value);
    }

    const sendMessage = () => {
        if (message.trim()) {
            const chatMessage = {
                nickname,
                content: message
            }
            if (stompClient) {
                stompClient.send('/app/chat', {}, JSON.stringify(chatMessage));
                setMessage('');
            }
        }
    }
    return (
        <div>
            <List>
                {messages.map((msg: any, index) => (
                    <ListItem key={index}>
                        <ListItemAvatar>
                            <Avatar>{msg.nickname.charAt(0)}</Avatar>
                        </ListItemAvatar>
                        <ListItemText
                            primary={
                                <Typography variant="subtitle1">{msg.nickname}</Typography>
                            }
                            secondary={msg.content}
                        />
                    </ListItem>
                ))}
            </List>

            <div style={{display: 'flex', alignItems: 'center'}}>
                <TextField
                    placeholder="Enter your nickname"
                    value={nickname}
                    onChange={handleNicknameChange}
                    autoFocus
                />
                <TextField
                    placeholder="Type a message"
                    value={message}
                    onChange={handleMessageChange}
                    fullWidth
                />
                <IconButton onClick={sendMessage} disabled={!message.trim()}>
                    send
                </IconButton>
            </div>
        </div>
    )
}

export default Home;
