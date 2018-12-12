import React, {Component} from 'react';
import './FrontPage.css';

import Post from '../../components/Post/Post';
import Navigator from '../../components/Navigator/Navigator';

class FrontPage extends Component{

	render(){
		return(
			<>
				<Navigator />
				<div className="frontpage-posts">
					<Post title="Is the world ending?" author="Abrudan Andrei" body="Yes it is you dum-dums" />
					<Post title="How are cookies made!" author="Barcau Emanuel Theodore Rosevelt" body="Just bake em' you fool" />
					<Post title="Why are you running?!" author="Abrudean Sergiu" body="My reaction when skype wakes up at startup" />
				</div>
			</>
		);
	}
}

export default FrontPage;
