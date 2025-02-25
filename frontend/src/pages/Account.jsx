import { useState } from "react"
import axios from "axios"

function Account() {
    const [file, setFile] = useState(null)
    const token = localStorage.getItem("token")

    const handleFileChange = (e) => {
        setFile(e.target.files[0])
    }

    const handleUpload = async (e) => {
        e.preventDefault()
        if (!file) return

        const formData = new FormData()
        formData.append("image", file)
        formData.append("filename", file.name)

        try {
            const response = await axios.post("/users/profile-picture", formData, {
                headers: {
                    Authorization: `Bearer ${token}`,
                    "Content-Type": "multipart/form-data"
                }
            })
            console.log("Image de profil mise à jour, url :", response.data)
            // Ici, vous pouvez mettre à jour l'affichage de la photo de profil en utilisant response.data
        } catch (error) {
            console.error("Erreur lors de l'upload de l'image de profil :", error)
        }
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
        </div>
    )
}

export default Account
