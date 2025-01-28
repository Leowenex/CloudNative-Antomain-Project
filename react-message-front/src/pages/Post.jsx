// src/pages/Post.jsx
import React, { useState } from 'react'
import axios from 'axios'
import { useNavigate } from 'react-router-dom'

function Post() {
    const [content, setContent] = useState('')
    const navigate = useNavigate()
    const token = localStorage.getItem('token')

    const handleSubmit = async (e) => {
        e.preventDefault()
        try {
            // Construire l'objet message selon votre DTO MessageCreationDto
            // Par exemple : { content } si votre DTO attend un champ 'content'
            const response = await axios.post(
                'http://localhost:8082/messages',
                { content },
                {
                    headers: {
                        Authorization: `Bearer ${token}`
                    }
                }
            )

            // Optionnel : afficher la réponse, ou un message de succès
            console.log('Message posté :', response.data)

            // Redirection vers la page Feed
            navigate('/feed')
        } catch (error) {
            console.error('Erreur lors de la création du message : ', error)
        }
    }

    return (
        <div className="container">
            <h2>Créer un nouveau message</h2>
            <form onSubmit={handleSubmit}>
                <div>
                    <label>Contenu du message :</label>
                    <textarea
                        rows="5"
                        value={content}
                        onChange={(e) => setContent(e.target.value)}
                        required
                    />
                </div>
                <button type="submit">Publier</button>
            </form>
        </div>
    )
}

export default Post
