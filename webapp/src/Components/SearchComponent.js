import React , {Component} from 'react'
import axios from 'axios'
import Container from 'react-bootstrap/Container'

/***
 * @author Anvitha Karanam
 * Added front-end UI to accept user query(keyword) & display the filtered tweets results 
 ***/

class SearchComponent extends Component {

  constructor() {
    super()

    this.state = {
      tweets: [],
      search : ''
    }
  }

  handleSubmit =  event => {
    event.preventDefault();
    console.log("form data = "+ this.state.search)
      axios.get("http://localhost:8080/api/tweets/send?searchString="+this.state.search)
      .then(response => {
        console.log(response)
        this.setState({tweets : response.data})
      })
      .catch(error => {console.log(error)})
  }

  handleSearchQueryChange = (event) => {
      this.setState({
        search: event.target.value
      })
  }




    render() {
      const { tweets } = this.state
      return (
        <React.Fragment>
          <form onSubmit={this.handleSubmit}>
            <div>
            <label> Enter Search Keyword </label>
            <input  type="text" value={this.state.search} onChange={this.handleSearchQueryChange}/>
            </div>
            <button>Search</button>
          </form>
          <Container>
          {
          tweets.length ? 
          tweets.map(tweet => <p> {tweet} </p>) : null
          }
          </Container>


        </React.Fragment>
      )
    }
  }

export default SearchComponent;
  
