import React, { Component } from 'react';
import { BrowserRouter as Router, Route, Redirect } from 'react-router-dom';
import './App.css';

import FrontPage from './pages/Front Page/FrontPage';
import LoginPage from './pages/Login Page/LoginPage';

import axios from 'axios';
import { apiUrl } from './constants';
import RegisterSuccess from './pages/Login Page/SubLogins/RegisterSuccess/RegisterSuccess';
import JobsPage from "./pages/Jobs/JobsPage";
import { withNamespaces } from 'react-i18next';
import UserDetailsPage from "./pages/User Page/UserDetailsPage/UserDetailsPage";
import UserDeletePage from "./pages/User Page/UserDeletePage/UserDeletePage";

//base url for ajax calls
axios.defaults.baseURL = apiUrl;
// axios.defaults.headers.post['Content-Type'] = 'application/json;charset=utf-8';
//allow cross-origin calls
axios.defaults.headers.post['crossDomain'] = true;

function App({ t }) {
    return (
        <Router>
            <div className="App">
                <Route exact path="/" render={() => <Redirect to="/jobs" />} />
                <Route path="/login" component={LoginPage} />
                <Route path="/signupsuccess" component={RegisterSuccess} />
                <Route path="/jobs" component={JobsPage} />
                <Route path="/user/:userId" component={UserDetailsPage} />
                <Route path="/delete-user" component={UserDeletePage} />
            </div>
        </Router>
    );
}

export default withNamespaces()(App);
