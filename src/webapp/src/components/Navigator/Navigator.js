import React, { Component } from 'react';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faUser, faPlus } from '@fortawesome/free-solid-svg-icons';
import {Link} from 'react-router-dom';
import './Navigator.css';

class Navigator extends Component {
	render() {
		return (
			<div className="navi">
				<div className="navi-left">
					<div className="navi-logo" />
				</div>

				<div className="navi-right">
					<Link to="/login" className="navi-button navi-account">
						<FontAwesomeIcon className="fa-fw" icon={faUser} />
						My Account
					</Link>
					<Link to="/login" className="navi-button navi-addpost">
						<FontAwesomeIcon className="fa-fw" icon={faPlus} />
						Create a new offer
					</Link>
				</div>
			</div>
		);
	}
}

export default Navigator;
