import axios from "axios";

//CHANGE THE URL PATH WHILE TESTING
const TWITTER_URL = "http://localhost:8080";

class ApiServices {
  tweet(formData) {
    return axios.post(TWITTER_URL + "/api/tweets", formData, {
      headers: {
        "content-type": "multipart/form-data"
      }
    });
  }

  getTweets() {
    return axios.get(TWITTER_URL + "/api/tweets/userTimeline");
  }

  deleteTweet(id) {
    return axios.delete(TWITTER_URL + "/api/tweets/" + id);
  }
}

export default new ApiServices();
