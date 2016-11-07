# Starting the program




0. Ensure that you have installed Java version `1.8.0_60` or later on your computer.<br>
> This program will not work with earlier versions of java including java 8. 




1. Download the latest version of `Over-Do-It.jar` from the releases tab.




2. Copy the file to your desired folder.




3. Create a new folder, `data`, in your desired folder. Copy the `SampleData.xml` into the `data` folder and rename it to `tasklist.xml` 




4. Double click the file to start the application. The application should run and GUI should appear shortly.<br><br>




# Manual Testing


## HotKeys


Note : run `list` if panels are empty.


<br>
>  <kbd>F1</kbd> : to show Help


>  <kbd>F11</kbd> : to toggle Fullscreen


<br>
## Command


Note : Can only be used if a command has been performed.


> <kbd>Up</kbd> : Show previous command


> <kbd>Down</kbd> : Show previous command


<br>
## Scrolling Left Panel


> <kbd>Home</kbd> : Scroll to top


> <kbd>End</kbd> :  Scroll to end


> <kbd>Page Up</kbd> : Scroll up


> <kbd>Page Down</kbd> : Scroll down


<br>
## Scrolling Right Panel


> <kbd>Ctrl</kbd> +  <kbd>Home</kbd> : Scroll to top


> <kbd>Ctrl</kbd> +  <kbd>End</kbd> : Scroll to end


> <kbd>Ctrl</kbd> +  <kbd>Page Up</kbd> : Scroll up


> <kbd>Ctrl</kbd> +  <kbd>Page Down</kbd> : Scroll down


<br>
## Add Command


<br>
Input : 
* `add`


> Result Message :
>
> Invalid command format! <br>
add: Adds a task to the task list. Parameters: TITLE [d/DESCRIPTION]  [s/DDMMYYYY [HHMM]] [e/DDMMYYYY [HHMM]] [t/TAG]... <br>
Example: <br>
add CS2103 d/Pre tutorial 1 s/15102016 2100 e/15112016 2300 t/urgent <br>
add CS1020 Tutorial d/many questions e/05102016 t/needhelp <br>
add Meeting
> 
> Visual Changes :
>
> Nothing happened.


<br>
Input : 
* `add CS2103 d/Pre tutorial 1 s/15102016 2100 e/15112016 2300 t/urgent`


> Result Message :
>
> New task added: CS2103 Description: Pre tutorial 1 Start: 15-10-2016 2100 End: 15-11-2016 2300 Tags: [urgent]
> 
> Visual Changes :
>
> Left Panel will focus on the task added. It can be overdue depending on date of testing.


<br>
Input : 
* `undo`


> Result Message :
>
> Undo: add CS2103 Description: Pre tutorial 1 Start: 15-10-2016 2100 End: 15-11-2016 2300 Tags: [urgent]
> 
> Visual Changes :
>
> Left panel will remove the task.


<br>
Input : 
* `add CS1020 Tutorial d/many questions e/05102016 t/needhelp`


> Result Message :
>
> New task added: CS1020 Tutorial Description: many questions Start:  End: 05-10-2016 Tags: [needhelp]
> 
> Visual Changes :
>
> Left Panel will focus on the overdue task added. Right panel is updated with the new overdue task.


<br>
Input : 
* `undo`


> Result Message :
>
> Undo: add CS1020 Tutorial Description: many questions Start:  End: 05-10-2016 Tags: [needhelp]
> 
> Visual Changes :
>
> Left panel will remove the task.


<br>
Input : 
* `add Meeting`


> Result Message :
>
> New task added: meeting Description:  Start:  End:  Tags: 
> 
> Visual Changes :
>
> Left Panel will focus on the floating task added.


<br>
Input : 
* `undo`


> Result Message :
>
> Undo: add meeting Description:  Start:  End:  Tags: 
> 
> Visual Changes :
>
> Left panel will remove the task.


## List Command


<br>
Input : 
* `list`


> Result Message :
>
> Listed all tasks.
> 
> Visual Changes :
>
> Left panel will display all tasks (excluding completed tasks).


<br>
Input : 
* `list completed`


> Result Message :
>
> Listed all completed tasks.
> 
> Visual Changes :
>
> Left panel will display all completed tasks


<br>
Input : 
* `list overdue`


> Result Message :
>
> Listed all overdue tasks.
> 
> Visual Changes :
>
> Left panel will display all overdue tasks


<br>
Input : 
* `list floating`


> Result Message :
>
> Listed all floating tasks.
> 
> Visual Changes :
>
> Left panel will display all floating tasks


<br>
Input : 
* `list today` <br>
Note : Depending on the date that the test is done, it can give different results.
 * add TESTTASK1 e/CURRENTDATE

> Result Message :
>
> Great! You have no tasks for today! 
>
> OR
> 
> Listed all tasks for today.
> 
> Visual Changes :
>
> Left panel will list all the tasks for today


<br>
Input : 
* `list week` <br>
Note : Depending on the date that the test is done, it can give different results.
 * add TESTTASK2 e/CURRENTDATE


> Result Message :
>
> Wow! You have no tasks for the week!
>
> OR
> 
> Listed all tasks for the week.
> 
> Visual Changes :
>
> Left panel will list all the tasks for the week


## Find Command


<br>
Input : 
* `find`


> Result Message :
>
> Invalid command format! <br>
find: Finds all tasks whose names/date/description contain any of the specified keywords (case-insensitive) and displays them as a list with index numbers. <br>
Parameters: KEYWORD [MORE_KEYWORDS]... <br>
Example:  <br>
find CS1020 Tutorial <br>
find 15102016 <br>
find CS1010
> 
> Visual Changes :
>
> Nothing happen.


<br>
Input : 
* `find party`


> Result Message :
>
> 1 tasks listed!
> 
> Visual Changes :
>
> Left panel will list all the tasks found


<br>
Input : 
* `find party drama`


> Result Message :
>
> 3 tasks listed!
> 
> Visual Changes :
>
> Left panel will list all the tasks found


## Storage Command


<br>
Input : 
* `storage`


> Result Message :
>
> Invalid command format! <br>
storage: Change the storage file location. <br>
Parameters: FILEPATH <br>
Example: <br>
storage ./data/tasklist.xml <br>
storage ./storagefile.xml
> 
> Visual Changes :
>
> Nothing happened


<br>
Input : 
* `storage ./data/tasklist.xml`


> Result Message :
>
> Storage file location has been updated to ./data/tasklist.xml
> 
> Visual Changes :
>
> Nothing happened


## Edit Command


<br>
Input : 
* `list`
* `edit 1 CS1021 Tutorial`


> Result Message :
>
> Edited task: Download Drama Description: recent drama Start:  End: 14-10-2016 Tags: [needhelp]
> 
> Visual Changes :
>
> Left panel will focus on the edited task.


<br>
Input : 
* `undo`


> Result Message :
>
> Undo: edit CS1021 Tutorial Description: recent drama Start:  End: 14-10-2016 Tags: [needhelp]
> 
> Visual Changes :
>
> Left panel will focus on the edited task.


<br>
Input : 
* `list`
* `edit 1 CS1022 Tutorial s/05102016 e/06102016 t/urgent`


> Result Message :
>
> Edited task: Download Drama Description: recent drama Start:  End: 14-10-2016 Tags: [needhelp]
> 
> Visual Changes :
>
> Left panel will focus on the edited task.


<br>
Input : 
* `undo`


> Result Message :
>
> Undo: edit CS1022 Tutorial Description: recent drama Start: 05-10-2016 End: 06-10-2016 Tags: [urgent]
> 
> Visual Changes :
>
> Left panel will focus on the edited task.


## Delete Command


<br>
Input : 
* `delete`


> Result Message :
>
> Invalid command format! <br>
delete: Deletes the task identified by the index number used in the last task listing.<br>
Parameters: INDEX (must be a positive integer)<br>
Example: <br>
delete 1<br>
delete 2
> 
> Visual Changes :
>
> Nothing happened.


<br>
Input : 
* `list`
* `delete 1`


> Result Message :
>
> Deleted Task: Deleted Task: Download Drama Description: recent drama Start:  End: 14-10-2016 Tags: [needhelp]
> 
> Visual Changes :
>
> Left panel will remove the task.


<br>
Input : 
* `undo`


> Result Message :
>
> Undo: delete Download Drama Description: recent drama Start:  End: 14-10-2016 Tags: [needhelp]
> 
> Visual Changes :
>
> Left panel will add the task back.


## Select Command


<br>
Input : 
* `select`


> Result Message :
>
> Invalid command format! <br>
select: Selects and display the contents of the task identified by the index number used in the last task listing.<br>
Parameters: INDEX (must be a positive integer)<br>
Example: <br>
select 1<br>
select 2
> 
> Visual Changes :
>
> Nothing happened.


<br>
Input : 
* `list`
* `select 1`


> Result Message :
>
> Title: Download Drama <br>
Description: recent drama <br>
Start: <br>
End: 14-10-2016 <br>
Tags: [needhelp] <br>
Completed: false <br>
> 
> Visual Changes :
>
> Nothing happened.


## Mark Command


<br>
Input : 
* `mark`


> Result Message :
>
> Invalid command format! <br>
mark: Marks a task identified by the index number used in the last task listing.<br>
Parameters: INDEX (must be a positive integer)<br>
Example: <br>
mark 1<br>
mark 2
> 
> Visual Changes :
>
> Nothing happened.


<br>
Input : 
* `list`
* `mark 1`


> Result Message :
>
> Task marked: Download Drama Description: recent drama Start:  End: 14-10-2016 Tags: [needhelp]
> 
> Visual Changes :
>
> Left panel remove the task from the list


<br>
Input : 
* `undo`


> Result Message :
>
> Undo: mark Download Drama Description: recent drama Start:  End: 14-10-2016 Tags: [needhelp]
> 
> Visual Changes :
>
> Left panel will add the task back.


## Unmark Command


<br>
Input : 
* `unmark`


> Result Message :
>
> Invalid command format! <br>
unmark: Unmark a task identified by the index number used in the last task listing.<br>
Parameters: INDEX (must be a positive integer)<br>
Example: <br>
unmark 1<br>
unmark 2
> 
> Visual Changes :
>
> Nothing happened.


<br>
Input : 
* `list completed`
* `unmark 3`


> Result Message :
>
> Task unmarked: BuyTextBook Description: For GET1016 Start: 14-10-2016 2100 End: 14-10-2016 2300 Tags: [notUrgent]
> 
> Visual Changes :
>
> Left panel remove the task from the list


<br>
Input : 
* `undo`


> Result Message :
>
> Undo: unmark BuyTextBook Description: For GET1016 Start: 14-10-2016 2100 End: 14-10-2016 2300 Tags: [notUrgent]
> 
> Visual Changes :
>
> Left panel will add the task back.


## Undo Command
Tested with other commands.


## Time Command


<br>
Input : 
* `time`


> Result Message :
>
> Invalid command format! <br>
time: Gives the time remaining before a deadline/ an event.<br>
Parameters: INDEX (must be a positive integer)<br>
Example: <br>
time 1 <br>
time 2
> 
> Visual Changes :
>
> Nothing happened.


<br>
Input : 
* `list overdue`
* `time 1`

> Result Message :
>
> Task is overdue!
> 
> Visual Changes :
>
> Nothing happened.


<br>
Input : 
* `list floating`
* `time 1`

> Result Message :
>
> Task has no date time specification!
> 
> Visual Changes :
>
> Nothing happened.


<br>
Input : 
* `list today`
* `time 1` <br>
Note : Depending on the date that the test is done, there might be no task to time.
 * add TESTTASK3 e/CURRENTDATE


> Result Message :
>
> 0 day(s).
> 
> Visual Changes :
>
> Nothing happened.


<br>
Input : 
* `list week`
* `time 1` <br>
Note : Depending on the date that the test is done, there might be no task to time.
 * add TESTTASK4 e/CURRENTDATE


> Result Message :
>
> X day(s), Y hour(s) left. (where X is the difference from current date to end date, Y is the hours left)
> 
> Visual Changes :
>
> Nothing happened.


## Help Command


<br>
Input : 
* `help`


> Result Message :
>
> Opened help window.
> 
> Visual Changes :
>
> New window will be open, displaying the user guide 


## Clear Command


<br>
Input : 
* `clear`


> Result Message :
>
> Task list has been cleared!
> 
> Visual Changes :
>
> Both left  and right panel will be empty.


## Exit Command


<br>
Input : 
* `exit`


> Visual Changes :
>
> Program will close.
