import React, { Component } from 'react';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faUser, faPlus } from '@fortawesome/free-solid-svg-icons';
import {Link} from 'react-router-dom';
import './Navigator.css';
import ReactFlagsSelect from "react-flags-select";
import 'react-flags-select/css/react-flags-select.css';
import i18n from "../../i18n";

class Navigator extends Component {

    onSelectFlag = (countryCode) => {
        i18n.changeLanguage(countryCode, (err, t) => {
            if (err) return console.log('something went wrong loading', err);
        });
    };

    render() {
        return (
            <div className="navi">
                <div className="navi-left">
                    <div className="navi-logo" />
                </div>

                <div className="navi-right">
                    <Link to="/login" className="navi-button navi-account">
                        <FontAwesomeIcon className="fa-fw" icon={faUser} />
						{i18n.t('myAccountLabel')}
                    </Link>
                    <Link to="/login" className="navi-button navi-addpost">
                        <FontAwesomeIcon className="fa-fw" icon={faPlus} />
                        Create a new offer
                    </Link>

					<div class="menu-flags">
                        <ReactFlagsSelect
                            countries={["US", "RO", "DE"]}
                            customLabels={{"US": "English","DE": "German","RO": "Romanian"}}
                            defaultCountry="US"
                            placeholder="Select Language"
                            onSelect={this.onSelectFlag}/>
					</div>
                </div>
            </div>
        );
    }
}

export default Navigator;
