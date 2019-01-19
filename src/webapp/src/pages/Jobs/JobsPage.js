import React, { Component } from 'react';
import "./JobsPage.css"
import JobsSubContainer from "./JobsSubContainer/JobsSubContainer";
import { JobRepository } from "../../repository/JobRepository";
import Navigator from "../../components/Navigator/Navigator";
import axios from 'axios';

export default class JobsPage extends Component {
	constructor(props) {
		super(props);
		this.state = {
			jobs: null,
			shownJobs: null,
			searchValue: "",
			userName: "TestUserName"
		}
	}

	componentDidMount() {
		axios.get(
			axios.defaults.baseURL + 'job-offers'
		).then(results => {
			this.setState({
				jobs: results.data.list,
				shownJobs: results.data.list
			});
		});
	}

	//TODO link to back-end
	filterJobs = (searchValue) => {
		searchValue = searchValue.toLowerCase();
		this.setState({
			shownJobs: this.state.jobs.filter(job => job.title.toLowerCase().includes(searchValue)),
			searchValue
		});
	};

	//TODO link to back-end
	deleteCallback = (jobId) => {
		const jobs = this.state.jobs;
		this.setState({
			jobs: jobs.filter(job => job.id !== jobId)
		})
	};

	//TODO link to back-end
	saveCallback = (newJob) => {
		const jobs = this.state.jobs;
		const index = jobs.findIndex(job => job.id === newJob.id);
		jobs.splice(index, 1, newJob);
		this.setState({
			jobs: jobs
		});
		JobRepository.update(newJob);
	};

	render() {
		if (!this.state.shownJobs)
			return null;
		return (
			<div id="mainPage">
				<Navigator searchCallback={this.filterJobs} />
				<div id="jobsContainer">
					<JobsSubContainer
						jobs={this.state.shownJobs}
						deleteCallback={this.deleteCallback}
						saveCallback={this.saveCallback}
					/>
				</div>
			</div>
		);
	}
}
