import React from 'react';
import { shallow } from 'enzyme';
import ListCheckpointsComponent from './ListCheckpointsComponent';


describe("ListCheckpointsComponent", () => {
  it("should render ListCheckpointsComponent", () => {
    const component = shallow(<ListCheckpointsComponent />);
    expect(component.getElements()).toMatchSnapshot();
  });
});
