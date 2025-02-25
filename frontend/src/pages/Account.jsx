import { useState, useEffect } from "react"
import axios from "axios"

function Account() {
    const [file, setFile] = useState(null)
    const [profilePic, setProfilePic] = useState(null)
    const token = localStorage.getItem("token")
    const placeholderAvatar = "https://upload.wikimedia.org/wikipedia/commons/7/7c/Profile_avatar_placeholder_large.png?20150327203541"

    // Récupère les infos utilisateur (et sa photo) au montage
    useEffect(() => {
        const fetchProfile = async () => {
            try {
                const response = await axios.get("/users/whoami", {
                    headers: { Authorization: `Bearer ${token}` }
                })
                if (response.data && response.data.profilePictureUrl) {
                    setProfilePic(response.data.profilePictureUrl)
                } else {
                    setProfilePic(placeholderAvatar)
                }
            } catch (error) {
                console.error("Erreur lors de la récupération des infos utilisateur :", error)
                setProfilePic(placeholderAvatar)
            }
        }
        fetchProfile()
    }, [token])

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
            setProfilePic(response.data)
        } catch (error) {
            console.error("Erreur lors de l'upload de l'image de profil :", error)
            setProfilePic(placeholderAvatar)
        }
    }

    return (
        <div
            style={{
                display: "flex",
                flexDirection: "column",
                alignItems: "center",
                padding: "20px"
            }}
        >
            <h2>Mon compte</h2>
            {profilePic && (
                <div style={{ marginBottom: "20px" }}>
                    <img
                        src={profilePic}
                        alt="Photo de profil"
                        style={{
                            width: "150px",
                            height: "150px",
                            borderRadius: "50%",
                            border: "3px solid #333"
                        }}
                        onError={(e) => { e.target.src = placeholderAvatar }}
                    />
                </div>
            )}
            <form onSubmit={handleUpload} style={{ display: "flex", flexDirection: "column", alignItems: "center" }}>
                <div style={{ marginBottom: "10px" }}>
                    <label>Photo de profil : </label>
                    <input type="file" onChange={handleFileChange} accept="image/*" />
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
                    Mettre à jour
                </button>
            </form>
        </div>
    )
}

export default Account
