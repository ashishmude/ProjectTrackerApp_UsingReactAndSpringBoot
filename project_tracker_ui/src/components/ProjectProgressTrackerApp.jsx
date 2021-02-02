import React, { Component } from "react";
import { withStyles } from "@material-ui/core/styles";
import {
  AppBar,
  Toolbar,
  Typography,
  IconButton,
  makeStyles,
} from "@material-ui/core";
import ListCheckpointsComponent from "./ListCheckpointsComponent";
import ProjectService from "../service/ProjectService";


const styles = (theme) => ({
  titleBar: {
      justifyContent: "center",
      display: "flex",
    },
});

class ProjectProgressTrackerApp extends Component {
  constructor(props) {
      super(props);
      this.state = {
        project: {}
      };
      this.refreshProjectDetails = this.refreshProjectDetails.bind(this);
    }
    componentDidMount() {
      this.refreshProjectDetails();
    }
    refreshProjectDetails() {
     ProjectService.retrieveProjectDetails()
       .then(response => {
           console.log(response);
           this.setState({ project: response.data });
       })
    }

  render() {
    const  { project } = this.state;
    const { classes } = this.props;
    return (
      <>
        <AppBar position="static">
          <Toolbar className={classes.titleBar}>
            <IconButton
              edge="start"
              color="inherit"
              aria-label="menu"
            ></IconButton>
            <Typography variant="h6">{ project.name } : { project.description }</Typography>
          </Toolbar>
        </AppBar>
        <ListCheckpointsComponent />
       </>
    );
  }
};

export default withStyles(styles)(ProjectProgressTrackerApp);
