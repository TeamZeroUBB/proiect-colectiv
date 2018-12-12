import React from 'react';
import {Link} from 'react-router-dom';
import './RegisterSuccess.css'

const RegisterSuccess = () => {
    return(
        <div className="register-success">
            <div className="register-success-title">Sign Up Complete!</div>
            <div className="register-success-body">Welcome abroad</div>
            <div className="register-success-body">Thank you for taking time to register. We hope you'll enjoy our comunity!</div>
            <div className="register-success-back">To head back to the login page, click <Link to="/login">here</Link></div>
        </div>
    );
};

export default RegisterSuccess;
