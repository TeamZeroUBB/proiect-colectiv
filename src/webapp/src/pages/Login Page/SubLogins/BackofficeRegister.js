import React, { Component } from 'react';
import Input from '../../../components/common/Input/Input';
import axios from 'axios';
import { Redirect } from 'react-router-dom';

const emailExistsError = "Sorry, but this email is already taken";
const incompleteError = "Please fill the required fields first";
const unknownError = "An unknown error occured";

class BackofficeRegister extends Component {
	constructor(props) {
		super(props);

		this.state = {
			globalErrors: null,
			redirectTo: null
		}

		this.username = React.createRef();
		this.password = React.createRef();
		this.rePassword = React.createRef();
	}

	allisValid(){
		return this.username.current.state.isValid &&
			this.password.current.state.isValid &&
			this.rePassword.current.state.isValid;
	}

	buttonClicked() {
		if(this.allisValid())
		{
			const formData = new FormData();
			formData.set("email", this.username.current.state.inputValue);
			formData.set("password", this.password.current.state.inputValue);
			formData.set("repeatPassword", this.rePassword.current.state.inputValue);

			axios.post("backoffice/account/register", formData)
			.then((response)=>{
				if(response.status === 200)
					this.setState({globalErrors: null, redirectTo: (<Redirect push to='/signupsuccess' />)})
			})
			.catch((err)=>{
				if(err.response.status === 400)
					this.setState({globalErrors: emailExistsError});
				else
					this.setState({globalErrors: unknownError});
			});
		}
		else
		{
			this.setState({globalErrors: incompleteError});
		}
	}

	render() {
		if(this.state.redirectTo)
			return this.state.redirectTo;

		return (
			<div className="loginpage-tab">
				<h1>Sign Up and post a job offer!</h1>
				<Input inputType="input" labelName="Username" type="text" rules={{ required: true, minLength: 3 }} ref={this.username} />
				<Input inputType="input" labelName="Password" type="password" rules={{ required: true, minLength: 8, minUpper: 1, minLower: 1 }} ref={this.password} />
				<Input inputType="input" labelName="Confirm Password" type="password" rules={{ required: true, shouldMatch: this.password }} errorMessage="Passwords do not match" ref={this.rePassword} />

				<button className="button" onClick={() => this.buttonClicked()}>Register!</button>
			</div>
		);
	}
}

export default BackofficeRegister;
