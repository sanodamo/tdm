import {combineReducers} from 'redux';
import scenario from './scenario.reducer';
import searchResults from './search.reducer';
import clientSettings from './client.settings.reducer'

import ajaxCallsInProgress from './ajaxStatusReducer';

const rootReducer = combineReducers({
    scenario, searchResults, clientSettings, ajaxCallsInProgress
});

export default rootReducer;