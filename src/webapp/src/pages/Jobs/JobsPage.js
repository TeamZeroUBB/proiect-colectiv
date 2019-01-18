import React, { Component } from 'react';
import "./JobsPage.css"
import JobsSubContainer from "./JobsSubContainer/JobsSubContainer";
import {Button, FormControl, FormGroup, MenuItem, Nav, Navbar, NavDropdown, NavItem} from "react-bootstrap";
import {Link} from "react-router-dom";
import {JobRepository} from "../../repository/JobRepository";
import Navigator from "../../components/Navigator/Navigator";
import i18n from '../../i18n'
import {Trans} from "react-i18next";
const defaultJobs = [
    {
        id: 1,
        title: "Title1",
        description: "Description1 asgsdf 32rf asdg sdf cargs fhnsdsfvsdhfbs3gdsfg re gsdf w fgfdgs fdb dfg  asgasg adsg sfdg asdg vsfdb trjghnthdfgh bfgsdh bdfsaf afd gcvsdfgxc asdfvgfnc sgsda sdbdgshrtear fnhdrse fdv ser xnf xdgxf gnref  gdsfv a fdsaxc gsfbvcv",
        image: "https://upload.wikimedia.org/wikipedia/commons/6/6f/HP_logo_630x630.png",
        phoneNumber: '0759021256',
        email: 'andrasiladislau@gmail.com',
        type: 'development',
        address: 'Cluj-Napoca Buftea 7'
    },
    {
        id: 2,
        title: "Title2",
        description: "Description2",
        image: "https://upload.wikimedia.org/wikipedia/commons/6/6f/HP_logo_630x630.png",
        phoneNumber: '0759021256',
    },
    {
        id: 3,
        title: "Title3",
        description: "Description3",
        image: "https://upload.wikimedia.org/wikipedia/commons/6/6f/HP_logo_630x630.png",
        phoneNumber: '0759021256',
    },
    {
        id: 4,
        title: "Title4",
        description: "Description4",
        image: "https://upload.wikimedia.org/wikipedia/commons/6/6f/HP_logo_630x630.png",
        phoneNumber: '0759021256',
    },
    {
        id:5,
        title: "Title5",
        description: "Description5",
        image: "https://upload.wikimedia.org/wikipedia/commons/6/6f/HP_logo_630x630.png",
        phoneNumber: '0759021256',
    },
    {
        id:6,
        title: "Title6",
        description: "Description6",
        image: "https://upload.wikimedia.org/wikipedia/commons/6/6f/HP_logo_630x630.png",
        phoneNumber: '0759021256',
    }
];
export default class JobsPage extends Component {
    constructor(props) {
        super(props);
        this.state = {
            jobs: defaultJobs,
            searchValue: "",
            userName: "TestUserName"
        };
        // this.getAllJobsFromServer();
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
                <Navigator />
                <div id="jobsContainer">
                    {this.createSubContainers()}
                </div>
            </div>
        );
    }
}