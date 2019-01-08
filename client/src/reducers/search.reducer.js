import * as types from '../actions/actionTypes';
import initialState  from './initialState';
export default function SearchReducer(state = initialState.searchResults ,action)
{
    switch(action.type)
    {
        case types.SEARCH_TEST_SCENARIOS_SUCCESS:
            return action.searchResults;         
        case types.DRILLDOWN_SUCCESS:   
             return Object.assign({},state,action.searchResults);    
        default :
            return state;
    }
}