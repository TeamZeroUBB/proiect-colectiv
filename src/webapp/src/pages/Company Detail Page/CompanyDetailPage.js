import React, { Component } from 'react';
import axios from 'axios';
import JobsSubContainer from "../Jobs/JobsSubContainer/JobsSubContainer";


export default class CompanyDetailPage extends Component {
    constructor(props) {
        super(props);
        this.state = {
            company: null
        }
    }

    componentDidMount() {
      axios.get(
          axios.defaults.baseURL + `company/${this.props.match.params.companyId}`
      ).then(results => {
          this.setState({ company: results.data });
      });
    }

    getJobsForContainer(index) {
        const jobs=[];
        for(let i = index * 4 ; i < (index + 1) * 4; i++){
            if(i < this.state.company.jobOffers.length) {
                jobs.push(this.state.company.jobOffers[i]);
            }
        }
        return jobs;
    }


    createSubContainers = () => {
        const numberOfContainer = Math.ceil(this.state.company.jobOffers.length/4);
        const subContainers = [];
        for(let i = 0; i < numberOfContainer; i++ ) {
            subContainers.push(<JobsSubContainer
                key={i}
                jobs={this.getJobsForContainer(i)}
            />);
        }
        return subContainers;
    };


    render() {
        const company = this.state.company;

        if (!company) {
          return null;
        }

        return (
          <div className="card md-6 main-card">
            <div className="card-body">
              <h5 className="card-title">{company.name}</h5>
              <h6 className="card-subtitle mb-2 text-muted">{company.description}</h6>
              <h6 className="card-subtitle mb-2 text-muted">{company.email}</h6>
            </div>

            <div id="jobsContainer">
            {this.createSubContainers()}
            </div>
          </div>
        );
    }
}