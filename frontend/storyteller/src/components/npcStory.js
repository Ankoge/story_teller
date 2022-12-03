import {useState} from "react";
import fetchGet from "../fetch/fetchGet";
import "./css/npcStory.css"
import fetchPost from "../fetch/fetchPost";
import {useEffectOnce} from "../hook/useEffectOnce";

const NpcStory = ({storyName}) => {
    const [npc, setNpc] = useState({
        name: "",
        appearance: "",
        behavior: "",
        story: "",
        location: "",
        type: "PEASANT"
    })
    const [nameColor, setNameColor] = useState("black");
    const [npcNames, setNpcNames] = useState([]);
    const [isDisabled, setDisabled] = useState(true)
    const [locationNames, setLocationNames] = useState([]);
    const [types, setTypes] = useState([]);

    function handleNameChange(event) {
        fetchGet("/npc/all-names/to-lower-case")
            .then(npcs => setNpcNames(npcs))
        const eventValue = event.target.value;
        if (!npcNames.includes(eventValue.toLowerCase().replaceAll(" ", ""))
            && eventValue.length > 0) {

            setNameColor("black")
            setDisabled(false);
        } else {
            setNameColor("darkred")
            setDisabled(true);
        }
        setNpc({
            ...npc,
            name: event.target.value
        })
    }

    function handleChange(event) {
        const value = event.target.value;
        setNpc({
            ...npc,
            [event.target.name]: value
        })
    }

    useEffectOnce(() => {
        fetchGet("/location/all-names")
            .then(data => setLocationNames(data));
        fetchGet("/npc/types")
            .then(data => setTypes(data));
    })

    function makeTypeDropDown() {
        return (
            <select name="type"
                    onChange={handleChange}
                    onLoad={handleChange}
                    value="PEASANT">
                {types.map((type, index) =>
                    <option key={"type-option-" + index}
                            value={type}>{type}
                    </option>)}
            </select>)
    }

    function makeLocationDropDown() {
        return (
            <select name="location"
                    onChange={handleChange}
            onClick={handleLocationDropdownClick}>
                <option key="location-option-none"
                        value="None">
                    None
                </option>
                {locationNames.map((location, index) =>
                    <option key={"location-option-" + index}
                            value={location}>{location}
                    </option>)}
            </select>)
    }

    function handleLocationDropdownClick() {
        fetchGet("/location/all-names")
            .then(data => setLocationNames(data));
    }

    function handleSubmit() {
        fetchPost("/npc/save", npc)
            .then(() => {
                setNpc({
                    ...npc,
                    name: "",
                    appearance: "",
                    behavior: "",
                    story: "",
                    location: "",
                    type: "PEASANT"
                });
                setDisabled(true)
            })
    }

    return npcNames && locationNames && types && (
        <div className="story-container">
            <h3>Npc Story</h3>
            <form>
                <div className="name-container">
                    <h4>Name</h4>
                    <input type="text"
                           name="name"
                           style={{color: nameColor}}
                           value={npc.name}
                           onChange={handleNameChange}
                           required={true}/>
                </div>
                <div className="text-area-container">
                    <h4>Appearance</h4>
                    <textarea className="text-area-appearance"
                              name="appearance"
                              value={npc.appearance}
                              onChange={handleChange}>
                    </textarea>
                </div>
                <div className="text-area-container">
                    <h4>Behavior</h4>
                    <textarea className="text-area-behavior"
                              name="behavior"
                              value={npc.behavior}
                              onChange={handleChange}>
                    </textarea>
                </div>
                <div className="text-area-container">
                    <h4>Story</h4>
                    <textarea name="story"
                              value={npc.story}
                              onChange={handleChange}>
                    </textarea>
                </div>
                <div className="drop-down-container">
                    <h4>Location</h4>
                    {makeLocationDropDown()}
                </div>
                <div className="drop-down-container">
                    <h4>Type</h4>
                    {makeTypeDropDown()}
                </div>
                <div className="submit-button-container">
                    <button className="submit-button"
                            type="button"
                            disabled={isDisabled}
                            onClick={handleSubmit}>
                        Add npc
                    </button>
                </div>
            </form>
        </div>
    )
}

export default NpcStory;