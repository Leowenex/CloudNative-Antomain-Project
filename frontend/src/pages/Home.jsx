import React from 'react'

function Home() {
    return (
        <div className="container">
            <h1>Bienvenue sur notre application React !</h1>
            <p>Ce site vous permet de publier des messages et de consulter le feed des autres utilisateurs. Voici un guide rapide pour bien démarrer :</p>

            <h2>Navigation</h2>
            <ul>
                <li><strong>Feed</strong> : Consultez les messages récents de tous les utilisateurs ou d’un utilisateur spécifique.</li>
                <li><strong>Nouveau message</strong> : Créez et publiez votre propre message, avec ou sans image.</li>
                <li><strong>Mon compte</strong> : Mettez à jour votre profil et votre photo de profil.</li>
            </ul>

            <h2>Fonctionnalités principales</h2>
            <ul>
                <li>Poster du texte et des images</li>
                <li>Voir les messages en temps réel (le feed se met à jour automatiquement toutes les 5 secondes)</li>
                <li>Interagir avec la communauté en consultant les profils et en naviguant entre les feeds</li>
            </ul>

            <p>Pour commencer, inscrivez-vous ou connectez-vous à votre compte. Bonne navigation !</p>
        </div>
    )
}

export default Home
