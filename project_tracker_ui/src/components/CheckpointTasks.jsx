import React from "react";
import { DataGrid } from "@material-ui/data-grid";

const columns = [
  { field: "taskName", headerName: "Task", flex: 1 },
  { field: "expectedCompletionDate", headerName: "ETA", flex: 1 },
  { field: "comments", headerName: "Comments", flex: 1 },
  {
    field: "completed",
    headerName: "Status",
    width: 160,
    valueFormatter: (params) => (params.value ? "Completed" : "In Progress"),
  },
];

const CheckpointTasks = ({ taskList }) => {
  return (
    <div style={{ height: 300, width: "100%" }}>
      <DataGrid
        rows={taskList}
        checkboxSelection={false}
        columns={columns}
        pageSize={5}
        hideFooter
      />
    </div>
  );
};
export default CheckpointTasks;
