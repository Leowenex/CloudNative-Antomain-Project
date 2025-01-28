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
            const response = await axios.post('http://localhost:8082/users/register', {
                username,
                password
            })
            setMessage(response.data?.message || 'Enregistrement réussi !')
            navigate('/login')
        } catch (error) {
            setMessage(error.response?.data?.error || 'Une erreur est survenue')
        }
    }

    return (
        <div>
            <h2>Enregistrement</h2>
            <form onSubmit={handleRegister}>
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

                <button type="submit">S'inscrire</button>
            </form>

            {message && <p>{message}</p>}
        </div>
    )
}

export default Register
