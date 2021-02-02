import axios from 'axios'

class ProjectCheckpointService {
    retrieveProjectDetails() {
        //Hardcoding it to 1, considering we are tracking single project.
        return axios.get('http://localhost:8080/project/1/');
    }
}

export default new ProjectCheckpointService()

