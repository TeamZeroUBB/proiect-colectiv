import React, { Component } from 'react';
import axios from 'axios';

import "./UserDetailsPage.css"

export default class UserDetailsPage extends Component {
    constructor(props) {
        super(props);

        axios.get(
            axios.defaults.baseURL + `user/${props.match.params.userId}`
        ).then(results => {
            this.user = results
        });
    }

    render() {
        return (
            <div id="jobsSubContainer">
                {this.user}
            </div>
        );
    }
}
