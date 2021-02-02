import React from 'react';
import { shallow } from 'enzyme';
import ProjectProgressTrackerApp from './ProjectProgressTrackerApp';


describe("ProjectProgressTrackerApp", () => {
  it("should render ProjectProgressTrackerApp", () => {
    const component = shallow(<ProjectProgressTrackerApp />);
    expect(component.getElements()).toMatchSnapshot();
  });
});
