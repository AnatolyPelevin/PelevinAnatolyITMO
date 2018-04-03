package PersonalOrganizer.OrganizerItem;

public class NoteItem implements OrganizerItemInterface {
    private String noteText;
    private String noteTitle;
    private Boolean isFavourite;
    private static int count;


    public NoteItem(){
        this.noteTitle = "New Note " + count;
        count++;
    }

    public NoteItem(String noteText, String noteTitle){
        this.noteText = noteText;
        this.noteTitle = noteTitle;
    }

    public NoteItem(String noteText, String noteTitle, Boolean isFavourite){
        this(noteText, noteTitle);
        this.isFavourite = isFavourite;
    }

    public String getNoteText() {
        return noteText;
    }

    public void setNoteText(String noteText) {
        this.noteText = noteText;
    }
    @Override
    public String getItemTitle() {
        return noteTitle;
    }
    @Override
    public void setItemTitle(String noteTitle) {
        if (noteTitle ==null || "".equals(noteTitle)) {
            this.noteTitle = "New Note" + count;
            count++;
        } else{
            this.noteTitle =  noteTitle;
        }
    }

    public Boolean getFavourite() {
        return isFavourite;
    }

    public void setFavourite(Boolean favourite) {
        isFavourite = favourite;
    }

}
