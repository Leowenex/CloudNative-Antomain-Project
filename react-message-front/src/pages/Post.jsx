import { useState } from "react"
import axios from "axios"
import { useNavigate } from "react-router-dom"

function Post() {
    const [content, setContent] = useState("")
    // const [file, setFile] = useState(null)  // Inutile pour l'instant
    const navigate = useNavigate()
    const token = localStorage.getItem("token")

    /*
    const handleFileChange = (e) => {
        setFile(e.target.files[0])
    }
    */

    const handleSubmit = async (e) => {
        e.preventDefault()
        try {
            // On ne gère pas l’upload pour le moment
            // let imageId = null
            // if (file) {
            //   ...
            // }

            // Pour le moment, on poste sans image
            await axios.post(
                "http://localhost:8082/messages",
                { content }, // ignorons imageId
                {
                    headers: { Authorization: `Bearer ${token}` }
                }
            )

            navigate("/feed")
        } catch (error) {
            console.error("Erreur lors de la création du message : ", error)
        }
    }

    return (
        <div className="container">
            <h2>Créer un nouveau message</h2>
            <form onSubmit={handleSubmit}>
                <div>
                    <label>Contenu du message :</label>
                    <textarea
                        rows="5"
                        value={content}
                        onChange={(e) => setContent(e.target.value)}
                        required
                    />
                </div>
                <div>
                    <label>Image (optionnel) :</label>
                    <input type="file" /*onChange={handleFileChange}*/ accept="image/*" />
                </div>
                <button type="submit">Publier</button>
            </form>

            <p>Ajout d’image pas encore disponible. (Placeholder)</p>
        </div>
    )
}

export default Post
