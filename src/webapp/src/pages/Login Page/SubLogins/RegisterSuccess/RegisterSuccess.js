import React from 'react';
import {Link} from 'react-router-dom';
import './RegisterSuccess.css'
import i18n from '../../../../i18n';
import Navigator from '../../../../components/Navigator/Navigator';

const RegisterSuccess = () => {
    return(
        <>
        <Navigator />
        <div className="register-success">
            <div className="register-success-title">{i18n.t("regsucc_msg1")}</div>
            <div className="register-success-body">{i18n.t("regsucc_msg2")}</div>
            <div className="register-success-body">{i18n.t("regsucc_msg3")}</div>
            <div className="register-success-back">{i18n.t("regsucc_msg4")} <Link to="/login">{i18n.t("regsucc_msg5")}</Link></div>
        </div>
        </>
    );
};

export default RegisterSuccess;
