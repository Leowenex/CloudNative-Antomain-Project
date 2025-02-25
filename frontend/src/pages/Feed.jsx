import { useEffect, useState } from "react"
import axios from "axios"
import { useParams, Link } from "react-router-dom"

function Feed() {
    const [messages, setMessages] = useState([])
    const token = localStorage.getItem("token")
    const { username: routeUsername } = useParams()

    useEffect(() => {
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
                console.error("Erreur lors de la récupération des messages : ", error)
            }
        }
        fetchMessages()
    }, [token, routeUsername])

    return (
        <div className="container">
            <h2>Feed</h2>
            {messages.length === 0 && <p>Aucun message pour le moment.</p>}

            {messages.map((msg) => {
                const profilePicUrl = msg.senderProfilePictureUrl
                    ? msg.senderProfilePictureUrl
                    : "https://upload.wikimedia.org/wikipedia/commons/7/7c/Profile_avatar_placeholder_large.png?20150327203541"

                const postImageUrl = msg.messagePictureUrl || null

                return (
                    <div key={msg.id} className="message-card">
                        <Link to={`/feed/${msg.senderUsername}`} className="msg-user">
                            <img
                                src={profilePicUrl}
                                alt="avatar"
                                style={{ width: "30px", height: "30px", borderRadius: "50%" }}
                            />
                            <span>{msg.senderUsername}</span>
                        </Link>
                        <p>{msg.content}</p>
                        {postImageUrl && (
                            <img
                                src={postImageUrl}
                                alt="post image"
                                style={{ maxWidth: "200px", display: "block", marginTop: "10px" }}
                            />
                        )}
                    </div>
                )
            })}
        </div>
    )
}

export default Feed
