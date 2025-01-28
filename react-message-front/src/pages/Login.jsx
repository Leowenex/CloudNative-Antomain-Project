import React, { useState } from 'react'
import axios from 'axios'
import { useNavigate } from 'react-router-dom'

function Login() {
    const navigate = useNavigate()
    const [username, setUsername] = useState('')
    const [password, setPassword] = useState('')
    const [error, setError] = useState('')

    const handleLogin = async (e) => {
        e.preventDefault()
        try {
            const response = await axios.post('http://localhost:8082/users/login', {
                username,
                password
            })

            // Si votre backend renvoie un objet du type { token: '...' }
            const token = response.data.token

            // On stocke le token dans le localStorage
            localStorage.setItem('token', token)

            // Redirection vers la page d'accueil par exemple
            navigate('/')
        } catch (err) {
            setError(err.response?.data?.error || 'Connexion impossible')
        }
    }

    return (
        <div>
            <h2>Connexion</h2>
            <form onSubmit={handleLogin}>
                <div>
                    <label>Username</label>
                    <input
                        type="username"
                        value={username}
                        onChange={(e) => setUsername(e.target.value)}
                        required
                    />
                </div>

                <div>
                    <label>Mot de passe</label>
                    <input
                        type="password"
                        value={password}
                        onChange={(e) => setPassword(e.target.value)}
                        required
                    />
                </div>

                <button type="submit">Se connecter</button>
            </form>

            {error && <p>{error}</p>}
        </div>
    )
}

export default Login
