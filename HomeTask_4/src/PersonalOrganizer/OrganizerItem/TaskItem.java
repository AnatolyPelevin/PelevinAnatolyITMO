package PersonalOrganizer.OrganizerItem;

import java.util.Date;
import java.util.Hashtable;

public class TaskItem implements OrganizerItemInterface {
    private String taskItemTitle;
    private static int count;
    private NoteItem noteItem;
    private Date taskDatedToDo;
    private Hashtable<ContactItem, String> contacts;

    public TaskItem (){
        this.taskItemTitle = "New Task" + count;
        this.taskDatedToDo = new Date();
        count++;
    }

    public TaskItem (String taskItemTitle, Date taskDatedToDo){
        this.taskItemTitle = taskItemTitle;
        this.taskDatedToDo = taskDatedToDo;
    }

    public NoteItem getNoteItem() {
        return noteItem;
    }

    public Date getTaskDatedToDo() {
        return taskDatedToDo;
    }

    public void setTaskDatedToDo(Date taskDatedToDo) {
        this.taskDatedToDo = taskDatedToDo;
    }

    public void setNoteItem(NoteItem noteItem) {
        this.noteItem = noteItem;
    }

    public Hashtable<ContactItem, String> getContacts() {
        return contacts;
    }

    public void setContacts(Hashtable<ContactItem, String> contacts) {
        this.contacts = contacts;
    }

    public void setContact(ContactItem contactItem, String describe) {
        contacts.put(contactItem, describe);
    }

    @Override
    public void setItemTitle(String itemTitle) {
        if (itemTitle ==null || "".equals(itemTitle)) {
            this.taskItemTitle = "New Task" + count;
            count++;
        } else{
            this.taskItemTitle =  itemTitle;
        }
    }

    @Override
    public String getItemTitle() {
        return taskItemTitle;
    }
}
