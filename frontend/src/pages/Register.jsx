import React, { useState } from 'react'
import axios from 'axios'
import { useNavigate } from 'react-router-dom'

function Register() {
    const navigate = useNavigate()
    const [username, setUsername] = useState('')
    const [password, setPassword] = useState('')
    const [message, setMessage] = useState('')

    const handleRegister = async (e) => {
        e.preventDefault()
        try {
            const response = await axios.post('users/register', {
                username,
                password
            })
            setMessage(response.data?.message || 'Enregistrement réussi !')

            // On pourrait se connecter automatiquement ici en récupérant le token
            // Mais dans cet exemple, on redirige vers la page de login :
            navigate('/login')
        } catch (error) {
            setMessage(error.response?.data?.error || 'Une erreur est survenue')
        }
    }

    return (
        <div className="container">
            <h2>Enregistrement</h2>
            <form onSubmit={handleRegister}>
                <div>
                    <label>Nom d'utilisateur</label>
                    <input
                        type="text"
                        placeholder="Choisissez un nom d'utilisateur"
                        value={username}
                        onChange={(e) => setUsername(e.target.value)}
                        required
                    />
                </div>

                <div>
                    <label>Mot de passe</label>
                    <input
                        type="password"
                        placeholder="Choisissez un mot de passe"
                        value={password}
                        onChange={(e) => setPassword(e.target.value)}
                        required
                    />
                </div>

                <button type="submit">S'inscrire</button>
            </form>

            {message && <p className="message">{message}</p>}
        </div>
    )
}

export default Register
