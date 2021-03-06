import React, { Component } from 'react';
import axios from 'axios';
import "./UserDetailsPage.css"
import UserProfile from './UserProfile/UserProfile';
import Navigator from "../../../components/Navigator/Navigator";
const noFileSelected = "Please select a file first";
const fileUpSuccess = "Success, you just uploaded your CV";
const fileUpError = "Something went wrong uploading CV";

export default class UserDetailsPage extends Component {
    constructor(props) {
        super(props);
        this.state = {
            user: null,
            selectedFile: null,
            loaded: 0,
            showError: false,
            errorMessage: ""
        }
    }

    componentDidMount() {
        axios.get(`user/${this.props.match.params.userId}`)
            .then(results => this.setState({ user: results.data }));
    }

    handleSelectedFile = event => {
        this.setState({
            selectedFile: event.target.files[0],
            loaded: 0
        })
    }

    handleUpload = () => {
        if (this.state.selectedFile === null) {
            this.setState({ showError: true, errorMessage: noFileSelected });
            return;
        }
        console.log(this.state.selectedFile);

        const data = new FormData()
        data.append('file', this.state.selectedFile, this.state.selectedFile.name)

        const config = {
            headers: { 'content-type': 'multipart/form-data' },
            onUploadProgress: ProgressEvent => this.setState({
                loaded: (ProgressEvent.loaded / ProgressEvent.total * 100)
            })
        }
        axios.put(`add-cv/${this.state.user.id}`, data, config)
            .then(res => this.setState({showError: true, errorMessage: fileUpSuccess}))
            .catch(err => this.setState({showError: true, errorMessage: fileUpError}))
    }

    render() {
        const user = this.state.user;

        if (!user) {
            return null;
        }

        return (
            <div>
                <Navigator
                    searchCallback={this.filterJobs}
                    addClickedCallback={this.addClickedCallback}
                />
                <UserProfile
                    firstName={user.firstName}
                    lastName={user.lastName}
                    email={user.email}
                    username={user.username}
                    password={user.password}
                    phoneNumber={user.phoneNumber}
                    city={user.city} />

                    <form className="udp-upload">
                        {this.state.showError && <div className="baga ceva stiling aici">{this.state.errorMessage}</div>}
                        <div className="file-path-wrapper">
                            <label>{user.isCvUploaded ? 'CV Re-upload' : 'CV Upload'}</label>
                            <input type="file" className="file-path validate" id="exampleFormControlFile1" onChange={this.handleSelectedFile} />
                        </div>
                        <input type="button"  value="Upload!" onClick={this.handleUpload} />
                    </form>
            </div>
        );
    }
}
