import "./css/locationStory.css"
import {useState} from "react";
import fetchGet from "../fetch/fetchGet";
import fetchPost from "../fetch/fetchPost";

const LocationStory = ({storyName}) => {
    const [location, setLocation] = useState({
        name: "",
        description: "",
        storyName: storyName
    });
    const [locationNames, setLocationNames] = useState([]);
    const [nameColor, setNameColor] = useState("black");
    const [isDisabled, setDisabled] = useState(true);
    const [saveBacklog, setSaveBacklog] = useState("")

    function handleNameChange(event) {
        fetchGet("/location/" + storyName + "/all-names/to-lower-case")
            .then(locations => setLocationNames(locations))
        const eventValue = event.target.value;
        if (!locationNames.includes(eventValue.toLowerCase().replaceAll(" ", ""))
            && eventValue.length > 0) {
            setNameColor("black")
            setDisabled(false)
        } else {
            setNameColor("darkred")
            console.log(location.name)
            setDisabled(true)
        }
        setLocation({
            ...location,
            name: event.target.value
        })
    }

    function handleSubmit() {
        fetchPost("/location/save", location)
            .then(() => setSaveBacklog(location.name))
            .then(() => {
                setLocation({
                    ...location,
                    name: "",
                    description: "",
                    storyName: storyName
                });
                setDisabled(true)
            })
            .then(() => setTimeout(() => setSaveBacklog(""), 2000))
    }

    function handleChange(event) {
        const value = event.target.value;
        setLocation({
            ...location,
            [event.target.name]: value
        })
        console.log(location.name, "\n", location.description)
    }

    return (
        <div className="story-container">
            <h3>Location Story</h3>
            <form>
                <div className="name-container">
                    <h4>Name</h4>
                    <input type="text"
                           name="name"
                           style={{color: nameColor}}
                           value={location.name}
                           onChange={handleNameChange}
                           required={true}/>
                </div>
                <div className="text-area-container">
                    <h4>Describe</h4>
                    <textarea name="description"
                              value={location.description}
                              onChange={handleChange}>
                    </textarea>
                </div>
                <div className="submit-button-container">
                    <button className="submit-button"
                            id="location-submit-button"
                            type="button"
                            disabled={isDisabled}
                            onClick={handleSubmit}>
                        Add location
                    </button>
                    <div className={"save-backlog-".concat(saveBacklog === "" ? "inactive" : "active")}>
                    Raven arrived with letter {saveBacklog}</div>
                </div>
            </form>
        </div>
    )
}

export default LocationStory;