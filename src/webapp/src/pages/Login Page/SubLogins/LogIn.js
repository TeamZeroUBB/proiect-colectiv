import React, { Component } from 'react';
import axios from 'axios';
import Input from '../../../components/common/Input/Input';

class LogIn extends Component {
	constructor(props) {
		super(props);

		this.username = React.createRef();
		this.password = React.createRef();
	}

	allIsValid() {
		return this.username.current.state.isValid &&
			this.password.current.state.isValid;
	}

	loginButtonClicked = () => {
		if (this.allIsValid()) {
			const body = {
				username: this.username.current.state.inputValue,
				password: this.password.current.state.inputValue
			};
			axios.post("login", body)
			.then((success)=>console.log("Success! returned ", success))
			.catch((err)=>console.log("Error, returned ", err))
		}
	}

	render() {
		return (
			<div className="loginpage-tab">
				<h1>Welcome back!</h1>
				<Input inputType="input" labelName="Username" type="text" rules={{ required: true, minLength: 3 }} ref={this.username} />
				<Input inputType="input" labelName="Password" type="password" rules={{ required: true, minLength: 3 }} ref={this.password} />
				<button className="button" onClick={this.loginButtonClicked}>Log In</button>
			</div>
		);
	}
}

export default LogIn;
