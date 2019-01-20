import React, { Component } from 'react';
import "./Input.css";
import i18n from '../../../i18n';

//usage:
//<Input inputType="input" labelName="Name" rules={required:true, minLength:3} type="text/password" etc. />

class Input extends Component {
	constructor(props) {
		super(props);

		this.state = {
			isValid: null,
			inputValue: null,
			providedErrorMessage: null,
			validationColorBar: 'input-default-bar',
			validationColorText: 'input-default-text',
		};
	}

	decideType() {
		var { inputType, rules, errorMessage, ...other } = this.props;
		switch (inputType) {
			case ('input'):
				{
					var { labelName, ...attributes } = other;
					return (
						<div className="input-group">
							<input className={`input-textinput ${this.state.validationColorText}`} required onBlur={evt => this.setAppropriateColor(evt)} {...attributes} />
							<label className={`input-label ${this.state.validationColorText}`}>{labelName}</label>
							{this.state.isValid === false &&
								<>
									<p className="input-error-text">{errorMessage}</p>
									<p className="input-error-text">{this.state.providedErrorMessage}</p>
								</>
							}
						</div>
					);
				}

			default:
				return (<input />);
		}
	}

	setAppropriateColor(evt) {
		const ivalue = evt.target.value;
		if (this.checkValidity(ivalue, this.props.rules)) {
			this.setState({ isValid: true, inputValue: ivalue, validationColorBar: "input-success-bar", validationColorText: "input-success-text" });
		}
		else {
			this.setState({ isValid: false, inputValue: ivalue, validationColorBar: "input-error-bar", validationColorText: "input-error-text" });
		}
	}

	countUpper(str) {
		for (var i = 0, len = str.length, count = 0; i < len; ++i) {
			if (str[i] >= 'A' && str[i] <= 'Z')
				++count;
		}
		return count;
	}

	countLower(str) {
		for (var i = 0, len = str.length, count = 0; i < len; ++i) {
			if (str[i] >= 'a' && str[i] <= 'z')
				++count;
		}
		return count;
	}

	//available validation rules:
	// - required
	// - minLength
	// - maxLength
	// - override to true
	// - override to false
	checkValidity(value, rules) {
		if(rules === undefined)
			return true;

		let isValid = true;
		let message = [];

		if (rules.required) {
			isValid = value.trim() !== '' && isValid;
			if (!isValid)
				message.push(i18n.t("input_requiredField"));
		}

		if (rules.minLength) {
			isValid = value.length >= rules.minLength && isValid;
			if (!isValid)
				message.push(`${i18n.t("input_longerThan")} ${rules.minLength} ${i18n.t("input_character")}`);
		}

		if (rules.maxLength) {
			isValid = value.length <= rules.maxLength && isValid;
			if (!isValid)
				message.push(`${i18n.t("input_shorterThan")} ${rules.maxLength} ${i18n.t("input_character")}`);
		}

		if (rules.minUpper) {
			isValid = this.countUpper(value) >= rules.minUpper && isValid;
			if (!isValid)
				message.push(`${i18n.t("input_shouldContain")} ${rules.minUpper} ${i18n.t("input_upperCase")}`);
		}

		if (rules.minLower) {
			isValid = this.countLower(value) >= rules.minLower && isValid;
			if (!isValid)
				message.push(`${i18n.t("input_shouldContain")} ${rules.minLength} ${i18n.t("input_lowerCase")}`);
		}

		if (rules.shouldMatch) {
			isValid = value === rules.shouldMatch.current.state.inputValue && isValid;
			//no provided message here. The parent should say what the problem is
		}

		this.setState({ providedErrorMessage: message[0] });

		return isValid;
	}

	render() {
		return (<>{this.decideType()}</>);
	}
}

export default Input;
