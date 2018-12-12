import React, { Component } from 'react';
import './LoginPage.css';
import LogIn from './SubLogins/LogIn';
import BackofficeRegister from './SubLogins/BackofficeRegister';
import ClientRegister from './SubLogins/ClientRegister';

class LoginPage extends Component {
	constructor(props) {
		super(props);

		this.state = {
			activeWindow: 1
		};
	}

	onLoginIsActive = () => this.setState({ activeWindow: 1 });

	onRegisterBackofficeIsActive = () => this.setState({ activeWindow: 2 });

	onRegisterClientIsActive = () => this.setState({ activeWindow: 3 });

	render() {
		return (
			<div className="loginpage">
				<div className="loginpage-form">
					<ul className="loginpage-tab-group">
						<li className={`tab${this.state.activeWindow === 1 ? " active" : ""}`} onClick={this.onLoginIsActive}>Log In</li>
						<li className={`tab${this.state.activeWindow === 2 ? " active" : ""}`} onClick={this.onRegisterBackofficeIsActive}>Sign Up Backoffice</li>
						<li className={`tab${this.state.activeWindow === 3 ? " active" : ""}`} onClick={this.onRegisterClientIsActive}>Sign Up Client</li>
					</ul>

					<div className="loginpage-tab-content">

						{this.state.activeWindow === 1 && <LogIn />}

						{this.state.activeWindow === 2 && <BackofficeRegister />}

						{this.state.activeWindow === 3 && <ClientRegister />}
					</div>
				</div>
			</div>
		);
	}
}

export default LoginPage;
