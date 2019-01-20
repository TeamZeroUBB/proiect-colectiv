import React, { Component } from 'react'
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faHome, faAt, faKey, faPhone } from '@fortawesome/free-solid-svg-icons';
import './UserProfile.css'

const userPics = [
	"https://i.ytimg.com/vi/-dXu_-Yl4XA/hqdefault.jpg",
	"http://4.bp.blogspot.com/-2Y_cBndYTTM/U03vweY__WI/AAAAAAAAALY/_zR8KX0TtKI/s1600/duanfa1.jpg",
	"http://everythreeweekly.com/wp-content/uploads/2015/12/handsome-man-938x535.jpg",
	"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQLVL1LdJhnw-6NVgNw2dRHBWdPRomsi6uBqPNHVXrMVUALOg019g",
	"https://www.koreandrama.org/wp-content/uploads/2018/05/Handsome-Guy-and-Jung-Eum-1.jpg",
	"https://www.animenewsnetwork.com/thumbnails/crop600x315/cms/buried-treasure/69556/handsomegirlbig.jpg",
	"https://www.famousbirthsdeaths.com/wp-content/uploads/2017/12/casey-neistat-bio-net-worth-facts.jpg",
	"https://i.kinja-img.com/gawker-media/image/upload/s--kSq3QP7s--/c_scale,f_auto,fl_progressive,q_80,w_800/v9kmolepadmesfeumis1.png",
	"https://imgix.ranker.com/user_node_img/50086/1001708051/original/ein-photo-u1?w=650&q=50&fm=jpg&fit=crop&crop=faces",
	"https://image.myanimelist.net/ui/THP3d05SFhdRvLOKLs2gqUFz0THCvIe10gufubJGDV2XdWQOGjgCmk-Xv-oy3g6MEK6BkLpRZLoFujymK4FWaY-6i9DOCGUWffP4AumloxdyaIiwqh7Ctds78c7cUY2SMeANFbeiELl7z9_HOlvXog",
];

export default class UserProfile extends Component {
	render() {
		return (
			<div className="userprofile-container">
				<div className="userprofile-header">
					<img className="userprofile-picture" src={userPics[Math.floor(Math.random()*userPics.length)]} alt="profile pic"/>
					<div className="userprofile-name">{this.props.firstName} {this.props.LastName}</div>
					<div className="userprofile-username">{this.props.username}</div>
				</div>
				<div className="userprofile-body">
					<div className="userprofile-attributes">
						<div>
							<FontAwesomeIcon className="fa-fw" icon={faHome} />
							{this.props.city} 
						</div>
						<div>
							<FontAwesomeIcon className="fa-fw" icon={faKey} />
							{this.props.password}
						</div>
						<div>
							<FontAwesomeIcon className="fa-fw" icon={faPhone} />
							{this.props.phoneNumber}
						</div>
						<div>
							<FontAwesomeIcon className="fa-fw" icon={faAt} />
							{this.props.email}
						</div>
					</div>
				</div>
			</div>
        )
	}
}
