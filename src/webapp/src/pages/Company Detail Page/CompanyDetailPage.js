import React, { Component } from 'react';
import axios from 'axios';
import JobsSubContainer from "../Jobs/JobsSubContainer/JobsSubContainer";
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faEnvelope, faPhone } from '@fortawesome/free-solid-svg-icons';


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
            if(i < this.state.company.postedJobOffers.length) {
                jobs.push(this.state.company.postedJobOffers[i]);
            }
        }
        return jobs;
    }


    createSubContainers = () => {
        const numberOfContainer = Math.ceil(this.state.company.postedJobOffers.length/4);
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
        <div>
          <div className="card" style={{width: 50 + '%'}} >
            <div className="card-body">
              <h5 className="card-title">{company.name}</h5>
              <h6 className="card-subtitle mb-2 text-muted"><FontAwesomeIcon icon={faEnvelope}/>{company.email}</h6>
              <h6 className="card-subtitle mb-2 text-muted"><FontAwesomeIcon icon={faPhone}/>{company.phoneNumber}</h6>
              <p className="card-text">{company.description}</p>

            </div>
          </div>
          <div id="jobsContainer">
            {this.createSubContainers()}
            </div>
        </div>
        );
    }
}