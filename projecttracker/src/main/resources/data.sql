INSERT INTO projects (id, name, description)
    VALUES (1, 'Project - XYZ', 'Digital mentor platform');

INSERT INTO project_checkpoints (id, checkpoint_name, description, project_id)
    VALUES (1, 'Requirements Gathering', 'This phase involves gathering entire requirement which will help to decide the scope of the project', 1);
INSERT INTO project_checkpoints (id, checkpoint_name, description, project_id)
    VALUES (2, 'Planning', 'This phase involves creating solution for application, sign off, etc', 1);
INSERT INTO project_checkpoints (id, checkpoint_name, description, project_id)
    VALUES (3, 'Implementation', 'This phase involves development, implementation, etc tasks.', 1);
INSERT INTO project_checkpoints (id, checkpoint_name, description, project_id)
    VALUES (4, 'Handover/Training and Closure', 'This phase involves project handover/closure and training to end user(s).', 1);


INSERT INTO checkpoint_tasks (id,task_name,expected_completion_date,comments,completed,checkpoint_id)
    VALUES (1,'Meetings with stakeholder', '25-01-2021', 'Finished', true, 1);
INSERT INTO checkpoint_tasks (id,task_name,expected_completion_date,comments,completed,checkpoint_id)
    VALUES (2,'Technology discussions', '05-02-2021', 'In Progress', false, 1);
INSERT INTO checkpoint_tasks (id,task_name,expected_completion_date,comments,completed,checkpoint_id)
    VALUES (3,'Documenting', 'Completed', 'Completed and signed off', true, 1);
INSERT INTO checkpoint_tasks (id,task_name,expected_completion_date,comments,completed,checkpoint_id)
    VALUES (4,'Approvals', 'Done', 'Received all approvals',true, 1);

INSERT INTO checkpoint_tasks (id,task_name,expected_completion_date,comments,completed,checkpoint_id)
    VALUES (5,'Architectural Diagram', '05-02-2021', 'In Progress', false, 2);
INSERT INTO checkpoint_tasks (id,task_name,expected_completion_date,comments,completed,checkpoint_id)
    VALUES (6,'Solution sign-off', 'TBD', 'In Progress', false, 2);
INSERT INTO checkpoint_tasks (id,task_name,expected_completion_date,comments,completed,checkpoint_id)
    VALUES (7,'Resourcing', '01-02-2021', 'Finalised resources', true, 2);

INSERT INTO checkpoint_tasks (id,task_name,expected_completion_date,comments,completed,checkpoint_id)
    VALUES (8,'High Level Design', '01-02-2021', 'HLD Signed off', true, 3);
INSERT INTO checkpoint_tasks (id,task_name,expected_completion_date,comments,completed,checkpoint_id)
    VALUES (9,'Development Completion', '01-07-2021', 'In Progress', false, 3);
INSERT INTO checkpoint_tasks (id,task_name,expected_completion_date,comments,completed,checkpoint_id)
    VALUES (10,'Testing Completion', 'TBD', 'In Progress', false, 3);
INSERT INTO checkpoint_tasks (id,task_name,expected_completion_date,comments,completed,checkpoint_id)
    VALUES (11,'Documentation', '01-05-2021', 'In Progress', false, 3);
INSERT INTO checkpoint_tasks (id,task_name,expected_completion_date,comments,completed,checkpoint_id)
    VALUES (12,'Testing and Business Sign off', '01-09-2021', 'In Progress', false, 3);
INSERT INTO checkpoint_tasks (id,task_name,expected_completion_date,comments,completed,checkpoint_id)
    VALUES (13,'Production implementation', '25-09-2021', 'In Progress', false, 3);

INSERT INTO checkpoint_tasks (id,task_name,expected_completion_date,comments,completed,checkpoint_id)
   VALUES (14,'Project Handover', '01-10-2021', 'In Progress', false, 4);
INSERT INTO checkpoint_tasks (id,task_name,expected_completion_date,comments,completed,checkpoint_id)
   VALUES (15,'End User Training', '01-11-2021', 'In Progress', false, 4);