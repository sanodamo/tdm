import * as types from '../actions/actionTypes';
import initialState  from './initialState';
export default function ScenarioReducer(state = initialState.scenarios ,action)
{
    switch(action.type)
    {
        case types.LOAD_TEST_SCENARIOS_SUCCESS:
            return action.scenarios;            
        default :
            return state;
    }
}