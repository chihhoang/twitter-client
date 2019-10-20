import React from "react";
import ReactDOM from "react-dom";
import "./index.css";
import "bootstrap/dist/css/bootstrap.min.css";
import { BrowserRouter, Route, Switch, Link } from "react-router-dom";
import twitterpage from "./Components/Twitterpage";
import SearchComponent from "./Components/SearchComponent";

/***
 * @author Manisha Yacham
 ***/

ReactDOM.render(
  <BrowserRouter>
    <div>
      <nav className="navbar navbar-expand-lg navbar-light bg-light">
        <ul className="navbar-nav mr-auto">
          <li>
            <Link to={"/"} className="nav-link">
              Home
            </Link>
          </li>
          <li>
            <Link to={"/search"} className="nav-link">
              Search
            </Link>
          </li>
        </ul>
      </nav>
      <hr />
      <Switch>
        <Route exact path="/" component={twitterpage} />
        <Route path="/search" component={SearchComponent} />
      </Switch>
    </div>
  </BrowserRouter>,
  document.getElementById("root")
);
