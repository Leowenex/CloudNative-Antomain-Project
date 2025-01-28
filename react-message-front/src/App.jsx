import 'react'
import {Routes, Route, Link, useNavigate} from 'react-router-dom'
import Home from './pages/Home'
import Register from './pages/Register'
import Login from './pages/Login'
import Feed from './pages/Feed'
import Post from './pages/Post'
import './App.css'
import Account from "./pages/Account.jsx";

function App() {
    const navigate = useNavigate()

    // Ex: on récupère username via decode du token ou localStorage
    const token = localStorage.getItem("token")
    const username = localStorage.getItem("username") // Cf. Login.jsx plus bas

    const handleLogout = () => {
        localStorage.removeItem("token")
        localStorage.removeItem("username")
        // ...éventuellement d’autres nettoyages
        navigate("/login")
    }

    return (
        <div>
            <nav className="nav-bar">
                <Link to="/">Accueil</Link>

                {/* Si un utilisateur est connecté, on affiche son nom et un bouton de déconnexion */}
                {token ? (
                    <>
                        <Link to="/feed">Feed</Link>
                        <Link to="/post">Nouveau message</Link>
                        <Link to="/account">Mon compte</Link>
                        <span className="nav-username">{username}</span>
                        <button onClick={handleLogout}>Déconnexion</button>
                    </>
                ) : (
                    // Sinon, on affiche juste le lien "Se connecter"
                    <Link to="/login">Se connecter</Link>
                )}
            </nav>

            <Routes>
                <Route path="/" element={<Home />} />
                <Route path="/feed" element={<Feed />} />
                <Route path="/feed/:username" element={<Feed />} /> {/* route spéciale */}
                <Route path="/post" element={<Post />} />
                <Route path="/account" element={<Account />} />
                <Route path="/login" element={<Login />} />
                <Route path="/register" element={<Register />} />
            </Routes>
        </div>
    )
}

export default App
