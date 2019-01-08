import * as types from './actionTypes';
import { beginAjaxCall, ajaxCallError } from './ajaxStatusActions'
import _ from 'lodash'

export function loadTestScenariosSuccess(scenarios) {
    return { type: types.LOAD_TEST_SCENARIOS_SUCCESS, scenarios };
}

export function loadClientsSuccess(clients) {
    return { type: types.LOAD_CLIENTS_SUCCESS, clients };
}

export function SearchTestScenariosSuccess(searchResults) {
    return { type: types.SEARCH_TEST_SCENARIOS_SUCCESS, searchResults };
}

export function drilldownSuccess(searchResults) {
    return { type: types.DRILLDOWN_SUCCESS, searchResults };
}

export function loadTestScenarios() {
    return function (dispatch) {
        dispatch(beginAjaxCall())
        return fetch(`/api/scenarios`)
            .then(res => res.json())
            .then(
            data => {
                console.log(data);
                dispatch(loadTestScenariosSuccess(data.Scenarios));
            },
            err => {               
                dispatch(ajaxCallError())
                throw (err);
            }
            )
            .catch(error => {
                dispatch(ajaxCallError())
                console.log('request failed', error);
            });

    };
}

export function loadClients() {
    return function (dispatch) {
        dispatch(beginAjaxCall())
        return fetch(`/api/clients`)
            .then(res => res.json())
            .then(
            data => {
                console.log(data);
                dispatch(loadClientsSuccess(data.Clients));
            },
            err => {
                console.log(err);
                dispatch(ajaxCallError())
                throw (err);
            }
            )
            .catch(error => {
                dispatch(ajaxCallError())
                console.log('request failed', error);
            });

    };
}

export function SearchTestScenarios(currentScenario, client, product) {           
    return function (dispatch) {
        dispatch(beginAjaxCall())        
        var url = _.replace(_.replace(currentScenario.api, ':clientId', client.clientId), ':product', product.product);
        return fetch(url)
            .then(res => res.json())
            .then(
            data => {
                console.log(data);
                dispatch(SearchTestScenariosSuccess(data));
            },
            err => {
                console.log(err);
                dispatch(ajaxCallError())
                throw (err);
            }
            )
            .catch(error => {
                dispatch(ajaxCallError())
                console.log('request failed', error);
            });

    };
}

export function DrillDownScenarios(url) {           
    return function (dispatch) {
        dispatch(beginAjaxCall())                
        return fetch(url)
            .then(res => res.json())
            .then(
            data => {
                console.log(data);
                dispatch(drilldownSuccess(data));
            },
            err => {
                console.log(err);
                dispatch(ajaxCallError())
                throw (err);
            }
            )
            .catch(error => {
                dispatch(ajaxCallError())
                console.log('request failed', error);
            });

    };
}