import { useState, useEffect } from "react";
const Post = () => {

    const [error, setError] = useState(null);
    const [isLoaded, setIsLoaded] = useState(false);
    const [postList, setPostList] = useState([]);

    useEffect(() => {
        fetch("http://localhost:8080/v1/posts")
            .then(res => res.json())
            .then(
                (result) => {
                    console.log(result);
                    setIsLoaded(true);
                    setPostList(result);
                    
                },
                (error) => {
                    console.log(error);
                    setIsLoaded(true);
                    setError(error);
                }
            )
    }, [])

    if (error) {
        return <><div>Error !!!</div></>
    } else if (!isLoaded) {
        return <><div>Loading...</div></>
    } else {
        return (
            <>
                <ul>
                    {postList.map(post=> (
                        <li>
                            {post.title} = {post.text}
                        </li>
                    ))}
                </ul>
            </>
        );
    }
}

export default Post