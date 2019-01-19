import React, { Component } from 'react';
import axios from 'axios';
import { Grid, Row, Col } from "react-bootstrap";

import "./UserDetailsPage.css"

export default class UserDetailsPage extends Component {
    constructor(props) {
        super(props);
        this.state = {
            user: null
        }
    }

    componentDidMount() {
      axios.get(
          axios.defaults.baseURL + `user/${this.props.match.params.userId}`
      ).then(results => {
          this.setState({ user: results.data });
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
          </div>
        );
    }
}
