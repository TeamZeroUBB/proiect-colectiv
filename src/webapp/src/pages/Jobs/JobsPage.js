import React, { Component } from 'react';
import "./JobsPage.css"
import JobsSubContainer from "./JobsSubContainer/JobsSubContainer";
import {Button, FormControl, FormGroup, MenuItem, Nav, Navbar, NavDropdown, NavItem} from "react-bootstrap";
import {Link} from "react-router-dom";
import {JobRepository} from "../../repository/JobRepository";
import Navigator from "../../components/Navigator/Navigator";
import axios from 'axios';
export default class JobsPage extends Component {
    constructor(props) {
        super(props);
        this.state = {
            jobs: this.getAllJobsFromServer(),
            searchValue: "",
            userName: "TestUserName",
        }
    }

    getAllJobsFromServer = () => {
        JobRepository.get().then(result => {
            this.setState({
                jobs: result && result.data && result.data.list
            });
            console.log(this.state.jobs);
        })
    };

    getJobsForContainer(index) {
        const jobs=[];
        for(let i = index * 4 ; i < (index + 1) * 4; i++){
            if(i < this.state.jobs.length) {
                jobs.push(this.state.jobs[i]);
            }
        }

        return jobs;
    }
    //TODO link to back-end
    filterJobs = (searchValue) => {
        this.setState({
            jobs: this.state.jobs.filter(job => job.title.includes(searchValue)),
            searchValue
        });
    };
    deleteCallback = (jobId) => {
        const jobs = this.state.jobs;
        console.log({jobId});
        this.setState({
            jobs: jobs.filter(job => job.jobOfferId!==jobId)
        });
        JobRepository.delete(jobId);
    };

    saveCallback = (newJob) => {
        const jobs = this.state.jobs;
        const index = jobs.findIndex(job => job.id === newJob.id);
        jobs.splice(index, 1, newJob);
        this.setState({
            jobs: jobs
        });
        JobRepository.update(newJob);
    };

    createSubContainers = () => {
        const numberOfContainer = Math.ceil(this.state.jobs && this.state.jobs.length/4);
        const subContainers = [];
        for(let i = 0; i < numberOfContainer; i++ ) {
            subContainers.push(<JobsSubContainer
                key={i}
                jobs={this.getJobsForContainer(i)}
                deleteCallback={this.deleteCallback}
                saveCallback={this.saveCallback}
            />);
        }
        return subContainers;
    };

    render() {
        // const user = this.props.session.user;
        const user = {
            userId: 2,
            isAdmin: true,
        };

        return (
            <div id="mainPage">
                <Navigator searchCallback={this.filterJobs}/>
                <div id="jobsContainer">
                    {this.createSubContainers()}
                </div>
            </div>
        );
    }
}
