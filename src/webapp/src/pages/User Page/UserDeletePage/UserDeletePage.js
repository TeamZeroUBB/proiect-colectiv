import React, { Component } from 'react';
import axios from 'axios';
import "./UserDeletePage.css"
import Navigator from "../../../components/Navigator/Navigator";

export default class UserDeletePage extends Component {
    constructor(props) {
        super(props);

        this.state = {
            username: "",
            successMessage: null,
            errorMessage: null,
        };
    }

    handleChange = (event) => this.setState({ username: event.target.value });

    handleSubmit = () => {
        axios.delete(`delete/${this.state.username}`)
            .then(result => this.setState({ successMessage: true,  errorMessage: false }))
            .catch(err => this.setState({ errorMessage: true, successMessage: false }));
    }

    render() {
        return (
            <div>
                <Navigator
                    searchCallback={this.filterJobs}
                    addClickedCallback={this.addClickedCallback}
                />
                <div className="jumbotron user-delete-form">
                    {this.state.successMessage && <span className="badge badge-primary response-message">User deleted!</span>}
                    {this.state.errorMessage && <span className="badge badge-danger response-message">There was a problem!</span>}
                    <form>
                        <div className="form-group">
                            <label>Username</label>
                            <input onChange={this.handleChange} className="form-control" type="text" value={this.state.username} aria-describedby="username" placeholder="Username" />
                            <small id="emailHelp" className="form-text text-muted">Please enter the username of the user you want to delete.</small>
                        </div>
                        <input type="button" className="btn btn-primary btn-submit" value="Delete" onClick={this.handleSubmit} />
                    </form>
                </div>
            </div>
        );
    }
}
