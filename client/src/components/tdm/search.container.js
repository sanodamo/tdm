import React from 'react';
import { connect } from 'react-redux';
import { browserHistory } from 'react-router';
import { bindActionCreators } from 'redux';
import PropTypes from 'prop-types';
import { Button, ButtonToolbar, DropdownButton, MenuItem } from 'react-bootstrap';
import { Typeahead } from 'react-bootstrap-typeahead';
import toastr from 'toastr';
import * as searchActions from '../../actions/search.action';
import SearchList from './search.list';
import _ from 'lodash'

class SearchContainer extends React.Component {
  constructor(props, context) {
    super(props, context);
    this.state = {      
      allowNew: false,
      multiple: false,
      scenarios: Object.assign([], this.props.scenarios),
      clients: Object.assign([], this.props.clients),
      products : [{product : "rad", displayName : "Rad"},{product : "onc", displayName : "Onc"}],
      selectedScenarios: [],
      selectedClients: [],
      selectedProducts: [],
      searchResults: []
    };

    this._loadSearchableScenario = this._loadSearchableScenario.bind(this);
    this._loadClients = this._loadClients.bind(this);
    this._handleScenarioSearch = this._handleScenarioSearch.bind(this);
    this._handleClientSearch = this._handleClientSearch.bind(this);
    this._handleProductSelect = this._handleProductSelect.bind(this);
    this._onSubmitSearch = this._onSubmitSearch.bind(this);
    this._renderMenuItemChildren = this._renderMenuItemChildren.bind(this);
    this._drillDownHandler = this._drillDownHandler.bind(this);

    this._loadSearchableScenario();
    this._loadClients();
  }

  _renderMenuItemChildren(option, props, index) {
    return (
      <div key={option.id}>
        <span>{option.description}</span>
      </div>
    );
  }

  _loadSearchableScenario() {

    this.props.actions.loadTestScenarios()
      .then(() => {
        console.log('completed')
        this.setState({ scenarios: this.props.scenarios });
      }
      )
      .catch(error => {
        toastr.error(error);
        this.setState({ scenarios: [] });
      });

  }

  _loadClients() {

    this.props.actions.loadClients()
      .then(() => {
        console.log('completed')
        this.setState({ clients: this.props.clients });
      }
      )
      .catch(error => {
        toastr.error(error);
        this.setState({ clients: [] });
      });

  }

  _handleScenarioSearch(selectedItem) {
    this.setState({ selectedScenarios: selectedItem })
  }

  _handleClientSearch(selectedItem) {
    this.setState({ selectedClients: selectedItem })
  }

  _onSubmitSearch(event) {
   
    var scenario = _.head(this.state.selectedScenarios);
    var client = _.head(this.state.selectedClients);
    var product = _.head(this.state.selectedProducts);

     this.props.actions.SearchTestScenarios(scenario, client, product)
      .then(() => {        
        this.setState({ searchResults: this.props.searchResults });
      }
      )
      .catch(error => {
        toastr.error(error);
        this.setState({ searchResults: [] });
      });
  }

  _handleProductSelect(selectedItem)
  {
    this.setState({ selectedProducts: selectedItem })
  }

  _drillDownHandler(selectedLink)
  {
    this.props.actions.DrillDownScenarios(selectedLink.uri)
      .then(() => {        
        this.setState({ searchResults: this.props.searchResults });
      }
      )
      .catch(error => {
        toastr.error(error);
        this.setState({ searchResults: [] });
      });

    console.log("Namma ivideyanu" + selectedLink);
  }

  render() {
    let isLoading = this.props.loading;
    return (
      <div>
        <div>
          <div style={{ width: '50%', float: 'left' }}>
            <label>Enter your scenario: </label>
            <Typeahead
              {...this.state}
              labelKey="description"
              options={this.state.scenarios}
              placeholder="Search for a scenario..."
              renderMenuItemChildren={this._renderMenuItemChildren}
              onChange={this._handleScenarioSearch}
            />
          </div>
          <div style={{ paddingLeft: '5px', width: '20%', float: 'left' }}>
            <label>Client: </label>
            <Typeahead
              {...this.state}
              labelKey="fullName"
              options={this.state.clients}
              placeholder="Search for a client..."
              onChange={this._handleClientSearch}
            />
          </div>
          <div style={{ paddingLeft: '5px', width: '20%', float: 'left' }}>
            <label>Product: </label>            
              <Typeahead
              {...this.state}
              labelKey="displayName"
              options={this.state.products}
              placeholder="Search for a products..."
              onChange={this._handleProductSelect}
            />            
          </div>
          <div style={{ paddingTop: '20px', float: 'right' }}>
            <ButtonToolbar>
              <Button bsStyle="primary" disabled={isLoading} onClick={this._onSubmitSearch}>                
                 {isLoading ? 'Loading...' : 'Search'}
               </Button>
            </ButtonToolbar>
          </div>
        </div>
        <br/>
        <SearchList testData={this.state.searchResults} drillDownHandler={this._drillDownHandler}   />
      </div>
    );
  }
}

SearchContainer.PropTypes = {
  scenarios: PropTypes.object.isRequired,
  clients: PropTypes.object.isRequired,
  searchResults : PropTypes.object.isRequired,
  actions: PropTypes.object.isRequired,
  loading: PropTypes.bool.isRequired
};

function mapDispatchToProps(dispatch) {
  return {
    actions: bindActionCreators(searchActions, dispatch)
  };
}

function mapStateToProps(state, ownProps) {
  return {
    scenarios: state.scenario,
    clients: state.clientSettings,
    searchResults : state.searchResults,
    loading: state.ajaxCallsInProgress > 0
  };
}

export default connect(mapStateToProps, mapDispatchToProps)(SearchContainer);