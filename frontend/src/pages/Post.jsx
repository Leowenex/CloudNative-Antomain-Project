import { useState } from "react"
import axios from "axios"
import { useNavigate } from "react-router-dom"

function Post() {
    const [content, setContent] = useState("")
    const [file, setFile] = useState(null)
    const navigate = useNavigate()
    const token = localStorage.getItem("token")

    const handleFileChange = (e) => {
        setFile(e.target.files[0])
    }

    const handleSubmit = async (e) => {
        e.preventDefault()
        try {
            const formData = new FormData()
            formData.append("content", content)
            if (file) {
                formData.append("messagePicture", file)
            }

            await axios.post("/messages", formData, {
                headers: {
                    Authorization: `Bearer ${token}`,
                    "Content-Type": "multipart/form-data"
                }
            })

            navigate("/feed")
        } catch (error) {
            console.error("Erreur lors de la création du message : ", error)
        }
    }

    return (
        <div className="container" style={{ padding: "20px", textAlign: "center" }}>
            <h2>Créer un nouveau message</h2>
            <form onSubmit={handleSubmit} style={{ maxWidth: "600px", margin: "0 auto" }}>
                <div style={{ marginBottom: "10px" }}>
                    <label>Contenu du message :</label>
                    <textarea
                        rows="5"
                        value={content}
                        onChange={(e) => setContent(e.target.value)}
                        required
                        style={{ width: "100%", padding: "10px", marginTop: "5px" }}
                    />
                </div>
                <div style={{ marginBottom: "10px" }}>
                    <label>Image (optionnel) :</label>
                    <input type="file" onChange={handleFileChange} accept="image/*" style={{ marginTop: "5px" }} />
                </div>
                <button
                    type="submit"
                    style={{
                        padding: "10px 20px",
                        border: "none",
                        borderRadius: "4px",
                        backgroundColor: "#333",
                        color: "#fff",
                        cursor: "pointer"
                    }}
                >
                    Publier
                </button>
            </form>
        </div>
    )
}

export default Post
