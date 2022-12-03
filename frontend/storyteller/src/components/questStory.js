import {useState} from "react";
import {useEffectOnce} from "../hook/useEffectOnce";
import fetchGet from "../fetch/fetchGet";
import fetchPost from "../fetch/fetchPost";

const QuestStory = () => {
    const [quest, setQuest] = useState({
        name: "",
        description: "",
        location: "",
        giverNpc: ""
    })
    const [npcs, setNpc] = useState([]);
    const [nameColor, setNameColor] = useState("black");
    const [questNames, setQuestNames] = useState([]);
    const [isDisabled, setDisabled] = useState(true);
    const [locations, setLocations] = useState([]);

    function handleNameChange(event) {
        fetchGet("/quest/all-names/to-lower-case")
            .then(quests => setQuestNames(quests))
        const eventValue = event.target.value;
        if (!questNames.includes(eventValue.toLowerCase().replaceAll(" ", ""))
            && eventValue.length > 0) {
            setNameColor("black")
            setDisabled(false);
        } else {
            setNameColor("darkred")
            setDisabled(true);
        }
        setQuest({
            ...quest,
            name: event.target.value
        })
    }

    useEffectOnce(() => {
        fetchGet("/npc/all-names")
            .then(data => setNpc(data))
        fetchGet("/location/all-names")
            .then(data => setLocations(data));
    })

    function handleChange(event) {
        const value = event.target.value;
        setQuest({
            ...quest,
            [event.target.name]: value
        })
        console.log(quest.name, "\n", quest.description, "\n", quest.location)
    }


    function makeDropDown(options, optionType) {
        return (
            <select name={optionType}
                    onChange={handleChange}>
                <option key={optionType + "-option-none"}
                        value="None">
                    None
                </option>
                {options.map((option, index) =>
                    <option key={optionType + "-option-" + index}
                            value={option}>{option}
                    </option>)}
            </select>)

    }

    function handleSubmit() {
        fetchPost("/quest/save", quest)
            .then(() => {
                setQuest({
                    ...quest,
                    name: "",
                    description: "",
                    location: "",
                    giverNpc: ""
                });
                setDisabled(true);
            })
    }

    return npcs && locations && (
        <div className="story-container">
            <h3>Quest Story</h3>
            <form>
                <div className="name-container">
                    <h4>Name</h4>
                    <input type="text"
                           name="name"
                           style={{color: nameColor}}
                           value={quest.name}
                           onChange={handleNameChange}
                           required={true}/>
                </div>
                <div className="text-area-container">
                    <h4>Describe</h4>
                    <textarea name="description"
                              value={quest.description}
                              onChange={handleChange}>
                    </textarea>
                </div>
                <div className="drop-down-container">
                    <h4>Location</h4>
                    {makeDropDown(locations, "location")}
                </div>
                <div className="drop-down-container">
                    <h4>Giver</h4>
                    {makeDropDown(npcs, "giverNpc")}
                </div>
                <div className="submit-button-container">
                    <button className="submit-button"
                            type="button"
                            disabled={isDisabled}
                            onClick={handleSubmit}>
                        Add quest
                    </button>
                </div>
            </form>
        </div>
    )
}

export default QuestStory;