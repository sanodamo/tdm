import React from 'react';
import PropTypes from 'prop-types';
import { Link, IndexLink } from 'react-router';


const Header = ({loading}) => {
  console.log("loading....");
  console.log(loading);
  return (
    <nav>
      <IndexLink to="/" activeClassName="active">Home</IndexLink>
      {" | "}
      <Link to="/about" activeClassName="active">About</Link>
       {" | "}      
      <Link to="/search" activeClassName="active">Search</Link>

      {loading && <span style={{paddingLeft:'20px'}}>Loading....</span>}
   
    </nav>   
   
  );
};

Header.propTypes = {
  loading : PropTypes.bool.isRequired
};
export default Header;
