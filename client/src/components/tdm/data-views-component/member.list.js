import React from 'react';
import { connect } from 'react-redux';
import { browserHistory } from 'react-router';
import { bindActionCreators } from 'redux';
import PropTypes from 'prop-types';
import { Table  } from 'react-bootstrap';
import _ from 'lodash'


export class MemberListComponent extends React.Component {
    constructor(props) {
        super(props);  
        this._handleDrilldown = this._handleDrilldown.bind(this);     
    }

    _handleDrilldown(e, item) {
        e.preventDefault();
        console.log(item);
        this.props.drillDownHandler(item);
    }

    render() {
        return (
            <div>
                <Table responsive hover >
                    <thead>
                        <tr>
                            <th>#</th>
                            <th>Id</th>
                            <th>Name</th>
                            <th>Dob</th>                            
                        </tr>
                    </thead>
                    <tbody>
                        {this.props.data.map((_item, i) => {
                            let id = null;
                            if(_item.uri){
                                 id = <td><a href="#" onClick={(event) => this._handleDrilldown(event,_item)}> {_item.subSsn}</a></td>              
                            }else{
                                 id = <td>{_item.subSsn}</td>   
                            }
                            return (
                                <tr key={i}>
                                    <td>{i + 1}</td>
                                    {id}
                                    <td>{_.startCase(_.toLower(_item.firstName + " " + _item.lastName))} </td>
                                    <td>{_item.dob} </td>
                                </tr>
                            )
                        })}

                    </tbody>
                </Table>
            </div>
        );
    }
}
