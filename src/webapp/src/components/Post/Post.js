import React, {Component} from 'react';
import './Post.css';

//props:
//1) Post title
//2) Post author
//3) Post body

class Post extends Component{
    render(){
        return (
            <div class='post-container'>
                <div class='post-title'>
                    {this.props.title}
                    <div class='post-author'>
                        {this.props.author}
                    </div>
                </div>
                <div class='post-body'>
                    {this.props.body}
                </div>
            </div>
        );
    }
}

export default Post;