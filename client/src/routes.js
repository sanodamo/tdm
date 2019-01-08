import React from 'react';
import { Route, IndexRoute } from 'react-router';
import App from './components/App';
import HomePage from './components/home/HomePage';
import AboutPage from './components/about/AboutPage';


import SearchContainer from './components/tdm/search.container'; 

//eslint-disable-line import/no-named-as-default

export default (
  <Route path="/" component={App}>
    <IndexRoute component={HomePage} />   
    <Route path="about" component={AboutPage} />
    <Route path="search" component={SearchContainer} />
  </Route>
);
