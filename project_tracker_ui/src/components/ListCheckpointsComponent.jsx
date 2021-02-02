import React, { Component } from "react";
import ProjectCheckpointService from "../service/ProjectCheckpointService";
import CheckpointTasks from "./CheckpointTasks";
import {
  Container,
  AccordionDetails,
  AccordionSummary,
  Accordion,
  Typography,
  Chip,
} from "@material-ui/core";
import ExpandMoreIcon from "@material-ui/icons/ExpandMore";
import { withStyles } from "@material-ui/core/styles";
import data from "../data.json";

const styles = (theme) => ({
  chip: {
    marginLeft: theme.spacing(1),
  },
  accordionSummary: {
    display: "flex",
    flexDirection: "row",
    justifyContent: "space-between",
  },
  container: {
    marginTop: theme.spacing(2),
  },
  title: {
    marginBottom: theme.spacing(2),
  },
  accordionDetails: {
    display: "flex",
    flexDirection: "column",
  },
  description: {
    marginBottom: theme.spacing(1),
  },
});

class ListCheckpointsComponent extends Component {
  constructor(props) {
    super(props);
    this.state = {
      data: [],
      expanded: {},
    };
    this.refreshCheckpoints = this.refreshCheckpoints.bind(this);
  }
  componentDidMount() {
    this.refreshCheckpoints();
  }
  refreshCheckpoints() {
     ProjectCheckpointService.retrieveAllCheckpoints()
         .then(response => {
             console.log(response);
             this.setState({ data: response.data });
         })
    //this.setState({ data });
  }
  onExpandedChange = (expanded) => {
    this.setState({ expanded });
  };

  render() {
    const { data } = this.state;
    const { classes } = this.props;
    return (
      <>
        <Container className={classes.container}>
          <Typography variant="h6" className={classes.title}>
            Checkpoints
          </Typography>
          {data.map((checkpoint, index) => (
            <Accordion>
              <AccordionSummary
                expandIcon={<ExpandMoreIcon />}
                aria-controls="panel1a-content"
                id="panel1a-header"
                className={classes.accordionSummary}
              >
                <Typography>{`${index + 1}. ${
                  checkpoint.checkpointName
                }`}</Typography>
                <Chip
                  size="small"
                  label={`${checkpoint.completionPercentage}% complete`}
                  color="primary"
                  className={classes.chip}
                />
              </AccordionSummary>
              <AccordionDetails className={classes.accordionDetails}>
                <Typography
                  variant="h6"
                  component="p"
                  className={classes.description}
                >
                  {checkpoint.description}
                </Typography>
                <CheckpointTasks
                  taskList={checkpoint.taskList}
                ></CheckpointTasks>
              </AccordionDetails>
            </Accordion>
          ))}
        </Container>
      </>
    );
  }
}

export default withStyles(styles)(ListCheckpointsComponent);
