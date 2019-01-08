import React from 'react';
import PropTypes from 'prop-types';
import { PanelGroup, Panel } from 'react-bootstrap';
import { TestDataComponent } from './test.data.component';

const SearchList = ({ testData, drillDownHandler }) => {
    
    return (
        
        <div style={{marginTop:"50px"}}>
            <PanelGroup>
                {Object.keys(testData).map((_item, i) => (
                    <Panel header={_item} eventKey={i} key={i}>
                        <TestDataComponent type={_item} data={testData[_item]} drillDownHandler={drillDownHandler}/>
                    </Panel>
                ))}
            </PanelGroup>
        </div>
        
    );
};

SearchList.propTypes = {
    testData: PropTypes.array.isRequired,
    drillDownHandler: PropTypes.func.isRequired

};

export default SearchList;