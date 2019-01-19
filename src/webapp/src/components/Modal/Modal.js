import React, { Component } from 'react';
import * as ReactModal from 'react-modal';
import "./Modal.css";
import Input from "../common/Input/Input";
import {Button, ButtonToolbar, ControlLabel, DropdownButton, FormControl, FormGroup, MenuItem} from "react-bootstrap";
import {typeToPicture} from "../Jobs/JobsComponent";
import i18n from '../../i18n';



export default class Modal extends Component {
    menuItems = () => {
        return [
            <MenuItem eventKey="1">{i18n.t('technology')}</MenuItem>,
            <MenuItem eventKey="2">{i18n.t('law')}</MenuItem>,
            <MenuItem eventKey="3">{i18n.t('economy')}</MenuItem>,
            <MenuItem eventKey="4">{i18n.t('education')}</MenuItem>,
            <MenuItem eventKey="5">{i18n.t('artist')}</MenuItem>,
        ]};
    constructor (props) {
        super(props);
        const job = this.props.job;
        const isEditMode = this.props.isEditMode;
        this.state = {
            isEditMode: isEditMode || false,
            jobImage: typeToPicture[job.type] || '',
            jobTitle: job.title || '',
            jobDescription: job.description || '',
            jobType: job.type || 'Select type...',
            jobAddress: job.address || '',
            jobPhoneNumber: job.phoneNumber || '',
            jobEmail: job.email || '',
            showSave: false
        };
        this.fieldPassed = {
            jobTitle: false,
            jobDescription: false,
            jobAddress: false,
            jobPhoneNumber: false,
            jobEmail: false
        }
    }

    handleCloseModal = () => {
        this.props.closeModal && this.props.closeModal();
    };

    handleEditClick = () => {
        this.setState({
            isEditMode: true
        })
    };

    handleSaveClick = () => {
        const saveCallback = this.props.saveCallback;
        this.setState({
            isEditMode: false
        });
        const oldJob = this.props.job;
        const newJob = {
            title: this.state.jobTitle,
            description: this.state.jobDescription,
            type: this.state.jobType,
            address: this.state.jobAddress,
            phoneNumber: this.state.jobPhoneNumber,
            email: this.state.jobEmail,
            image: this.state.jobImage
        };
        saveCallback(Object.assign({}, oldJob, newJob));
    };

    handleFieldInputChange = (e, fieldType) => {
        this.setState({
            [fieldType] : e.target.value
        })
    };

    getViewWithoutEditMode = (job) => {
        return (<div id="jobOfferModalContainer">
            <div id="jobDetailsModal">
                <div id="jobTitleModal">
                    <div id="jobTitle">{this.state.jobTitle}</div>
                    <div id="jobType">{this.menuItems()[this.state.jobType - 1].props.children}</div>
                </div>
                <div id="jobDescription">
                    {this.state.jobDescription}
                </div>
            </div>
            <div id="jobContactDetailsModal">
                <div id="jobImageModal">
                    <img src={this.state.jobImage}/>
                </div>
                <div id="jobContactDetailsSubContainer">
                    <div className="jobContact">{this.state.jobPhoneNumber}</div>
                    <div className="jobContact">{this.state.jobEmail}</div>
                    <div className="jobContact">{this.state.jobAddress}</div>
                    <div id="applyButton">
                        <Button bsStyle="primary">{i18n.t('applyLabel')}</Button>
                    </div>
                </div>
            </div>
        </div>)
    };

    selectedJobType = (key, e) => {
        this.setState({
            jobType: key,
            jobImage: typeToPicture[key]
        })
    };

    getValidationState = (fieldType) => {
        const length = this.state[fieldType].length;
        let allPassed = false;
        if (length > 5) {
            this.fieldPassed[fieldType] = true;
            allPassed = Object.keys(this.fieldPassed).every(val => this.fieldPassed[val]===true);
            if(allPassed && !this.state.showSave) {
                this.setState({
                    showSave: true
                })
            }
            return 'success';
        }
        else if (length > 0) {
            this.fieldPassed[fieldType] = false;
            if(!allPassed && this.state.showSave) {
                this.setState({
                    showSave: false
                })
            }
            return 'error';
        }
        return null;
    }

    getViewEditMode = (job) => {
        return (<div id="jobOfferModalContainer">
            <div id="jobDetailsModal">
                <div id="jobTitleModal">
                    <div id="jobTitle">
                        <form>
                            <FormGroup
                                controlId="formBasicText"
                                validationState={this.getValidationState('jobTitle')}
                            >
                                <FormControl
                                    type="text"
                                    value={this.state.jobTitle}
                                    placeholder={i18n.t('titlePlaceholderLabel')}
                                    onChange={(e) => this.handleFieldInputChange(e, 'jobTitle')}
                                />
                            </FormGroup>
                        </form>
                    </div>
                    <div id="jobType">
                        <ButtonToolbar>
                            <DropdownButton onSelect={this.selectedJobType} title={this.menuItems()[this.state.jobType - 1] && this.menuItems()[this.state.jobType - 1].props.children || i18n.t('typePlaceholderLabel')} id="dropdown-size-medium">
                                {this.menuItems()}
                            </DropdownButton>
                        </ButtonToolbar>
                    </div>
                </div>
                <div id="jobDescription">
                    <form>
                        <FormGroup
                            controlId="formBasicText"
                            validationState={this.getValidationState('jobDescription')}
                        >
                            <FormControl
                                type="text"
                                componentClass="textarea"
                                value={this.state.jobDescription}
                                placeholder={i18n.t('descriptionPlaceholderLabel')}
                                onChange={(e) => this.handleFieldInputChange(e, 'jobDescription')}
                            />
                        </FormGroup>
                    </form>
                </div>
            </div>
            <div id="jobContactDetailsModal">
                <div id="jobImageModal">
                    <img src={this.state.jobImage}/>
                </div>
                <div id="jobContactDetailsSubContainer">
                    <div className="jobContact">
                        <form>
                            <FormGroup
                                controlId="formBasicText"
                                validationState={this.getValidationState('jobPhoneNumber')}
                            >
                                <FormControl
                                    type="text"
                                    value={this.state.jobPhoneNumber}
                                    placeholder={i18n.t('phoneNumberPlaceholderLabel')}
                                    onChange={(e) => this.handleFieldInputChange(e, 'jobPhoneNumber')}
                                />
                            </FormGroup>
                        </form>
                    </div>
                    <div className="jobContact">
                        <form>
                            <FormGroup
                                controlId="formBasicText"
                                validationState={this.getValidationState('jobEmail')}
                            >
                                <FormControl
                                    type="text"
                                    value={this.state.jobEmail}
                                    placeholder={i18n.t('emailPlaceholderLabel')}
                                    onChange={(e) => this.handleFieldInputChange(e, 'jobEmail')}
                                />
                            </FormGroup>
                        </form>
                    </div>
                    <div className="jobContact">
                        <form>
                            <FormGroup
                                controlId="formBasicText"
                                validationState={this.getValidationState('jobAddress')}
                            >
                                <FormControl
                                    required="required"
                                    type="text"
                                    value={this.state.jobAddress}
                                    placeholder={i18n.t('addressPlaceholderLabel')}
                                    onChange={(e) => this.handleFieldInputChange(e, 'jobAddress')}
                                />
                            </FormGroup>
                        </form>
                    </div>
                    <div id="applyButton">
                        <Button bsStyle="primary">{i18n.t('applyLabel')}</Button>
                    </div>
                </div>
            </div>
        </div>)
    };

    render () {
        const job = this.props.job;
        return (
            <div>
                <ReactModal
                    isOpen={this.props.showModal}
                    contentLabel="Minimal Modal Example"
                    // className="Modal"
                    // overlayClassName="Overlay"
                    onRequestClose={this.handleCloseModal}
                    shouldCloseOnOverlayClick={true}
                    style={{
                        overlay: {
                            // backgroundColor: 'papayawhip'
                        },
                        content: {
                            position: 'absolute',
                            top: '45px',
                            left: '20%',
                            right: '40px',
                            bottom: '40px',
                            border: '1px solid #ccc',
                            background: '#fff',
                            overflow: 'none',
                            WebkitOverflowScrolling: 'touch',
                            borderRadius: '4px',
                            outline: 'none',
                            padding: '0px',
                            width: '60%'
                        }
                    }}
                >
                    <div id="jobOfferHeader">
                        <div id="jobOfferEditIcon" className="jobOfferIcon">
                            {!this.state.isEditMode && <img onClick={this.handleEditClick} src="data:image/svg+xml;utf8;base64,PD94bWwgdmVyc2lvbj0iMS4wIiBlbmNvZGluZz0iaXNvLTg4NTktMSI/Pgo8IS0tIEdlbmVyYXRvcjogQWRvYmUgSWxsdXN0cmF0b3IgMTkuMC4wLCBTVkcgRXhwb3J0IFBsdWctSW4gLiBTVkcgVmVyc2lvbjogNi4wMCBCdWlsZCAwKSAgLS0+CjxzdmcgeG1sbnM9Imh0dHA6Ly93d3cudzMub3JnLzIwMDAvc3ZnIiB4bWxuczp4bGluaz0iaHR0cDovL3d3dy53My5vcmcvMTk5OS94bGluayIgdmVyc2lvbj0iMS4xIiBpZD0iQ2FwYV8xIiB4PSIwcHgiIHk9IjBweCIgdmlld0JveD0iMCAwIDU1LjI1IDU1LjI1IiBzdHlsZT0iZW5hYmxlLWJhY2tncm91bmQ6bmV3IDAgMCA1NS4yNSA1NS4yNTsiIHhtbDpzcGFjZT0icHJlc2VydmUiIHdpZHRoPSIyNHB4IiBoZWlnaHQ9IjI0cHgiPgo8cGF0aCBkPSJNNTIuNjE4LDIuNjMxYy0zLjUxLTMuNTA4LTkuMjE5LTMuNTA4LTEyLjcyOSwwTDMuODI3LDM4LjY5M0MzLjgxLDM4LjcxLDMuOCwzOC43MzEsMy43ODUsMzguNzQ5ICBjLTAuMDIxLDAuMDI0LTAuMDM5LDAuMDUtMC4wNTgsMC4wNzZjLTAuMDUzLDAuMDc0LTAuMDk0LDAuMTUzLTAuMTI1LDAuMjM5Yy0wLjAwOSwwLjAyNi0wLjAyMiwwLjA0OS0wLjAyOSwwLjA3NSAgYy0wLjAwMywwLjAxLTAuMDA5LDAuMDItMC4wMTIsMC4wM2wtMy41MzUsMTQuODVjLTAuMDE2LDAuMDY3LTAuMDIsMC4xMzUtMC4wMjIsMC4yMDJDMC4wMDQsNTQuMjM0LDAsNTQuMjQ2LDAsNTQuMjU5ICBjMC4wMDEsMC4xMTQsMC4wMjYsMC4yMjUsMC4wNjUsMC4zMzJjMC4wMDksMC4wMjUsMC4wMTksMC4wNDcsMC4wMywwLjA3MWMwLjA0OSwwLjEwNywwLjExLDAuMjEsMC4xOTYsMC4yOTYgIGMwLjA5NSwwLjA5NSwwLjIwNywwLjE2OCwwLjMyOCwwLjIxOGMwLjEyMSwwLjA1LDAuMjUsMC4wNzUsMC4zNzksMC4wNzVjMC4wNzcsMCwwLjE1NS0wLjAwOSwwLjIzMS0wLjAyN2wxNC44NS0zLjUzNSAgYzAuMDI3LTAuMDA2LDAuMDUxLTAuMDIxLDAuMDc3LTAuMDNjMC4wMzQtMC4wMTEsMC4wNjYtMC4wMjQsMC4wOTktMC4wMzljMC4wNzItMC4wMzMsMC4xMzktMC4wNzQsMC4yMDEtMC4xMjMgIGMwLjAyNC0wLjAxOSwwLjA0OS0wLjAzMywwLjA3Mi0wLjA1NGMwLjAwOC0wLjAwOCwwLjAxOC0wLjAxMiwwLjAyNi0wLjAybDM2LjA2My0zNi4wNjNDNTYuMTI3LDExLjg1LDU2LjEyNyw2LjE0LDUyLjYxOCwyLjYzMXogICBNNTEuMjA0LDQuMDQ1YzIuNDg4LDIuNDg5LDIuNyw2LjM5NywwLjY1LDkuMTM3bC05Ljc4Ny05Ljc4N0M0NC44MDgsMS4zNDUsNDguNzE2LDEuNTU3LDUxLjIwNCw0LjA0NXogTTQ2LjI1NCwxOC44OTVsLTkuOS05LjkgIGwxLjQxNC0xLjQxNGw5LjksOS45TDQ2LjI1NCwxOC44OTV6IE00Ljk2MSw1MC4yODhjLTAuMzkxLTAuMzkxLTEuMDIzLTAuMzkxLTEuNDE0LDBMMi43OSw1MS4wNDVsMi41NTQtMTAuNzI4bDQuNDIyLTAuNDkxICBsLTAuNTY5LDUuMTIyYy0wLjAwNCwwLjAzOCwwLjAxLDAuMDczLDAuMDEsMC4xMWMwLDAuMDM4LTAuMDE0LDAuMDcyLTAuMDEsMC4xMWMwLjAwNCwwLjAzMywwLjAyMSwwLjA2LDAuMDI4LDAuMDkyICBjMC4wMTIsMC4wNTgsMC4wMjksMC4xMTEsMC4wNSwwLjE2NWMwLjAyNiwwLjA2NSwwLjA1NywwLjEyNCwwLjA5NSwwLjE4MWMwLjAzMSwwLjA0NiwwLjA2MiwwLjA4NywwLjEsMC4xMjcgIGMwLjA0OCwwLjA1MSwwLjEsMC4wOTQsMC4xNTcsMC4xMzRjMC4wNDUsMC4wMzEsMC4wODgsMC4wNiwwLjEzOCwwLjA4NEM5LjgzMSw0NS45ODIsOS45LDQ2LDkuOTcyLDQ2LjAxNyAgYzAuMDM4LDAuMDA5LDAuMDY5LDAuMDMsMC4xMDgsMC4wMzVjMC4wMzYsMC4wMDQsMC4wNzIsMC4wMDYsMC4xMDksMC4wMDZjMCwwLDAuMDAxLDAsMC4wMDEsMGMwLDAsMC4wMDEsMCwwLjAwMSwwaDAuMDAxICBjMCwwLDAuMDAxLDAsMC4wMDEsMGMwLjAzNiwwLDAuMDczLTAuMDAyLDAuMTA5LTAuMDA2bDUuMTIyLTAuNTY5bC0wLjQ5MSw0LjQyMkw0LjIwNCw1Mi40NTlsMC43NTctMC43NTcgIEM1LjM1MSw1MS4zMTIsNS4zNTEsNTAuNjc5LDQuOTYxLDUwLjI4OHogTTE3LjUxMSw0NC44MDlMMzkuODg5LDIyLjQzYzAuMzkxLTAuMzkxLDAuMzkxLTEuMDIzLDAtMS40MTRzLTEuMDIzLTAuMzkxLTEuNDE0LDAgIEwxNi4wOTcsNDMuMzk1bC00Ljc3MywwLjUzbDAuNTMtNC43NzNsMjIuMzgtMjIuMzc4YzAuMzkxLTAuMzkxLDAuMzkxLTEuMDIzLDAtMS40MTRzLTEuMDIzLTAuMzkxLTEuNDE0LDBMMTAuNDQsMzcuNzM4ICBsLTMuMTgzLDAuMzU0TDM0Ljk0LDEwLjQwOWw5LjksOS45TDE3LjE1Nyw0Ny45OTJMMTcuNTExLDQ0LjgwOXogTTQ5LjA4MiwxNi4wNjdsLTkuOS05LjlsMS40MTUtMS40MTVsOS45LDkuOUw0OS4wODIsMTYuMDY3eiIgZmlsbD0iIzAwMDAwMCIvPgo8Zz4KPC9nPgo8Zz4KPC9nPgo8Zz4KPC9nPgo8Zz4KPC9nPgo8Zz4KPC9nPgo8Zz4KPC9nPgo8Zz4KPC9nPgo8Zz4KPC9nPgo8Zz4KPC9nPgo8Zz4KPC9nPgo8Zz4KPC9nPgo8Zz4KPC9nPgo8Zz4KPC9nPgo8Zz4KPC9nPgo8Zz4KPC9nPgo8L3N2Zz4K" />}
                            {this.state.isEditMode && this.state.showSave && <img onClick={this.handleSaveClick} src="data:image/svg+xml;utf8;base64,PD94bWwgdmVyc2lvbj0iMS4wIiBlbmNvZGluZz0iaXNvLTg4NTktMSI/Pgo8c3ZnIHhtbG5zPSJodHRwOi8vd3d3LnczLm9yZy8yMDAwL3N2ZyIgeG1sbnM6eGxpbms9Imh0dHA6Ly93d3cudzMub3JnLzE5OTkveGxpbmsiIHZlcnNpb249IjEuMSIgdmlld0JveD0iMCAwIDI2IDI2IiBlbmFibGUtYmFja2dyb3VuZD0ibmV3IDAgMCAyNiAyNiIgd2lkdGg9IjI0cHgiIGhlaWdodD0iMjRweCI+CiAgPHBhdGggZD0ibS4zLDE0Yy0wLjItMC4yLTAuMy0wLjUtMC4zLTAuN3MwLjEtMC41IDAuMy0wLjdsMS40LTEuNGMwLjQtMC40IDEtMC40IDEuNCwwbC4xLC4xIDUuNSw1LjljMC4yLDAuMiAwLjUsMC4yIDAuNywwbDEzLjQtMTMuOWgwLjF2LTguODgxNzhlLTE2YzAuNC0wLjQgMS0wLjQgMS40LDBsMS40LDEuNGMwLjQsMC40IDAuNCwxIDAsMS40bDAsMC0xNiwxNi42Yy0wLjIsMC4yLTAuNCwwLjMtMC43LDAuMy0wLjMsMC0wLjUtMC4xLTAuNy0wLjNsLTcuOC04LjQtLjItLjN6IiBmaWxsPSIjMDAwMDAwIi8+Cjwvc3ZnPgo=" />}
                        </div>
                        <div id="jobOfferCloseIcon" className="jobOfferIcon">
                            <img onClick={this.handleCloseModal} src="data:image/svg+xml;utf8;base64,PD94bWwgdmVyc2lvbj0iMS4wIiBlbmNvZGluZz0iaXNvLTg4NTktMSI/Pgo8IS0tIEdlbmVyYXRvcjogQWRvYmUgSWxsdXN0cmF0b3IgMTYuMC4wLCBTVkcgRXhwb3J0IFBsdWctSW4gLiBTVkcgVmVyc2lvbjogNi4wMCBCdWlsZCAwKSAgLS0+CjwhRE9DVFlQRSBzdmcgUFVCTElDICItLy9XM0MvL0RURCBTVkcgMS4xLy9FTiIgImh0dHA6Ly93d3cudzMub3JnL0dyYXBoaWNzL1NWRy8xLjEvRFREL3N2ZzExLmR0ZCI+CjxzdmcgeG1sbnM9Imh0dHA6Ly93d3cudzMub3JnLzIwMDAvc3ZnIiB4bWxuczp4bGluaz0iaHR0cDovL3d3dy53My5vcmcvMTk5OS94bGluayIgdmVyc2lvbj0iMS4xIiBpZD0iQ2FwYV8xIiB4PSIwcHgiIHk9IjBweCIgd2lkdGg9IjI0cHgiIGhlaWdodD0iMjRweCIgdmlld0JveD0iMCAwIDUxMCA1MTAiIHN0eWxlPSJlbmFibGUtYmFja2dyb3VuZDpuZXcgMCAwIDUxMCA1MTA7IiB4bWw6c3BhY2U9InByZXNlcnZlIj4KPGc+Cgk8ZyBpZD0iY2FuY2VsIj4KCQk8cGF0aCBkPSJNMjU1LDBDMTE0Ljc1LDAsMCwxMTQuNzUsMCwyNTVzMTE0Ljc1LDI1NSwyNTUsMjU1czI1NS0xMTQuNzUsMjU1LTI1NVMzOTUuMjUsMCwyNTUsMHogTTM4Mi41LDM0Ni44bC0zNS43LDM1LjcgICAgTDI1NSwyOTAuN2wtOTEuOCw5MS44bC0zNS43LTM1LjdsOTEuOC05MS44bC05MS44LTkxLjhsMzUuNy0zNS43bDkxLjgsOTEuOGw5MS44LTkxLjhsMzUuNywzNS43TDI5MC43LDI1NUwzODIuNSwzNDYuOHoiIGZpbGw9IiMwMDAwMDAiLz4KCTwvZz4KPC9nPgo8Zz4KPC9nPgo8Zz4KPC9nPgo8Zz4KPC9nPgo8Zz4KPC9nPgo8Zz4KPC9nPgo8Zz4KPC9nPgo8Zz4KPC9nPgo8Zz4KPC9nPgo8Zz4KPC9nPgo8Zz4KPC9nPgo8Zz4KPC9nPgo8Zz4KPC9nPgo8Zz4KPC9nPgo8Zz4KPC9nPgo8Zz4KPC9nPgo8L3N2Zz4K" />
                        </div>
                    </div>
                    {!this.state.isEditMode && this.getViewWithoutEditMode(job)}
                    {this.state.isEditMode && this.getViewEditMode(job)}
                </ReactModal>
            </div>
        );
    }
}