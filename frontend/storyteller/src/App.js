import './App.css';
import {BrowserRouter, Route, Routes} from "react-router-dom";
import WriteStory from "./views/writeStory";
import Main from "./views/main";

function App() {

    return (
            <BrowserRouter>
                <Routes>
                    <Route path="/" element={<Main/>}/>
                    <Route path="/write-story" element={<WriteStory/>}/>
                </Routes>
            </BrowserRouter>
    );
}

export default App;
