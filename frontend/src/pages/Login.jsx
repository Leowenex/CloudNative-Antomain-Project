import React, { useState } from 'react'
import axios from 'axios'
import {Link, useNavigate} from 'react-router-dom'

function Login() {
    const navigate = useNavigate()
    const [username, setUsername] = useState('')
    const [password, setPassword] = useState('')
    const [error, setError] = useState('')

    const handleLogin = async (e) => {
        e.preventDefault()
        try {
            const response = await axios.post('users/login', {
                username,
                password
            })

            // Récupération du token renvoyé par le backend
            const token = response.data.token

            // Stockage du token dans le localStorage
            localStorage.setItem('token', token)

            // Redirection vers la page d'accueil
            navigate('/')
        } catch (err) {
            setError(err.response?.data?.error || 'Connexion impossible')
        }
    }

    return (
        <div className="container">
            <h2>Connexion</h2>
            <form onSubmit={handleLogin}>
                <div>
                    <label>Nom d'utilisateur</label>
                    <input
                        type="text"
                        placeholder="Entrez votre nom d'utilisateur"
                        value={username}
                        onChange={(e) => setUsername(e.target.value)}
                        required
                    />
                </div>

                <div>
                    <label>Mot de passe</label>
                    <input
                        type="password"
                        placeholder="Entrez votre mot de passe"
                        value={password}
                        onChange={(e) => setPassword(e.target.value)}
                        required
                    />
                </div>

                <button type="submit">Se connecter</button>
            </form>

            <p>Pas de compte ? <Link to="/register">Inscris-toi ici</Link></p>


            {error && <p className="error">{error}</p>}
        </div>


    )
}

export default Login
