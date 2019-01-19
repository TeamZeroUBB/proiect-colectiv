import React, { Component } from 'react';
import "./JobsPage.css"
import JobsSubContainer from "./JobsSubContainer/JobsSubContainer";
import {Button, FormControl, FormGroup, MenuItem, Nav, Navbar, NavDropdown, NavItem} from "react-bootstrap";
import {Link} from "react-router-dom";
import {JobRepository} from "../../repository/JobRepository";

import axios from 'axios';
export default class JobsPage extends Component {
    constructor(props) {
        super(props);
        this.state = {
            jobs: null,
            searchValue: "",
            userName: "TestUserName"
        }

        this.getJobs();
    }

    getJobs() {
        axios.get(
            axios.defaults.baseURL + 'job-offers'
        ).then(results => {
          this.setState( { jobs: results.data.list });
        });
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
    filterJobs = (e) => {
        const searchValue = e.target.value;
        this.setState({
            jobs: this.state.jobs.filter(job => job.title.includes(searchValue)),
            searchValue
        });
    };
//TODO link to back-end
    deleteCallback = (jobId) => {
        const jobs = this.state.jobs;
        this.setState({
            jobs: jobs.filter(job => job.id!==jobId)
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

    createSubContainers = () => {
        const numberOfContainer = Math.ceil(this.state.jobs.length/4);
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
        return (
            <div id="mainPage">
                <Navbar inverse>
                    <Navbar.Header>
                        <Navbar.Brand>
                            <Link to="/jobs">Team Zero Jobs</Link>
                        </Navbar.Brand>
                        <Navbar.Toggle />
                    </Navbar.Header>
                    <Navbar.Collapse>
                        <Navbar.Form pullLeft>
                            <FormGroup id="searchBar">
                                <FormControl value={this.state.searchValue} onChange={this.filterJobs} type="text" placeholder="Search" />
                            </FormGroup>{' '}
                        </Navbar.Form>
                        <Nav pullRight>
                            <NavItem eventKey={1} href="#">
                                Welcome, {this.state.userName}
                            </NavItem>
                        </Nav>
                    </Navbar.Collapse>
                </Navbar>
                <div id="jobsContainer">
                    {this.createSubContainers()}
                </div>
            </div>
        );
    }
}
