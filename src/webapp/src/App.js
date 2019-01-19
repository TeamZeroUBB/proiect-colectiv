import React, { Component } from 'react';
import { BrowserRouter as Router , Route} from 'react-router-dom';
import './App.css';

import FrontPage from './pages/Front Page/FrontPage';
import LoginPage from './pages/Login Page/LoginPage';

import axios from 'axios';
import { apiUrl } from './constants';
import RegisterSuccess from './pages/Login Page/SubLogins/RegisterSuccess/RegisterSuccess';
import JobsPage from "./pages/Jobs/JobsPage";
import { withNamespaces } from 'react-i18next';

//base url for ajax calls
axios.defaults.baseURL = apiUrl;
// axios.defaults.headers.post['Content-Type'] = 'application/json;charset=utf-8';
//allow cross-origin calls
axios.defaults.headers.post['crossDomain'] = true;

function App ({ t }) {
    return (
        <Router>
            <div className="App">
                <Route exact path="/" component={FrontPage} />
                <Route path="/login" component={LoginPage} />
                <Route path="/signupsuccess" component={RegisterSuccess} />
                <Route path="/jobs" component={JobsPage}/>
            </div>
        </Router>
    );
}

export default withNamespaces()(App);
