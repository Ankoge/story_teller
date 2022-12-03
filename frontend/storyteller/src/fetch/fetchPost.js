import axios from "./axiosInstance";

const fetchPost = async (url, body) => {
    try {
        console.log("In POST fetch with: ", url, {
            "headers": {
                "Authorization": "Bearer "
            }
        })
        const response = await axios.post(url, body)
        console.log("Fetch response: ", response.data, url)
    } catch (e) {
        console.log("Error in POST fetch: ", e.message, url)
    }
}

export default fetchPost;