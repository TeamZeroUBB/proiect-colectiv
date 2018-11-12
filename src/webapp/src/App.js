import React, { Component } from 'react';
import './App.css';

//imports for components
import Post from './components/Post/Post';

class App extends Component {
  render() {
    return (
      <div className="App">
        <Post title="Is the world ending?" author="Abrudan Andrei" body="Yes it is you dum-dums" />
        <Post title="How are cookies made!" author="Barcau Emanuel Theodore Rosevelt" body="Just bake em' you fool" />
      </div>
    );
  }
}

export default App;
