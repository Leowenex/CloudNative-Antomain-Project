import { useEffect, useState } from "react"
import axios from "axios"
import { useParams, Link } from "react-router-dom"

function Feed() {
    const [messages, setMessages] = useState([])
    const token = localStorage.getItem("token")
    const { username: routeUsername } = useParams()

    // Le titre du feed change s'il est filtré par utilisateur
    const feedTitle = routeUsername ? `Feed de ${routeUsername}` : "Feed"

    // Fonction de récupération des messages
    const fetchMessages = async () => {
        try {
            let url = "/messages"
            if (routeUsername) {
                url = `/messages/${routeUsername}`
            }
            const response = await axios.get(url, {
                headers: { Authorization: `Bearer ${token}` }
            })
            console.log("Réponse de l'API:", response.data)
            if (Array.isArray(response.data)) {
                setMessages(response.data)
            } else {
                console.error("La réponse n'est pas un tableau :", response.data)
                setMessages([])
            }
        } catch (error) {
            console.error("Erreur lors de la récupération des messages :", error)
            setMessages([])
        }
    }

    // Récupération initiale et rafraîchissement toutes les 5 secondes
    useEffect(() => {
        fetchMessages()
        const intervalId = setInterval(fetchMessages, 5000)
        return () => clearInterval(intervalId)
    }, [token, routeUsername])

    const placeholderAvatar = "https://upload.wikimedia.org/wikipedia/commons/7/7c/Profile_avatar_placeholder_large.png?20150327203541"

    return (
        <div
            style={{
                display: "flex",
                flexDirection: "column",
                alignItems: "center",
                padding: "20px"
            }}
        >
            <h2>{feedTitle}</h2>
            {messages.length === 0 ? (
                <p>Aucun message pour le moment.</p>
            ) : (
                messages.map((msg) => {
                    const profilePicUrl = msg.senderProfilePictureUrl ? msg.senderProfilePictureUrl : placeholderAvatar
                    const postImageUrl = msg.messagePictureUrl || null

                    return (
                        <div
                            key={msg.id}
                            style={{
                                border: "1px solid #ccc",
                                borderRadius: "8px",
                                padding: "10px",
                                margin: "10px",
                                width: "80%",
                                maxWidth: "600px",
                                textAlign: "center",
                                backgroundColor: "#f9f9f9"
                            }}
                        >
                            <Link
                                to={`/feed/${msg.senderUsername}`}
                                style={{
                                    textDecoration: "none",
                                    color: "inherit",
                                    display: "flex",
                                    alignItems: "center",
                                    justifyContent: "center"
                                }}
                            >
                                <img
                                    src={profilePicUrl}
                                    alt="avatar"
                                    style={{
                                        width: "50px",
                                        height: "50px",
                                        borderRadius: "50%",
                                        border: "2px solid #333",
                                        marginRight: "10px"
                                    }}
                                    onError={(e) => { e.target.src = placeholderAvatar }}
                                />
                                <span style={{ fontWeight: "bold" }}>{msg.senderUsername}</span>
                            </Link>
                            <p
                                style={{
                                    margin: "10px 0",
                                    fontFamily: "'Helvetica Neue', Helvetica, Arial, sans-serif",
                                    fontSize: "16px"
                                }}
                            >
                                {msg.content}
                            </p>
                            {postImageUrl && (
                                <img
                                    src={postImageUrl}
                                    alt="post image"
                                    style={{
                                        maxWidth: "100%",
                                        maxHeight: "400px",
                                        border: "1px solid #ccc",
                                        borderRadius: "4px",
                                        marginTop: "10px"
                                    }}
                                    onError={(e) => { e.target.style.display = "none" }}
                                />
                            )}
                        </div>
                    )
                })
            )}
        </div>
    )
}

export default Feed
