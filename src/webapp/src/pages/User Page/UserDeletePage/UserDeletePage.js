import React, { Component } from 'react';
import axios from 'axios';
import "./UserDeletePage.css"

export default class UserDeletePage extends Component {
    constructor(props) {
        super(props);

        this.state = {
          username: '',
          successMessage: null,
          errorMessage: null,
        };

        this.handleChange = this.handleChange.bind(this);
    }

    handleChange(event) {
      this.setState({ username: event.target.username });

      console.log(this.state);
    }

    handleSubmit(event) {
        event.preventDefault();
        console.log(this.state.username);
        axios.delete(
            axios.defaults.baseURL + `delete/${this.state.username}`
        ).then(result => {
            this.setState({
                successMessage: true,
            });
        }).catch(err => {
            this.setState({
                errorMessage: true,
            });
        });
    }

    render() {
        // const user = this.props.session.user;
        const user = {
            userId: 2,
            isAdmin: true,
        };

        return (
            <div className="jumbotron user-delete-form">
                {this.state.successMessage && <span className="badge badge-primary response-message">User deleted!</span>}
                {this.state.errorMessage && <span className="badge badge-danger response-message">There was a problem!</span>}
                <form>
                    <div className="form-group">
                        <label>Username</label>
                        <input onChange={this.handleChange} className="form-control" type="text" value={this.state.username} aria-describedby="username" placeholder="Username"/>
                        <small id="emailHelp" className="form-text text-muted">Please enter the username of the user you want to delete.</small>
                    </div>

                    <input type="submit" className="btn btn-primary btn-submit" value="Delete" />
                </form>
            </div>
        );
    }
}
