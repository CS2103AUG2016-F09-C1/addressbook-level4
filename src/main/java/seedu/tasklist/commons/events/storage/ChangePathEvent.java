package seedu.tasklist.commons.events.storage;

import seedu.tasklist.commons.events.BaseEvent;

//@@author A0138516A

/** Indicates the file path in the storage has changed*/
public class ChangePathEvent extends BaseEvent{

	private String filepath;
	
	
	public ChangePathEvent(String filepath){
		this.filepath = filepath;
	}
	
	@Override
	public String toString() {
		return this.filepath;
	}

}
