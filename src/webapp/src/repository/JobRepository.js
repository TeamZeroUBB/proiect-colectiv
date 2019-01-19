import axios from 'axios';

const ip = '25.66.34.68:8080';

export class JobRepository {
    static get(){
        return axios
            .get(`http://${ip}/job-offers`)
            .then(response => {
                console.log(response);
                return response;
            })
            .catch(error => console.log(`Following error occurred when fetching: ${error}`));
    }

    static add(job) {
        return axios
            .post(`http://${ip}/job-offer-service/create`, job)
            .then(response => {
                console.log(response);
                return response;
            })
            .catch(error => console.log(`Following error occurred when adding: ${error}`));
    }

    static update(job) {
        return axios
            .put(`http://${ip}/job-offer-service/update`, job)
            .then(response => {
                console.log(response);
                return response;
            })
            .catch(error => console.log(`Following error occurred when updating: ${error}`));
    }

    static delete(jobId) {
        return axios
            .delete(`http://${ip}/job-offer-service/delete/${jobId}`)
            .then(response => {
                console.log(response);
                return response;
            })
            .catch(error => console.log(`Following error occurred when deleting: ${error}`));
    }

    static filter(title){
        return axios
            .get(`http://${ip}/job-offers/filter?title=${title}`)
            .then(response => {
                console.log(response);
                return response;
            })
            .catch(error => console.log(`Following error occurred when fetching: ${error}`));
    }
}