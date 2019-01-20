import React, { Component } from 'react';
import Input from '../../../components/common/Input/Input';
import axios from 'axios';
import { Redirect } from 'react-router-dom';
import i18n from '../../../i18n';

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
		if(this.allisValid()) {
			axios.post("/register", {
				email: this.username.current.state.inputValue,
				password: this.password.current.state.inputValue,
				repeatPassword: this.rePassword.current.state.inputValue,
			}).then((response)=>{
				if(response.status === 200)
					this.setState({globalErrors: null, redirectTo: (<Redirect push to='/signupsuccess' />)})
			}).catch((err)=>{
				this.setState({globalErrors: i18n.t("emailExists")});
				// if(err.response.status === 400)
				// 	this.setState({globalErrors: i18n.t("emailExists")});
				// else
				// 	this.setState({globalErrors: });
			});
		}
		else
		{
			this.setState({globalErrors: i18n.t("incompleteError")});
		}
	}

	render() {
		if(this.state.redirectTo)
			return this.state.redirectTo;

		return (
			<div className="loginpage-tab">
				<h1>{i18n.t("signUpBackofficeMsg")}</h1>
				<Input inputType="input" labelName={i18n.t("username")} type="text" rules={{ required: true, minLength: 3 }} ref={this.username} />
				<Input inputType="input" labelName={i18n.t("password")} type="password" rules={{ required: true, minLength: 8, minUpper: 1, minLower: 1 }} ref={this.password} />
				<Input inputType="input" labelName={i18n.t("repassword")} type="password" rules={{ required: true, shouldMatch: this.password }} errorMessage={i18n.t("input_match")} ref={this.rePassword} />

				<button className="button" onClick={() => this.buttonClicked()}>{i18n.t("register")}</button>
			</div>
		);
	}
}

export default BackofficeRegister;
