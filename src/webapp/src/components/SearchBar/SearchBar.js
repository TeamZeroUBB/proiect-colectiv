import React, { Component } from 'react';
import {FormControl, FormGroup, Glyphicon, InputGroup} from "react-bootstrap";
import './SearchBar.css'
import i18n from '../../i18n'
export default class SearchBar extends Component {
    constructor(props, context) {
        super(props, context);
        this.state = {
            value: ''
        };
    }

    handleEnter = (event) => {
        if(event.charCode===13){
            event.preventDefault();
            this.props.searchCallback(this.state.value);
        }
    };

    handleSearchClick = () => {
        this.props.searchCallback(this.state.value);
    };

    handleChange = (e) => {
        this.setState({
            value: e.target.value
        })
    };

    render() {
        return (
            <FormGroup id="searchBar">
                <InputGroup>
                    <FormControl
                        type="text"
                        placeholder={i18n.t('searchPlaceholder')}
                        onKeyPress={this.handleEnter}
                        onChange={this.handleChange}
                    />

                    <InputGroup.Addon>
                        <div onClick={this.handleSearchClick}>
                            <Glyphicon glyph="glyphicon glyphicon-search" />
                        </div>
                    </InputGroup.Addon>
                </InputGroup>
            </FormGroup>
        );
    }
}

