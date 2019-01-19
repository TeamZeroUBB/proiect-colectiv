import React, { Component } from 'react';
import { Link } from 'react-router-dom';

import "./JobsComponent.css";
import Modal from "../Modal/Modal";

//a dictionary with a default type
export var typeToPicture = new Proxy(
	{
		"0": "https://www.insidescience.org/sites/default/files/5_heic1808a_crop.jpg",
		"1": "https://static.independent.co.uk/s3fs-public/thumbnails/image/2013/01/24/12/v2-cute-cat-picture.jpg?w968h681",
		"2": "https://ichef.bbci.co.uk/news/976/cpsprodpb/2E2E/production/_102622811_sunrise.jpg",
		"3": "https://gfnc1kn6pi-flywheel.netdna-ssl.com/wp-content/uploads/2017/12/pictures-of-golden-retrievers.jpg",
		"4": "http://coolwildlife.com/wp-content/uploads/galleries/post-3004/Fox%20Picture%20006.jpg",
		"5": "https://images.unsplash.com/photo-1533651180995-3b8dcd33e834?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&w=1000&q=80",
		"6": "https://amp.businessinsider.com/images/55b00ec72acae716008b920d-750-563.jpg",
	},
	{
		get: function (object, property) {
			//default value if the type is not supported
			return object.hasOwnProperty(property) ? object[property] : "https://i.kym-cdn.com/entries/icons/original/000/000/091/TrollFace.jpg";
		}
	}
);

export default class JobsComponent extends Component {
	constructor(props) {
		super(props);
		this.state = {
			showModal: false
		}
	}

	jobOfferClickHandler = () => {
		this.setState({
			showModal: true
		})
	};

	jobOfferCloseHandler = () => {
		this.setState({
			showModal: false
		})
	};

	deleteJob = () => {
		const job = this.props.job;
		const deleteCallback = this.props.deleteCallback;
		// const user = props.session.user.userId;
    const user = {
        userId: 2,
        isAdmin: true,
    };
		deleteCallback(job.id);

		axios.get(
				axios.defaults.baseURL + `/job-offer-service/delete/${user.userId}/${job.jobOfferId}`
		).then(results => {
				this.setState({ user: results.data });
		});
	};

	render() {
		const job = this.props.job;
		const user = props.session.user.userId;

		return (
			<div id="jobOfferContainer">
				{	 user.isAdmin && user.userId === job.userId &&
					<div id="deleteJob">
						<img id="deleteIcon" onClick={this.deleteJob} src="data:image/svg+xml;utf8;base64,PD94bWwgdmVyc2lvbj0iMS4wIiBlbmNvZGluZz0iaXNvLTg4NTktMSI/Pgo8IS0tIEdlbmVyYXRvcjogQWRvYmUgSWxsdXN0cmF0b3IgMTkuMS4wLCBTVkcgRXhwb3J0IFBsdWctSW4gLiBTVkcgVmVyc2lvbjogNi4wMCBCdWlsZCAwKSAgLS0+CjxzdmcgeG1sbnM9Imh0dHA6Ly93d3cudzMub3JnLzIwMDAvc3ZnIiB4bWxuczp4bGluaz0iaHR0cDovL3d3dy53My5vcmcvMTk5OS94bGluayIgdmVyc2lvbj0iMS4xIiBpZD0iQ2FwYV8xIiB4PSIwcHgiIHk9IjBweCIgdmlld0JveD0iMCAwIDQ4Ni40IDQ4Ni40IiBzdHlsZT0iZW5hYmxlLWJhY2tncm91bmQ6bmV3IDAgMCA0ODYuNCA0ODYuNDsiIHhtbDpzcGFjZT0icHJlc2VydmUiIHdpZHRoPSIyNHB4IiBoZWlnaHQ9IjI0cHgiPgo8Zz4KCTxnPgoJCTxwYXRoIGQ9Ik00NDYsNzBIMzQ0LjhWNTMuNWMwLTI5LjUtMjQtNTMuNS01My41LTUzLjVoLTk2LjJjLTI5LjUsMC01My41LDI0LTUzLjUsNTMuNVY3MEg0MC40Yy03LjUsMC0xMy41LDYtMTMuNSwxMy41ICAgIFMzMi45LDk3LDQwLjQsOTdoMjQuNHYzMTcuMmMwLDM5LjgsMzIuNCw3Mi4yLDcyLjIsNzIuMmgyMTIuNGMzOS44LDAsNzIuMi0zMi40LDcyLjItNzIuMlY5N0g0NDZjNy41LDAsMTMuNS02LDEzLjUtMTMuNSAgICBTNDUzLjUsNzAsNDQ2LDcweiBNMTY4LjYsNTMuNWMwLTE0LjYsMTEuOS0yNi41LDI2LjUtMjYuNWg5Ni4yYzE0LjYsMCwyNi41LDExLjksMjYuNSwyNi41VjcwSDE2OC42VjUzLjV6IE0zOTQuNiw0MTQuMiAgICBjMCwyNC45LTIwLjMsNDUuMi00NS4yLDQ1LjJIMTM3Yy0yNC45LDAtNDUuMi0yMC4zLTQ1LjItNDUuMlY5N2gzMDIuOXYzMTcuMkgzOTQuNnoiIGZpbGw9IiMwMDAwMDAiLz4KCQk8cGF0aCBkPSJNMjQzLjIsNDExYzcuNSwwLDEzLjUtNiwxMy41LTEzLjVWMTU4LjljMC03LjUtNi0xMy41LTEzLjUtMTMuNXMtMTMuNSw2LTEzLjUsMTMuNXYyMzguNSAgICBDMjI5LjcsNDA0LjksMjM1LjcsNDExLDI0My4yLDQxMXoiIGZpbGw9IiMwMDAwMDAiLz4KCQk8cGF0aCBkPSJNMTU1LjEsMzk2LjFjNy41LDAsMTMuNS02LDEzLjUtMTMuNVYxNzMuN2MwLTcuNS02LTEzLjUtMTMuNS0xMy41cy0xMy41LDYtMTMuNSwxMy41djIwOC45ICAgIEMxNDEuNiwzOTAuMSwxNDcuNywzOTYuMSwxNTUuMSwzOTYuMXoiIGZpbGw9IiMwMDAwMDAiLz4KCQk8cGF0aCBkPSJNMzMxLjMsMzk2LjFjNy41LDAsMTMuNS02LDEzLjUtMTMuNVYxNzMuN2MwLTcuNS02LTEzLjUtMTMuNS0xMy41cy0xMy41LDYtMTMuNSwxMy41djIwOC45ICAgIEMzMTcuOCwzOTAuMSwzMjMuOCwzOTYuMSwzMzEuMywzOTYuMXoiIGZpbGw9IiMwMDAwMDAiLz4KCTwvZz4KPC9nPgo8Zz4KPC9nPgo8Zz4KPC9nPgo8Zz4KPC9nPgo8Zz4KPC9nPgo8Zz4KPC9nPgo8Zz4KPC9nPgo8Zz4KPC9nPgo8Zz4KPC9nPgo8Zz4KPC9nPgo8Zz4KPC9nPgo8Zz4KPC9nPgo8Zz4KPC9nPgo8Zz4KPC9nPgo8Zz4KPC9nPgo8Zz4KPC9nPgo8L3N2Zz4K" />
					</div>
				}

				<div id="jobImage"><img src={typeToPicture[job.type]} /></div>
				<div id="jobTitle" onClick={this.jobOfferClickHandler}>{job.title}</div>
				<div id="userId" onClick={this.userClickHandler}><Link to={`/user/${job.userId}`}>User: {job.userId}</Link></div>
				<div id="jobDescription">{job.phoneNumber}</div>
				{this.state.showModal &&
					<Modal
						job={job}
						showModal={this.state.showModal}
						closeModal={this.jobOfferCloseHandler}
						saveCallback={this.props.saveCallback}
					/>
				}
			</div>
		);
	}
}
