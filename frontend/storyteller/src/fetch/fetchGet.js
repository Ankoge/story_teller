import axios from "./axiosInstance";


const fetchGet = async (url) => {
    try {
        console.log("In GET fetch with: ", url, {
            "headers": {
                "Authorization": "Bearer "
            }
        })
        const response = await axios.get(url)
        console.log("Fetch data: ", response.data, url)
        return response.data;
    } catch (e) {
        console.log("Error in GET fetch: ", e.message, url)
    }
}

export default fetchGet;