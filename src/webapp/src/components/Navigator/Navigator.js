import React, { Component } from 'react';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faUser, faPlus, faUserMinus } from '@fortawesome/free-solid-svg-icons';
import {Link} from 'react-router-dom';
import './Navigator.css';
import ReactFlagsSelect from "react-flags-select";
import 'react-flags-select/css/react-flags-select.css';
import i18n from "../../i18n";
import SearchBar from "../SearchBar/SearchBar";

class Navigator extends Component {

    onSelectFlag = (countryCode) => {
        window.localStorage.setItem('countryCode', countryCode);
        i18n.changeLanguage(countryCode, (err, t) => {
            if (err) return
        });
    };

    handleAddButtonClick = () => {
        this.props.addClickedCallback && this.props.addClickedCallback();
    };

    render() {
        return (
            <div className="navi">
                <div className="navi-left">
                    <a href='/jobs'>
                        <div className="navi-logo" />
                    </a>
                    {this.props.searchCallback && <SearchBar searchCallback={this.props.searchCallback}/>}
                </div>

                <div className="navi-right">
                    <Link to="/delete-user" className="navi-button navi-addpost">
                        <FontAwesomeIcon className="fa-user-minus" icon={faUserMinus} />
                        Delete user
                    </Link>
                    <Link to="/login" className="navi-button navi-account">
                        <FontAwesomeIcon className="fa-fw" icon={faUser} />
						{i18n.t('myAccountLabel')}
                    </Link>
                    <a onClick={this.handleAddButtonClick} className="navi-button navi-addpost">
                        <FontAwesomeIcon className="fa-fw" icon={faPlus} />
                        {i18n.t('newOfferLabel')}
                    </a>

					<div className="menu-flags">
                        <ReactFlagsSelect
                            countries={["US", "RO", "DE"]}
                            customLabels={{"US": "English","DE": "German","RO": "Romanian"}}
                            defaultCountry={window.localStorage.getItem('countryCode') || "US"}
                            placeholder="Select Language"
                            onSelect={this.onSelectFlag}/>
					</div>
                </div>
            </div>
        );
    }
}

export default Navigator;
