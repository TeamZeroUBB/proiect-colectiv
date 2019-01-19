import React, { Component } from 'react';
import axios from 'axios';
import Input from '../../../components/common/Input/Input';
import { Redirect } from 'react-router-dom';

class LogIn extends Component {
	constructor(props) {
		super(props);

		this.state = {
			errorMessage: null,
			redirect: false,
			redirectId: null
		};

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
				.then((response) => {
					localStorage.setItem("user", JSON.stringify(response.data));
					this.setState({redirect: true, redirectId: response.data.id});
				})
				.catch((err) =>{
					if(err.response.status === 500)
						this.setState({showError: true, errorMessage: "Bad credentials. Try again."});

					setTimeout(()=>this.setState({errorMessage: null}), 7000)
				})
		}
	}

	render() {
		if(this.state.redirect)
			return <Redirect to={`/user/${this.state.redirectId}`} push={true}/>

		return (
			<div className="loginpage-tab">
				<h1>Welcome back!</h1>
				<div className="error-text error-text-color">{this.state.errorMessage}</div>
				<Input inputType="input" labelName="Username" type="text" rules={{ required: true }} ref={this.username} />
				<Input inputType="input" labelName="Password" type="password" rules={{ required: true }} ref={this.password} />
				<button className="button" onClick={this.loginButtonClicked}>Log In</button>
			</div>
		);
	}
}

export default LogIn;
