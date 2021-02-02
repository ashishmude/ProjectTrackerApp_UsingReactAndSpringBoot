import React from 'react';
import { shallow } from 'enzyme';
import CheckpointTasks from './CheckpointTasks';


describe("CheckpointTasks", () => {
  it("should render CheckpointTasks", () => {
    const component = shallow(<CheckpointTasks />);
    expect(component.getElements()).toMatchSnapshot();
  });
});
