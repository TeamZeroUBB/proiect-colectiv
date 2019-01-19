import React, { Component } from 'react';
import {FormControl, FormGroup, Glyphicon, InputGroup} from "react-bootstrap";
import './SearchBar.css'
import i18n from '../../i18n'
export default class SearchBar extends Component {
    constructor(props, context) {
        super(props, context);

        this.handleChange = this.handleChange.bind(this);

        this.state = {
            value: ''
        };
    }

    getValidationState() {
        const length = this.state.value.length;
        if (length > 10) return 'success';
        else if (length > 5) return 'warning';
        else if (length > 0) return 'error';
        return null;
    }

    handleChange = (e) => {
        this.props.searchCallback(e.target.value);
        this.setState({ value: e.target.value });
    }

    render() {
        return (
            <form >
                <FormGroup id="searchBar">
                    <InputGroup>
                        <FormControl
                            type="text"
                            placeholder={i18n.t('searchPlaceholder')}
                            onChange={this.handleChange}
                        />
                        <InputGroup.Addon>
                            <Glyphicon glyph="glyphicon glyphicon-search" />
                        </InputGroup.Addon>
                    </InputGroup>
                </FormGroup>
            </form>
        );
    }
}

