# Calendar Assistant
Calendar Assistant is an API which resolves conflicts of the scheduled meetings

## Features
This API uses H2 db and has already saved some dummy data.
- To retrieve all the data use api 
  ### GET /api/calendar/meeting/employee/getAll

- To retrieve records based on Employee designation use api
  ### GET /api/calendar/meeting/getEmployee/designation/{designation}
- To retrieve records based on Employee Name use api
  #### GET /api/calendar/meeting/getEmployee/employeeName/{employeeName}
- To resolve Conflicts in the scheduled Meetings use api (refer SampleInput.json for body)
  #### POST /api/calendar/meeting/resolveconflicts



