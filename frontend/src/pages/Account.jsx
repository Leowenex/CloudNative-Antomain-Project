import { useState } from "react"

function Account() {
    const [file, setFile] = useState(null)
    // const token = localStorage.getItem("token") // Inutile pour l'instant

    const handleFileChange = (e) => {
        setFile(e.target.files[0])
    }

    const handleUpload = async (e) => {
        e.preventDefault()
        if (!file) return

        // Pour l’instant, on se contente d’un console.log
        // (Le backend images n’étant pas encore opérationnel)
        console.log("Fichier sélectionné :", file)

        // TODO: implémenter l'upload vers /images puis PATCH /users/me/profile-image
        //       une fois le backend fonctionnel
    }

    return (
        <div className="container">
            <h2>Mon compte</h2>
            <form onSubmit={handleUpload}>
                <div>
                    <label>Photo de profil : </label>
                    <input type="file" onChange={handleFileChange} accept="image/*" />
                </div>
                <button type="submit">Mettre à jour</button>
            </form>

            <p>Fonctionnalité d'upload d'image pas encore disponible.</p>
        </div>
    )
}

export default Account
