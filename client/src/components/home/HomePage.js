import React from 'react';
import {Link} from 'react-router';

class HomePage extends React.Component{

    render(){
        return (
               <div className="jumbotron">
                <h1>Test Data Management</h1>
                <p>Search database to get the right test data for configured scenarios
                </p>
                  <Link to="about" className="btn btn-primary btn-lg"> Learn more </Link>
               </div> 
        );
    }
}

export default HomePage;