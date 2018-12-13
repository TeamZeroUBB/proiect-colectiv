import React, { Component } from 'react';
import "./JobsPage.css"
import JobsSubContainer from "./JobsSubContainer/JobsSubContainer";
import {Button, FormControl, FormGroup, MenuItem, Nav, Navbar, NavDropdown, NavItem} from "react-bootstrap";
import {Link} from "react-router-dom";
const defaultJobs = [
    {
        id: 1,
        title: "Title1",
        description: "Description1",
        image: "https://upload.wikimedia.org/wikipedia/commons/6/6f/HP_logo_630x630.png"
    },
    {
        id: 2,
        title: "Title2",
        description: "Description2",
        image: "https://upload.wikimedia.org/wikipedia/commons/6/6f/HP_logo_630x630.png"
    },
    {
        id: 3,
        title: "Title3",
        description: "Description3",
        image: "https://upload.wikimedia.org/wikipedia/commons/6/6f/HP_logo_630x630.png"
    },
    {
        id: 4,
        title: "Title4",
        description: "Description4",
        image: "https://upload.wikimedia.org/wikipedia/commons/6/6f/HP_logo_630x630.png"
    },
    {
        id:5,
        title: "Title5",
        description: "Description5",
        image: "https://upload.wikimedia.org/wikipedia/commons/6/6f/HP_logo_630x630.png"
    },
    {
        id:6,
        title: "Title6",
        description: "Description6",
        image: "https://upload.wikimedia.org/wikipedia/commons/6/6f/HP_logo_630x630.png"
    }
];
export default class JobsPage extends Component {
    constructor(props) {
        super(props);
        this.state = {
            jobs: defaultJobs,
            searchValue: "",
            userName: "TestUserName"
        }
    }

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
            jobs: defaultJobs.filter(job => job.title.includes(searchValue)),
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
        })
    };

    createSubContainers = () => {
        const numberOfContainer = Math.ceil(this.state.jobs.length/4);
        const subContainers = [];
        for(let i = 0; i < numberOfContainer; i++ ) {
            subContainers.push(<JobsSubContainer
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