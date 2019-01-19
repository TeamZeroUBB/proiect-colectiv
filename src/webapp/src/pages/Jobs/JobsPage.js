import React, { Component } from 'react';
import "./JobsPage.css"
import JobsSubContainer from "./JobsSubContainer/JobsSubContainer";
import {JobRepository} from "../../repository/JobRepository";
import Navigator from "../../components/Navigator/Navigator";
import Modal from "../../components/Modal/Modal";

export default class JobsPage extends Component {
    constructor(props) {
        super(props);
        this.state = {
            jobs: this.getAllJobsFromServer(),
            userName: "TestUserName",
            showModal: false
        }
    }

    getAllJobsFromServer = () => {
        JobRepository.get().then(result => {
            this.setState({
                jobs: result && result.data && result.data.list
            });
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

    filterJobs = (searchValue) => {
        JobRepository
            .filter(searchValue)
            .then((result) => {
                this.setState({
                    jobs: result && result.data && result.data.list
                });
            });

    };
    
    deleteCallback = (jobId) => {
        JobRepository
            .delete(jobId)
            .then(response => {
                const jobs = this.state.jobs;
                this.setState({
                    jobs: jobs.filter(job => job.jobOfferId!==jobId)
                });
            })
    };
    
    saveCallback = (newJob) => {
        JobRepository
            .update(newJob)
            .then(response => {
                const jobs = this.state.jobs;
                const index = jobs.findIndex(job => job.jobOfferId === newJob.jobOfferId);
                jobs.splice(index, 1, newJob);
                this.setState({
                    jobs: jobs
                });
            })
        ;
    };

    //TODO update userId to current logged in userId
    createCallback = (job) => {
        JobRepository
            .add(job)
            .then(response=>{
                this.setState({
                    jobs: [...this.state.jobs, Object.assign({}, job, { userId: 1})]
                })
            });
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

    addClickedCallback = () => {
        this.setState({
            showModal: true
        })
    };

    jobOfferCloseHandler = () => {
        this.setState({
            showModal: false
        })
    };


    render() {
        return (
            <div id="mainPage">
                <Navigator
                    searchCallback={this.filterJobs}
                    addClickedCallback={this.addClickedCallback}
                />
                {this.state.showModal &&
                <Modal
                    job={{}}
                    showModal={this.state.showModal}
                    closeModal={this.jobOfferCloseHandler}
                    isEditMode={true}
                    saveCallback={this.createCallback}
                />
                }
                <div id="jobsContainer">
                    {this.createSubContainers()}
                </div>
            </div>
        );
    }
}
