import React, {Component} from 'react';
import {apiUrl} from '../../constants';
import axios from 'axios';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

class PostLoader extends Component {
	actionUrl = "/jobOffer";

	constructor(props){
		super(props);

		this.state = {
			data: [],
			isLoading: false
		};
	}

	componentDidMount(){
		axios.get(this.actionUrl)
		.then((response)=>console.log(response))
		.catch((error)=>console.log("An error has occurred while trying to get: " + apiUrl + this.actionUrl + "\nThe error is:", error))
	}

	render() {
		if(isLoading){
			return <FontAwesomeIcon icon="spinner" spin />
		}

		var mapData = data.map((item)=>{
			return <Post />
		});

		return (
			<div>
				{mapData}
			</div>
		);
	}
}

export default PostLoader;
