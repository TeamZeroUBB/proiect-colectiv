import React, { Component } from 'react';
import axios from 'axios';
import { Grid, Row, Col } from "react-bootstrap";

import "./UserDetailsPage.css"

export default class UserDetailsPage extends Component {
    constructor(props) {
        super(props);
        this.state = {
            user: null,
            selectedFile: null,
            loaded: 0,
        }
    }

    componentDidMount() {
      axios.get(`user/${this.props.match.params.userId}`)
      .then(results => {
          this.setState({ user: results.data });
      });
    }

    handleSelectedFile = event => {
      this.setState({
        selectedFile: event.target.files[0],
        loaded: 0,
      })
    }

    handleUpload = () => {
      const data = new FormData()
      data.append('file', this.state.selectedFile, this.state.selectedFile.name)

      axios
          .post((`add-cv/${user.userId}`, data, {
              onUploadProgress: ProgressEvent => {
                  this.setState({
                      loaded: (ProgressEvent.loaded / ProgressEvent.total*100),
                  })
              },
          })
          .then(res => {
              console.log(res.statusText);
          });
      }

    render() {
        const user = this.state.user;

        if (!user) {
          return null;
        }

        return (
          <div className="card md-6 main-card">
              <div className="card-body">
                  <h5 className="card-title">{user.username}</h5>
                  <h6 className="card-subtitle mb-2 text-muted">{user.lastName + ' ' + user.firstName}</h6>
                  <h6 className="card-subtitle mb-2 text-muted">{user.email}</h6>
              </div>

              <form>
                  <div class="form-group" onSubmit={this.handleSubmit}>
                      <label>{user.isCvUploaded ? 'CV Re-upload' : 'CV Upload'}</label>
                      <input type="file" class="form-control-file" id="exampleFormControlFile1" onChange={this.handleSelectedFile}/>
                  </div>
              </form>
          </div>
        );
    }
}
