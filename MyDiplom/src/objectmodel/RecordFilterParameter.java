package objectmodel;


public class RecordFilterParameter {
    private int parameterId;
    private String name;
    private String value;
    private RwFile file;

    public int getParameterId() {
        return parameterId;
    }

    public void setParameterId(int parameterId) {
        this.parameterId = parameterId;
    }

     public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public RwFile getFile() {
        return file;
    }

    public void setFile(RwFile file) {
        this.file = file;
    }
}
