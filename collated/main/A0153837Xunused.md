# A0153837Xunused
###### \Model.java
``` java
    //Not used due to list command
    /** Show all the tasks that is due today **/
    void updateFilteredTaskListToday();

}
```
###### \ModelManager.java
``` java
    //Not used due to list command
    @Override
    public void updateFilteredTaskListToday(){
        updateFilteredTaskList(new PredicateExpression(new TodayQualifier()));
    }

```
###### \ModelManager.java
``` java
    //Not used due to list command
    private class TodayQualifier implements Qualifier {

		@Override
		public boolean run(ReadOnlyTask task) {
			return task.isToday();
		}
    }

}
```
###### \Task.java
``` java
    //Not used due to list command
    public boolean isToday() {
    	if(this.getEndDateTime().getDate().getLocalDate() != null){
        	return (this.getEndDateTime().getDate().getLocalDate().isEqual(LocalDate.now()));
    	}
    	return false;
    }

}
```
###### \TodayCommand.java
``` java
//Not used due to list command
public class TodayCommand extends Command{
	public static final String COMMAND_WORD = "today";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all tasks that need to be done by today";

    public static final String MESSAGE_SUCCESS = "Listed all tasks due today";
	@Override
	public Command prepare(String args) {
		return new TodayCommand();
	}

	@Override
	public CommandResult execute() {
		model.updateFilteredTaskListToday();
        return new CommandResult(MESSAGE_SUCCESS);
	}

}
```
