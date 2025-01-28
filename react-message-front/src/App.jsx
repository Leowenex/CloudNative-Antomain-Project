import React from 'react'
import { Routes, Route, Link } from 'react-router-dom'
import Home from './pages/Home'
import Register from './pages/Register'
import Login from './pages/Login'
import Feed from './pages/Feed'
import Post from './pages/Post'
import './App.css'

function App() {
    return (
        <div>
            {/* Barre de navigation */}
            <nav className="nav-bar">
                <Link to="/">Accueil</Link>
                <Link to="/feed">Feed</Link>
                <Link to="/post">Nouveau message</Link>
                <Link to="/login">Se connecter</Link>
                <Link to="/register">S'enregistrer</Link>
            </nav>

            <Routes>
                <Route path="/" element={<Home />} />
                <Route path="/feed" element={<Feed />} />
                <Route path="/post" element={<Post />} />
                <Route path="/login" element={<Login />} />
                <Route path="/register" element={<Register />} />
            </Routes>
        </div>
    )
}

export default App
