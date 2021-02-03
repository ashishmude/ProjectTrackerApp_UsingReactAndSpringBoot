import React from 'react';
import { shallow } from 'enzyme';
import CheckpointTasks from './CheckpointTasks';


describe("CheckpointTasks", () => {

  const data = [
      {
        id: 1,
        taskName: "Meetings with stakeholder",
        expectedCompletionDate: "25-01-2021",
        checkpointId: 1,
        comments: "Finished",
        completed: true,
      },
    ];
  it("should match the snapshot for CheckpointTasks", () => {
    const tree = shallow(<CheckpointTasks taskList={data} />);
    expect(tree.getElement()).toMatchSnapshot();
  });

});
