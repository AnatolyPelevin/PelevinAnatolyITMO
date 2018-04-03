package PersonalOrganizer.OrganizerItem;

public class OrganizerItemFactory {

    public static OrganizerItemInterface createOrganizerItem(OrganizerItemType organizerItemType){
        switch (organizerItemType) {
            case NOTE:
                return new NoteItem();
            case CONTACT:
                return new ContactItem();
            case PASSWORD:
                return new PasswordItem();
            case TASK:
                return new TaskItem();
            default:
                return null;

        }
    }
}

