import React, { Component } from "react";
import ApiServices from "./ApiServices";
import Table from "react-bootstrap/Table";
import Container from "react-bootstrap/Container";
import Button from "react-bootstrap/Button";
import Row from "react-bootstrap/Row";
import Col from "react-bootstrap/Col";
import Card from "react-bootstrap/Card";

export default class ListUser extends Component {
  constructor(props) {
    super(props);
    this.state = {
      text: "",
      data: []
    };
  }

  componentDidMount() {
    this.gettweets();
  }

  tweet = () => {
    let formData = new FormData();
    formData.append("text", this.state.text);
    ApiServices.tweet(formData).then(res => {
      alert("Sucessfully tweeted");
      this.gettweets();
      this.setState({ text: "" });
    });
  };
  gettweets = () => {
    ApiServices.getTweets()
      .then(res => {
        this.setState({ data: res.data });
      })
      .catch(error => {
        alert("something went wrong!!");
        throw error;
      });
  };
  delete = id => {
    ApiServices.deleteTweet(id).then(res => {
      alert("Tweet deleted");
      this.gettweets();
    });
  };

  onChange = e => {
    this.setState({ [e.target.name]: e.target.value });
  };

  render() {
    return (
      <div>
        <Container>
          <Row className="justify-content-md-center">
            <h1 style={{ color: "#00acee" }}>TwitterPage</h1>
          </Row>
          <Row className="justify-content-md-center">
            <textarea
              rows="3"
              cols="60"
              type="text"
              name="text"
              placeholder="What's happening?"
              value={this.state.text}
              onChange={this.onChange}
            />
            <Button varaint="info" onClick={this.tweet}>
              Tweet
            </Button>
          </Row>

          <Row className="justify-content-md-center">
            <h2 style={{ color: "#00acee" }}>Tweets</h2>
          </Row>
          <Row>
            <Col></Col>
            <Col>
              <Table size="xl" responsive>
                <tbody>
                  {this.state.data.map(data => (
                    <div>
                      <tr id={data.id}>
                        <td>
                          <Card style={{ width: "35rem", height: "6rem" }}>
                            <Card.Body>
                              <Card.Title style={{ postion: "relative" }}>
                                {" "}
                                {data.name + " @" + data.screenName}>
                                <button
                                  style={{
                                    position: "absolute",
                                    right: "10px",
                                    top: "5px"
                                  }}
                                  onClick={() => this.delete(data.id)}
                                >
                                  x
                                </button>
                              </Card.Title>
                              <Card.Subtitle className="mb-2 text-muted">
                                {" "}
                              </Card.Subtitle>
                              <Card.Text>
                                <td>{data.text}</td>
                              </Card.Text>
                            </Card.Body>
                          </Card>
                        </td>
                      </tr>
                    </div>
                  ))}
                </tbody>
              </Table>
            </Col>
            <Col></Col>
          </Row>
        </Container>
      </div>
    );
  }
}
