import React from 'react'
import { Routes, Route, Link } from 'react-router-dom'
import Home from './pages/Home'
import Register from './pages/Register'
import Login from './pages/Login'
import './App.css'

function App() {
    return (
        <div>
            {/* Navigation simple */}
            <nav className="nav-bar">
                <Link to="/">Accueil</Link>
                <Link to="/login">Se connecter</Link>
                <Link to="/register">S'enregistrer</Link>
            </nav>

            {/* Définition des routes */}
            <Routes>
                <Route path="/" element={<Home />} />
                <Route path="/register" element={<Register />} />
                <Route path="/login" element={<Login />} />
            </Routes>
        </div>
    )
}

export default App
