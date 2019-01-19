import React, { Component } from 'react';
import "./JobsSubContainer.css"
import JobsComponent from "../../../components/Jobs/JobsComponent";
export default class JobsSubContainer extends Component {
    constructor(props) {
        super(props);
    }

    getJobsToRender = () => {
        return this.props.jobs.map((job, index, jobs) => {
            return (<JobsComponent
                key={index}
                job={job}
                deleteCallback={this.props.deleteCallback}
                saveCallback={this.props.saveCallback}
            />);
        });
    };

    render() {
        return (
            <div id="jobsSubContainer">
                {this.getJobsToRender()}
            </div>
        );
    }
}
