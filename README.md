# Team DontBreakPls Project 1

- A Todo List application. The user can create a user profile for creating, reading, updating, and deleting todo tasks from the database. Built with Java 8.

</a>

- [Installation](#installation)
- [Usage](#usage)
- [Contributors](#contributors)
- [License](#license)

## Installation

- In order to access this application, all you need to do is navigate to <https://diuguide.github.io/>, or click the hyperlink or picture of the application above and you will be directed to the website.

[back to Table of Contents](#table-of-contents)

## Usage

- The UI for this application is Postman. The user will need to clone both the WebApp and ORM to their local machine in order to run the application.
- To create a user create a POST route in postman with the route **http://localhost:8080/TaskForce/user** use the template below to register a user.

        {
        "username":"sampleUsername",
        "password":"s@mplep@$$w0r3",
        "email":"sample.email@revature.com",
        "age":"XX",
        "first_name":"Sample",
        "last_name":"User"
        }

- Once a user is registered, you may log into the application by creating a PUT request with the same route used above. Use the template below to login to the application

        {
        "username":"sampleUsername",
        "password":"s@mplep@$$w0r3"
        }

- If the login is successful, you will recieve:

       Succesfully Logged In: sampleUser

- After logging in, the user can create a task with a POST route in post man using this route: **http://localhost:8080/TaskForce/task** and the template below:

       {
           "title":"Sample Title",
           "message":"Sample message",
           "dueDate":"05/31/2021",
           "username":"sampleUsername"
       }

- Once a task has been created, the task can be deleted by using a DELETE route in postman. Use the template below:

        {
        "taskId":"514"
        }

- The user can update the content of a task with a PUT route using the following templates:

- The user can change each part of the task by using these options:

        "option":"title",
                 "content",
                 "dueDate",
                 "state"

- To change task title:

        {
            "option":"title",
            "title":"Changing title of task 513",
            "taskId":"513"
        }

- To change task message:

        {
            "option":"content",
            "message":"Changing the content of task 513",
            "taskId":"513"
        }

- To change task due date:

        {
            "option":"date_due",
            "date_due":"4/23/1987",
            "taskId":"513"
        }

- To change task state:

        {
            "option":"state",
            "taskId":"513"
        }

[back to Table of Contents](#table-of-contents)

## Contributors

- [Everett Diuguid](https://github.com/diuguide/)

[back to Table of Contents](#table-of-contents)

## License

[MIT](LICENSE) copyright (c) 2020 Everett Diuguid

[back to Table of Contents](#table-of-contents)
