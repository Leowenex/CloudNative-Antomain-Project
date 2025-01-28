// src/pages/Feed.jsx
import React, { useEffect, useState } from 'react'
import axios from 'axios'

function Feed() {
    const [messages, setMessages] = useState([])
    const token = localStorage.getItem('token')

    useEffect(() => {
        const fetchMessages = async () => {
            try {
                const response = await axios.get('http://localhost:8082/messages', {
                    headers: {
                        Authorization: `Bearer ${token}`
                    }
                })
                setMessages(response.data)
            } catch (error) {
                console.error('Erreur lors de la récupération des messages : ', error)
            }
        }

        fetchMessages()
    }, [token])

    return (
        <div className="container">
            <h2>Feed</h2>
            {messages.length === 0 && <p>Aucun message pour le moment.</p>}
            {messages.map((msg) => (
                <div key={msg.id} className="message-card">
                    {/* Utilisez msg.senderUsername au lieu de msg.username */}
                    <h4>{msg.senderUsername}</h4>
                    <p>{msg.content}</p>

                    {/* Affichez un champ date seulement si vous l'avez */}
                    {/* Si votre MessageDto contient un createdAt, utilisez msg.createdAt */}
                    {/* Sinon, supprimez cette ligne ou affichez autre chose */}
                    {/* <small>Posté le {msg.createdAt}</small> */}
                </div>
            ))}
        </div>
    )
}

export default Feed
