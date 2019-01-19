import axios from 'axios';
import {apiUrl} from "../constants";


export class JobRepository {
    static get(){
        return axios
            .get(`${apiUrl}job-offers`)
            .then(response => {
                console.log(response);
                return response;
            })
            .catch(error => console.log(`Following error occurred when fetching: ${error}`));
    }

    static add(job) {
        return axios
            .post(`${apiUrl}job-offer-service/create`, job)
            .then(response => {
                console.log(response);
                return response;
            })
            .catch(error => console.log(`Following error occurred when adding: ${error}`));
    }

    static update(job) {
        return axios
            .put(`${apiUrl}job-offer-service/update`, job)
            .then(response => {
                console.log(response);
                return response;
            })
            .catch(error => console.log(`Following error occurred when updating: ${error}`));
    }

    static delete(jobId) {
        return axios
            .delete(`${apiUrl}job-offer-service/delete/${jobId}`)
            .then(response => {
                console.log(response);
                return response;
            })
            .catch(error => console.log(`Following error occurred when deleting: ${error}`));
    }

    static filter(title){
        return axios
            .get(`${apiUrl}job-offers/filter?title=${title}`)
            .then(response => {
                console.log(response);
                return response;
            })
            .catch(error => console.log(`Following error occurred when fetching: ${error}`));
    }
}