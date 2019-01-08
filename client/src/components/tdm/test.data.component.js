import React from 'react';
import { connect } from 'react-redux';
import { browserHistory } from 'react-router';
import { bindActionCreators } from 'redux';
import PropTypes from 'prop-types';
import { Table } from 'react-bootstrap';
import _ from 'lodash'
import { MemberListComponent } from './data-views-component/member.list';

export class TestDataComponent extends React.Component {
    constructor(props) {
        super(props);       
    }


    render() {

        const dataType = this.props.type;

        let view = null

        switch(_.toLower(dataType)){
            case 'member':
            case 'physician':
            case 'provider':
                view = <MemberListComponent type={dataType} data={this.props.data} drillDownHandler={this.props.drillDownHandler} />
                break;

        }
        return (
            <div>
                {view}
            </div>
        );
    }
}
