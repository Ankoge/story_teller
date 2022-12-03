import "./css/main.css"
import {Link} from "react-router-dom";

const Main = () => {
    return (
        <div className="main-container">
            <div className="options-container">
                <Link className="card-container" to="/tell-story"><img className="card-img"
                                                     src={require("../images/lectern-with-open-book.png")}
                                                     alt="lectern-with-open-book"/></Link>
                <Link className="card-container" to="/write-story"><img className="card-img"
                                                     src={require("../images/open-book-with-pen.png")}
                                                     alt="open-book-with-pen"/>
                </Link>
            </div>
        </div>
    )
}

export default Main;