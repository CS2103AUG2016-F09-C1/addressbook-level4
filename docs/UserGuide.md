

<!-- @@author A0146840E -->




# User Guide
<br>




* [Getting Started](#starting-the-program)
* [Introduction](#introduction)
   * [Tasklist](#tasklist)
   * [Task Card Indicator](#task-card-indicator)
* [Hotkeys](#hotkeys)
   * [Main Window](#main-window)
   * [Command](#command)
   * [Interactive Tasklist](#interactive-tasklist)
   * [Overall Tasklist](#overall-tasklist)
* [Commands](#commands)
   * [Help](#viewing-help--help)
   * [Add](#adding-a-task--add)
   * [List](#listing-tasks--list)
   * [Find](#finding-tasks--find)
   * [Storage](#set-storage-file-location--storage)
   * [Edit](#edit-a-task--edit)
   * [Delete](#deleting-a-task--delete)
   * [Select](#select-a-task--select)
   * [Mark](#mark-a-task--mark)
   * [Unmark](#unmark-a-task--unmark)
   * [Undo](#undo-a-task--undo)
   * [Time](#check-the-time-remaining--time)
   * [Clear](#clearing-all-entries--clear)
   * [Exit](#exiting-the-program--exit)
* [FAQ](#faq)
* [Command Summary](#command-summary)






<br>
## Starting the program




0. Ensure that you have installed Java version `1.8.0_60` or later on your computer.<br>
> This program will not work with earlier versions of java including java 8. 




1. Download the latest version of `Over-Do-It.jar` from the releases tab.




2. Copy the file to your desired folder.




3. Double click the file to start the application. The application should run and GUI should appear shortly.<br><br>
<img src="images/Ui.png">


4. Type the command in the command box at the top and press <kbd>Enter</kbd> to execute the command.<br>
Type `help` followed by pressing <kbd>Enter</kbd> will open the help window.


5. Some example commands you can try:
   * **`add`** `CS2103 d/tutorial e/20102016` :
     adds a task named `CS2103` with a description of `tutorial` with a deadline of `20/10/16`.
   * **`delete`**` 1` : deletes the first item on the task list.
   * **`exit`** : exits the app




6. Refer to the [Commands](#commands) section below for details of each command.<br>




<br>
## Introduction




After startup, the application will always show overdue tasks, if any, and tasks for the next 7 days.


<br>
### Tasklist




Interactive tasklist (left panel) and Overall tasklist (right panel) are sorted in chronological order starting from the earliest date.


Sorted in order:


1. Overdue tasks


2. Normal tasks


3. Floating tasks


4. Completed tasks


<br>
### Task Card Indicator




**`Normal Task`** : White




> <img src="images/TaskCardWhite.png" width="300">




**`Completed Task`** : Pale Green




> <img src="images/TaskCardPaleGreen.png" width="300">




**`Floating Task`** : Pale Yellow




> <img src="images/TaskCardPaleYellow.png" width="300">




**`Overdue Task`** :  Pale Red




> <img src="images/TaskCardPaleRed.png" width="300">


<br>
## Hotkeys


<br>
### Main Window




>  <kbd>F1</kbd> : Help


>  <kbd>F11</kbd> : Fullscreen


<br>
### Command




> <kbd>Up</kbd> : Show previous command


> <kbd>Down</kbd> : Show previous command


<br>
### Interactive Tasklist




> <kbd>Home</kbd> : Scroll to top


> <kbd>End</kbd> :  Scroll to end


> <kbd>Page Up</kbd> : Scroll up


> <kbd>Page Down</kbd> : Scroll down


<br>
### Overall Tasklist




> <kbd>Ctrl</kbd> +  <kbd>Home</kbd> : Scroll to top


> <kbd>Ctrl</kbd> +  <kbd>End</kbd> : Scroll to end


> <kbd>Ctrl</kbd> +  <kbd>Page Up</kbd> : Scroll up


> <kbd>Ctrl</kbd> +  <kbd>Page Down</kbd> : Scroll down


<br>
<!-- @@author A0140019W -->
## Commands
> **Command Format**
> * Words in `UPPER_CASE` are the parameters.
> * Items in `SQUARE_BRACKETS` are optional.
> * Items with `...` after them can have multiple instances.
> * Order of parameters are fixed. 


<br>
### Viewing help : `help`
Format: `help`




> Help is also shown if you press <kbd>F1</kbd>
 
<br>
### Adding a task : `add`
If you want to add the a task to the TaskList, here are the format to follow<br>
Format: `add TITLE [d/DETAILS] [s/START DATE TIME] [e/END DATE TIME] [t/TAG]...`


> Title, details and tags are alphanumeric. Date and time are numeric.
>
> Date and time is in 24hr format : `DDMMYY HHMM`
> 
> Tasks can have any number of tags (including 0)
>
> Floating tasks can be added without any date and time




Examples: 
* `add CS1020 Tutorial d/many questions e/05102016 1200  t/needhelp`
* `add Meeting d/for project s/05102016 1200 e/05102016 1400 t/priority1`
* `add CS1010 Take home lab d/hard to do s/05102016 1200`
* `add CS2103 Project d/hard to do`
* `add CS1231 Mid-Term Test`
<br>


### Listing tasks : `list`
This command help you to lists the stipulated tasks in the task list <br>
Format: `list [PARAMETERS]`


> Parameters: completed, overdue, floating, today, week




Example:
* `list’<br>
list all tasks
* `list completed’<br>
list all completed tasks
* `list overdue’<br>
list all overdue tasks
* `list floating’<br>
list all floating tasks
* `list today’<br>
list all tasks for the day
* `list week’<br>
list all tasks for the week


<br>
### Finding tasks : `find`
If you want to find tasks whose title, description, date, time and tags contain any of the information you want to search for.<br>
Format: `find KEYWORD [MORE_KEYWORDS]`




> The search is case insensitive, the order of the keywords does not matter, only the title, description, date, time and tags are searched and tasks matching at least one keyword will be returned (i.e. `OR` search).




Examples: 
* `find cs2103`<br>
  Display tasks containing `CS2103` and `cs2103`
* `find cs1010 15 2016`<br>
  Display tasks containing `cs1010`, `15`, or `2016`
* `find lab`<br>
  Display tasks containing `lab`.


<br>
### Set storage file location : `storage`
Saving the TaskList to anywhere you want. <br>
Format: `storage FILEPATH`




> Task list data are saved in a file called `data\tasklist.xml` in the root folder by default.
> 
> The file name must end in `.xml` for it to be acceptable to the program.
>
> `Note`: The previous storage file will be deleted.
> 
> Irreversible with `undo`


Example: 
* `storage \folder\file.xml` <br>
  Storage file location has been changed to `\folder\file.xml`.
* `storage \data\newtasklist.xml` <br>
  Storage file location has been changed to `\data\newtasklist.xml`.


<br>
### Edit a task : `edit`
Edit any information you want to edit from the task <br>
Format: `edit INDEX [TITLE] [d/DETAILS] [s/STARTTIME] [e/ENDTIME]`




> Edit the task at the specified `INDEX`.  <br>
> The index refers to the index number shown in the most recent listing.<br>
> The index **must be a positive integer** 1, 2, 3, ...




Examples:
* `list`<br>
  `edit 1 d/new updates`<br>
  Edits the 1st task in the task list, changing the task description to `new updates`.
* `list floating`<br>
  `edit 2 d/new updates e/051016 1200`<br>
  Edits the 2nd floating task in the task list, changing the task description to `new updates` and end date and time to `051016 1200`.


<br>
### Deleting a task : `delete`
Delete any task if you is not needed or not important<br>
Format: `delete INDEX`




> Deletes the task at the specified `INDEX`. 
> The index refers to the index number shown in the most recent listing.
> The index **must be a positive integer** 1, 2, 3, ...




Examples: 
* `list`<br>
  `delete 2`<br>
  Deletes the 2nd task in the task list.
* `find cs2103`<br> 
  `delete 1`<br>
  Deletes the 1st task in the results of the `find` command.


<br>
### Select a task : `select`
Selects the task identified by the index number used in the last task listing.<br>
Format: `select INDEX`




> Select and display the information of the task. <br>
> The index refers to the index number shown in the most recent listing.<br>
> The index **must be a positive integer** 1, 2, 3, ...




Examples: 
* `list`<br>
  `select 2`<br>
  Selects the 2nd task in the task list.
* `find CS2103` <br> 
  `select 1`<br>
  Selects the 1st task in the results of the `find` command.


<br>
<!-- @@author A0138516A -->
### Mark a task : `mark`
You can mark the task as completed at the specified ‘INDEX’.<br>
Format: `mark INDEX`


> Mark the task as completed. <br>
> The index refers to the index number shown in the most recent listing.<br>
> The index **must be a positive integer** 1, 2, 3, ...


Examples: 
* `list`<br>
  `mark 2`<br>
  Marks the 2nd task in the task list.
* `find CS2103` <br> 
  `mark 1`<br>
  Marks the 1st task in the results of the `find` command.


<br>
### Unmark a task : `unmark`
You can unmark the task as not completed at the specified ‘INDEX’.<br>
Format: `unmark INDEX`


> Mark the task as not completed. <br>
> The index refers to the index number shown in the most recent listing.<br>
> The index **must be a positive integer** 1, 2, 3, ...


Examples: 
* `list`<br>
  `unmark 2`<br>
  Unmarks the 2nd task in the task list.
* `find Homework 2` <br> 
  `unmark 1`<br>
  Unmarks the 1st task in the results of the `find` command.


<br>
### Undo a task : `undo`
You can undo the previous command if you want.<br>
Format: `undo`




> `Note`: Does not work on clear and storage command. <br>




Examples: 
* `mark 1`<br>
  `undo`<br>
  Undo the previous command, and the first task will be unmark.
* `add CS2103` <br> 
  `undo`<br>
  Undo the previous command, added task will be removed.


<br>
 <!-- @@author A0153837X -->
### Check the time remaining : `time`
Gives the time remaining for a task (in days and hours) at the specified ‘INDEX’.<br>
Format: `time INDEX`
 
 
 > Gives the time remaining to the deadline/ event <br>
 > The index refers to the index number shown in the most recent listing.<br>
 > The index **must be a positive integer** 1, 2, 3, ...
 
 
 Examples:
 * `list`<br>
   `time 1`<br>
   16 day(s), 5 hour(s) left.
 * `list` <br>
   `time 2`<br>
   Task is overdue!


  <!-- @@author A0138516A -->


<br>
### Clearing all entries : `clear`
Have a  fresh start with everything cleared<br>
Format: `clear`  




> `Note`: Clearing all task will remove all entries in the task list
> 
> Irreversible with `undo`


<br>
### Exiting the program : `exit`
Exits the program.<br>
Format: `exit`  




> `Note`: The application will close.




<br>
## FAQ
<br>

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with 
       the file that contains the data of your previous Address Book folder.




**Q**: Do i need to manually save the data?<br>
**A**: Task List ‘s data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.




<br>   
## Command Summary



<br>
**Add** : `add TITLE [d/DETAILS] [s/START DATE TIME] [e/END DATE TIME] [t/TAG]...`
<br>




Examples: 
  * `add CS1020 Tutorial d/many questions e/05102016 1200  t/needhelp`
  * `add Meeting d/for project s/05102016 1200 e/05102016 1400 t/priority1`
  * `add CS1010 Take home lab d/hard to do s/05102016 1200`
  * `add CS2103 Project d/hard to do`
  * `add CS1231 Mid-Term Test`




<br>
**List** : `list PARAMETERS`
<br>




Examples: 
  * `list’<br>
  list all tasks
  * `list completed’<br>
  list all completed tasks
  * `list overdue’<br>
  list all overdue tasks
  * `list floating’<br>
  list all floating tasks
  * `list today’<br>
  list all tasks for the day
  * `list week’<br>
  list all tasks for the week




<br>
**Find** : `find KEYWORD [MORE_KEYWORDS]`
<br>




Examples: 
  * `find cs2103`<br>
  Display tasks containing `CS2103` and `cs2103`
  * `find cs1010 15 2016`<br>
  Display tasks containing `cs1010`, `15`, or `2016`
  * `find lab`<br>
  Display tasks containing `lab`.




<br>
**Storage** : `storage FILEPATH`
<br>




Examples: 
  * `storage \folder\file.xml` <br>
  Storage file location has been changed to `\folder\file.xml`.
  * `storage \data\newtasklist.xml` <br>
  Storage file location has been changed to `\data\newtasklist.xml`.




<br>
**Edit** : `edit INDEX [TITLE] [d/DETAILS] [s/STARTTIME] [e/ENDTIME]`
<br>




Examples: 
  * `list`<br>
  `edit 1 d/new updates`<br>
  Edits the 1st task in the task list, changing the task description to `new updates`.
  * `list floating`<br>
  `edit 2 d/new updates e/051016 1200`<br>
  Edits the 2nd floating task in the task list, changing the task description to `new updates` and end date and time to `051016 1200`.




<br>
**Delete** : `delete INDEX`
<br>




Examples: 
  *  `list`<br>
  `delete 2`<br>
  Deletes the 2nd task in the task list.
  * `find cs2103`<br> 
  `delete 1`<br>
  Deletes the 1st task in the results of the `find` command.




<br>
**Select** : `select INDEX`
<br>




Examples: 
  * `list`<br>
  `select 2`<br>
  Selects the 2nd task in the task list.
  * `find CS2103` <br> 
  `select 1`<br>
  Selects the 1st task in the results of the `find` command.




<br>
**Mark** : `mark INDEX`
<br>




Examples: 
  * `list`<br>
  `mark 2`<br>
  Marks the 2nd task in the task list.
  * `find CS2103` <br> 
  `mark 1`<br>
  Marks the 1st task in the results of the `find` command.




<br>
**Unmark** : `unmark INDEX`
<br>




Examples: 
  * `list`<br>
  `unmark 2`<br>
  Unmarks the 2nd task in the task list.
  * `find Homework 2` <br> 
  `unmark 1`<br>
  Unmarks the 1st task in the results of the `find` command.


<br>
**Undo** : `undo`
<br>




Examples: 
* `mark 1`<br>
  `undo`<br>
  Undo the previous command, and the first task will be unmark.
* `add CS2103` <br> 
  `undo`<br>
  Undo the previous command, added task will be removed.


<br>
 <!-- @@author A0153837X -->
**Time** : `time`
 <br>




 Examples:
 * `list`<br>
   `time 1`<br>
   16 day(s), 5 hour(s) left.
 * `list` <br>
   `time 2`<br>
   Task is overdue!
 <br>
  <!-- @@author A0138516A -->


**Clear** : `clear`




<br>
**Exit** : `exit`
`


