# Developer Guide

* [Setting Up](#setting-up)
* [Design](#design)
* [Implementation](#implementation)
* [Testing](#testing)
* [Continuous Integration](#continuous-integration)
* [Making a Release](#making-a-release)
* [Managing Dependencies](#managing-dependencies)
* [Appendix A: User Stories](#appendix-a--user-stories)
* [Appendix B: Use Cases](#appendix-b--use-cases)
* [Appendix C: Non Functional Requirements](#appendix-c--non-functional-requirements)
* [Appendix D: Glossary](#appendix-d--glossary)
* [Appendix E : Product Survey](#appendix-e-product-survey)


## Setting up

#### Prerequisites

1. **JDK 8** or later
2. **Eclipse** IDE
3. **e(fx)clipse** plugin for Eclipse

#### Importing the project into Eclipse

0. Fork this repo, and clone the fork to your computer
1. Open Eclipse (Note: Ensure you have installed the **e(fx)clipse plugin** as given in the prerequisites above)
2. Click `File` > `Import`
3. Click `General` > `Existing Projects into Workspace` > `Next`
4. Click `Browse`, then locate the project's directory
5. Click `Finish`

## Design
<img src="images/mainClassDiagram.png"/>

## Testing

* In Eclipse, right-click on the `test/java` folder and choose `Run as` > `JUnit Test`

## Appendix A : User Stories

Priorities: High (must have) - `* * *`, Medium (nice to have)  - `* *`,  Low (unlikely to have) - `*`


Priority | As a ... | I want to ... | So that I can...
-------- | :-------- | :--------- | :-----------
`* * *` | new user | see usage instructions | refer to instructions when I forget how to use the App
`* * *`| new user | set my storage file location | use a cloud syncing services to access data from multiple computers
`* * *`| user | create a task | keep track of my new tasks; may include date and/or time
`* * *`| user | find a task by name | locate details of tasks without having to go through the entire list
`* * *`| user | find a task by dates | locate details of tasks without having to go through the entire list
`* * *`| user | find a task by groups | locate details of tasks without having to go through the entire list
`* * *`| user | find a task by tags | locate details of tasks without having to go through the entire list
`* * *`| user | edit the tasks | updates the changes of the task
`* * *`| user | delete a tasks | remove entries that I no longer need
`* * *`| user | check for time collision  | ensure that no events are stacked
`* * *`| user | redo the previous commands | undo the last changes
`* * *`| user | display the number of tasks completed or left | keep track of my progress
`* * `| new user | set my username | regard the app as my own
`* * `| user | change/add shortcut names  | it is more intuitive
`* * `| user | hide private tasks by default | minimize chance of someone else seeing them by accident
`* * `| user | lock tasklist | prevent unauthorised access
`* `| user | change the task list's theme | personalise the look of the application


## Appendix B : Use Cases

(For all use cases below, the **System** is the `TaskList` and the **Actor** is the `user`, unless specified otherwise)

#### Use case: List tasks

**MSS**

1. User requests to list tasks
2. TaskList shows a list of tasks
Use case ends.

**Extensions**

2a. The list is empty

> Use case ends

#### Use case: Add task

**MSS**

1. User requests to add task
2. TaskList add the task
Use case ends.

**Extensions**

2a. Invalid parameters

 > 2a1.TaskList shows an error message
 > 2a2. Use case ends

#### Use case: Find task

**MSS**

1. User requests to find tasks
2. TaskList shows a list of tasks
Use case ends.

**Extensions**

2a. The list is empty

> Use case ends

#### Use case: Edit task

**MSS**

1. User requests to list tasks
2. TaskList shows a list of tasks
3. User requests to edit a specific task in the list
4. TaskList edit the task
Use case ends.

**Extensions**

2a. The list is empty

> Use case ends

3a. The given index is invalid

> 3a1. TaskList shows an error message <br>
  Use case resumes at step 2

#### Use case: Delete task

**MSS**

1. User requests to list tasks
2. TaskList shows a list of tasks
3. User requests to delete a specific task in the list
4. TaskList deletes the task
Use case ends.

**Extensions**

2a. The list is empty

> Use case ends

3a. The given index is invalid

> 3a1. TaskList shows an error message <br>
  Use case resumes at step 2

## Appendix C : Non Functional Requirements

1. Should work on any [mainstream OS](#mainstream-os) as long as it has Java 8 or higher installed.
2. Should be able to hold up to 1000 tasks.
3. Should come with automated unit tests and open source code.
4. Should favor DOS style commands over Unix-style commands.
5. Should be intuitive for users to use the commands and user interface
6. Should respond to the user in less than 0.5 seconds

## Appendix D : Glossary

##### Mainstream OS

> Windows, Linux, Unix, OS-X

##### Private task details

> A task details that is not meant to be shared with others

## Appendix E : Product Survey

#### Product Name: Todoist
* Description: To Do List and Task Manager
* Product Review:
    * Strengths:
Easy to add, view and organize tasks.
Tasks with deadlines and recurring dates.
Focus on important tasks with priority levels.
    * Weaknesses:
Tasks are not auto sorted by dates, priorities or names.

#### Product Name: Todo.Txt
* Description: Simple to-do list, operated through commandline interface
* Product Review:
    * Strengths:
While simple, the application has 3 main features that make it useful.
Priority: to sort to do list based on relative importance.
Project: to categorise tasks.
Context: to insert comments and additional information about the item.
    * Weaknesses:
Users unfamiliar with commandline tools will be intimidated to use it.
Not too user-friendly as using the application is not intuitive; users may need to read the documentation to fully understand the app.

#### Product Name: Fantastical
* Description: Calendar to-do-list app
* Product Review:
    * Strengths:
Clean design, works well with different types of calendar display.
Group multiple calendars together from multiple devices.
    * Weaknesses:
Some users find the many features too distracting and seldom use them outside of the main ones.


#### Product Name: BusyCal3
* Description: To-do list and calendar
* Product Review:
    * Strengths:
SmartFilter: allows users to customise what tasks to be shown and also by what order of priority
Users can create multiple filters and switch between them
Mini calendar widget
    * Weaknesses:
Some users face integration error between devices.
