import React, { Component } from 'react';
import Input from '../../../components/common/Input/Input';
import axios from 'axios';
import { Redirect } from 'react-router-dom';

const emailExistsError = "Sorry, but this email is already taken";
const incompleteError = "Please fill the required fields first";
const unknownError = "An unknown error occured";

class ClientRegister extends Component {
	constructor(props) {
		super(props);

		this.state={
			globalErrors: null,
			redirectTo: null
		};

		this.email = React.createRef();
		this.password = React.createRef();
		this.rePassword = React.createRef();
		this.firstName = React.createRef();
		this.lastName = React.createRef();
		this.town = React.createRef();
		this.address = React.createRef();
		this.zipcode = React.createRef();
		this.phone = React.createRef();
	}

	allIsValid() {
		return this.email.current.state.isValid &&
			this.password.current.state.isValid &&
			this.rePassword.current.state.isValid;

	}

	onLogInClicked = () => {
		if (this.allIsValid()) {
			const formData = new FormData();
			formData.set("email", this.email.current.state.inputValue);
			formData.set("password", this.password.current.state.inputValue);
			formData.set("repeatPassword", this.rePassword.current.state.inputValue);
			formData.set("firstName", this.firstName.current.state.inputValue);
			formData.set("lastName", this.lastName.current.state.inputValue);
			formData.set("town", this.town.current.state.inputValue);
			formData.set("address", this.address.current.state.inputValue);
			formData.set("zipCode", this.zipcode.current.state.inputValue);
			formData.set("phoneNumber", this.phone.current.state.inputValue);

			axios.post("client/account/register", formData)
				.then((response) => {
					console.log(response);
					if(response.status === 200)
						this.setState({ globalErrors: null, redirectTo: (<Redirect push to='/signupsuccess' />) })
				})
				.catch((err) => {
					if(err.response.status === 400)
						this.setState({globalErrors: emailExistsError});
					else
						this.setState({globalErrors: unknownError});
				});
		}
		else
			this.setState({globalErrors: incompleteError});
	};

	render() {
		if(this.state.redirectTo)
			return this.state.redirectTo;

		return (
			<div className="loginpage-tab">
				<h1>Sign Up and earn some money!</h1>
				<p className="error-text-color error-text">{this.state.globalErrors}</p>
				<Input inputType="input" labelName="Email" type="text" rules={{ required: true }} ref={this.email} />
				<Input inputType="input" labelName="Password" type="password" rules={{ required: true, minLength: 3 }} ref={this.password} />
				<Input inputType="input" labelName="Confirm Password" type="password" errorMessage="Passwords do not match" rules={{ required: true, shouldMatch: this.password }} ref={this.rePassword} />
				<Input inputType="input" labelName="First Name" type="text" rules={{ required: true, minLength: 3 }} ref={this.firstName} />
				<Input inputType="input" labelName="Last Name" type="text" rules={{ required: true, minLength: 3 }} ref={this.lastName} />
				<Input inputType="input" labelName="Town" type="text" rules={{ minLength: 3 }} ref={this.town} />
				<Input inputType="input" labelName="Address" type="text" rules={{ minLength: 3 }} ref={this.address} />
				<Input inputType="input" labelName="Zip-code" type="text" ref={this.zipcode} />
				<Input inputType="input" labelName="Phone Number" type="tel" rules={{ minLength: 3 }} ref={this.phone} />
				<button className="button" onClick={this.onLogInClicked}>Log In</button>
			</div>
		);
	}
}

export default ClientRegister;
