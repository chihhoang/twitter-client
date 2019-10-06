import React from "react";
import ReactDOM from "react-dom";
import "./index.css"; 
import "bootstrap/dist/css/bootstrap.min.css";
import { BrowserRouter, Route } from "react-router-dom";
import twitterpage from "./Components/Twitterpage";

ReactDOM.render(
  <BrowserRouter>
    <Route exact path="/twitter" component={twitterpage} />
  </BrowserRouter>,
  document.getElementById("root")
);
