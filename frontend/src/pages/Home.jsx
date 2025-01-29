import React from 'react'

function Home() {
    // Exemple de récupération du token (s'il existe) :
    const storedToken = localStorage.getItem('token')

    return (
        <div className="container">
            <h1>Accueil</h1>
            <p>Bienvenue sur notre application React !</p>

            {storedToken ? (
                <p>Token présent dans le localStorage : {storedToken}</p>
            ) : (
                <p>Vous n'êtes pas connecté.</p>
            )}
        </div>
    )
}

export default Home
