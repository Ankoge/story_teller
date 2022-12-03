import LocationStory from "../components/locationStory";
import QuestStory from "../components/questStory";
import NpcStory from "../components/npcStory";
import "./css/story.css"
import {useState} from "react";
import {useEffectOnce} from "../hook/useEffectOnce";
import fetchGet from "../fetch/fetchGet";
import fetchPost from "../fetch/fetchPost";


const WriteStory = () => {
    const [storyName, setStoryName] = useState(undefined);
    const [storyNames, setStoryNames] = useState([]);
    const [nameColor, setNameColor] = useState("black");
    const [isDisabled, setDisabled] = useState(true);
    const [story, setStory] = useState({
        name: ""
    })
    const [tabIndex, setTabIndex] = useState(0);
    const tabNames = ["Location", "Npc", "Quest"]

    useEffectOnce(() => {
        fetchGet("/story/all-names")
            .then(data => setStoryNames(data))
    })

    function handleChange(event) {
        setStoryName(event.target.value === "None" ? undefined : event.target.value)
    }

    function handleNameChange(event) {
        const eventValue = event.target.value;
        if (!storyNames.includes(eventValue.toLowerCase().replaceAll(" ", ""))
            && eventValue.length > 0) {
            setNameColor("black")
            setDisabled(false)
        } else {
            setNameColor("darkred")
            setDisabled(true)
        }
        setStory({
            ...story,
            name: eventValue
        })
    }

    function handleSubmit() {
        fetchPost("/story/save", "")
            .then();
        setDisabled(true)
    }

    function handleTabClick(event) {
        const tabIndex = parseInt(event.target.dataset.tab, 10);
        setTabIndex(tabIndex);

    }

    return (
        <div>
            <h2>Write Your Story</h2>
            <div className="story-choose-container">
                <div className="drop-down-container">
                    <select name="storyId"
                            onChange={handleChange}>
                        <option key={"story-option-none"}
                                value={undefined}
                        >None
                        </option>
                        {storyNames.map((storyName, index) => {
                            return <option key={"story-" + index}
                                           value={storyName}
                            >{storyName}</option>
                        })}
                    </select>
                </div>
                <div className="story-name-container">
                    <input type="text"
                           name="name"
                           onChange={handleNameChange}
                           required={true}
                           value={story.name}
                           style={{color: nameColor}}/><button id="story-submit" className="submit-button"
                                                               type="button"
                                                               disabled={isDisabled}
                                                               onClick={handleSubmit}>
                    Add story
                </button>

                </div>
                <div  className="submit-button-container">

                </div>
            </div>
            <div className="tab-container">
                {tabNames.map((name, index) => (
                    <button key={"tab-".concat(index.toString(10))}
                        data-tab={index}
                    onClick={handleTabClick}
                    className={"tab tab-".concat(tabIndex === index ? "active" : "inactive")}>
                        {name}
                    </button>
                ))}
            </div>
            <section className={"tab-item tab-item-".concat(tabIndex === 0 ? "active" : "inactive")}>
                {storyName && <LocationStory storyName={storyName}/>}</section>
            <section className={"tab-item tab-item-".concat(tabIndex === 1 ? "active" : "inactive")}>
                {storyName && <NpcStory storyName={storyName}/>}</section>
            <section className={"tab-item tab-item-".concat(tabIndex === 2 ? "active" : "inactive")}>
                {storyName && <QuestStory storyName={storyName}/>}</section>
        </div>
    )
}

export default WriteStory;