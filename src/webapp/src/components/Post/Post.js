import React, {Component} from 'react';
import './Post.css';

//props:
//1) Post title
//2) Post author
//3) Post body

class Post extends Component{
	render(){
		return (
			<div className='post-container'>
				<div className='post-title'>
					{this.props.title}
					<div className='post-author'>
						{this.props.author}
					</div>
				</div>
				<div className='post-body'>
					{this.props.body}
				</div>
			</div>
		);
	}
}

export default Post;
