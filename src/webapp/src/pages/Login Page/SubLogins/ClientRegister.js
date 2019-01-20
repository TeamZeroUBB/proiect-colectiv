import React, { Component } from 'react';
import Input from '../../../components/common/Input/Input';
import axios from 'axios';
import { Redirect } from 'react-router-dom';
import i18n from '../../../i18n';

const emailExistsError = "Sorry, but this email is already taken";
const incompleteError = "Please fill the required fields first";
const unknownError = "An unknown error occured";

class ClientRegister extends Component {
	constructor(props) {
		super(props);

		this.state = {
			globalErrors: null,
			redirectTo: null
		};

		this.username = React.createRef();
		this.password = React.createRef();
		this.rePassword = React.createRef();
		this.firstName = React.createRef();
		this.lastName = React.createRef();
		this.email = React.createRef();
		this.city = React.createRef();
	}

	allIsValid() {
		return this.username.current.state.isValid &&
			this.email.current.state.isValid &&
			this.password.current.state.isValid &&
			this.rePassword.current.state.isValid;

	}

	onLogInClicked = () => {
		if (this.allIsValid()) {
			// const formData = new FormData();
			// formData.set("username",this.username.current.state.inputValue);
			// formData.set("password",this.password.current.state.inputValue);
			// formData.set("firstName",this.firstName.current.state.inputValue);
			// formData.set("lastName",this.lastName.current.state.inputValue);
			// formData.set("email",	this.email.current.state.inputValue);
			// formData.set("city",	this.city.current.state.inputValue);

			const formData = {
				"username": this.username.current.state.inputValue,
				"password": this.password.current.state.inputValue,
				"firstName": this.firstName.current.state.inputValue,
				"lastName": this.lastName.current.state.inputValue,
				"email": this.email.current.state.inputValue,
				"city": this.city.current.state.inputValue
			};

			axios.post("register", formData)
				.then((response) => {
					console.log(response);
					if (response.status === 200)
						this.setState({ globalErrors: null, redirectTo: (<Redirect push to='/signupsuccess' />) })
				})
				.catch((err) => {
					this.setState({ globalErrors: i18n.t("emailExistsError") });
					// if(err.response.status === 400)
					// 	this.setState({globalErrors: emailExistsError});
					// else
					// 	this.setState({globalErrors: unknownError});
				});
		}
		else
			this.setState({ globalErrors: i18n.t("incompleteError") });
	};

	render() {
		if (this.state.redirectTo)
			return this.state.redirectTo;

		return (
			<div className="loginpage-tab">
				<h1>{i18n.t("signUpClientMsg")}</h1>
				<p className="error-text-color error-text">{this.state.globalErrors}</p>
				<Input inputType="input" labelName={i18n.t("username")} type="text" rules={{ required: true, minLength: 5 }} ref={this.username} />
				<Input inputType="input" labelName={i18n.t("password")} type="password" rules={{ required: true, minLength: 3 }} ref={this.password} />
				<Input inputType="input" labelName={i18n.t("repassword")} type="password" errorMessage={i18n.t("input_match")} rules={{ required: true, shouldMatch: this.password }} ref={this.rePassword} />
				<Input inputType="input" labelName={i18n.t("firstname")} type="text" rules={{ minLength: 2 }} ref={this.firstName} />
				<Input inputType="input" labelName={i18n.t("lastname")} type="text" rules={{ minLength: 2 }} ref={this.lastName} />
				<Input inputType="input" labelName={i18n.t("email")} type="text" rules={{ required: true, minLength: 3 }} ref={this.email} />
				<Input inputType="input" labelName={i18n.t("city")} type="text" rules={{ minLength: 2 }} ref={this.city} />
				<button className="button" onClick={this.onLogInClicked}>{i18n.t("register")}</button>
			</div>
		);
	}
}

export default ClientRegister;
