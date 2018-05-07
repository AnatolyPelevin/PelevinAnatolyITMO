public class TestClass {
    private String privateFieldStr_1;
    @ExcludeFieldAnnotation
    private String privateFieldStr_2;
    public String publicFieldStr_1;
    @ExcludeFieldAnnotation
    public String publicFieldStr_2;

    private int privateFieldInt_1;
    @ExcludeFieldAnnotation
    private int privateFieldInt_2;
    public int publicFieldInt_1;
    @ExcludeFieldAnnotation
    public int publicFieldInt_2;


    public  TestClass(){
        this.privateFieldStr_1 = "private string 1";
        this.privateFieldStr_2 = "private string 2";
        this.publicFieldStr_1 = "public string 1";
        this.publicFieldStr_2 = "public string 2";

        this.privateFieldInt_1 = 1;
        this.privateFieldInt_2 = 2;
        this.publicFieldInt_1 = 3;
        this.publicFieldInt_2 = 4;
    }


    public  TestClass(String privateFieldStr_1, String privateFieldStr_2, String publicFieldStr_1, String publicFieldStr_2
                           ,int privateFieldInt_1, int privateFieldInt_2,  int publicFieldInt_1, int publicFieldInt_2){
        this.privateFieldStr_1 = privateFieldStr_1;
        this.privateFieldStr_2 = privateFieldStr_2;
        this.publicFieldStr_1  = publicFieldStr_1;
        this.publicFieldStr_2  = publicFieldStr_2;

        this.privateFieldInt_1  = privateFieldInt_1;
        this.privateFieldInt_2  = privateFieldInt_2;
        this.publicFieldInt_1  = publicFieldInt_1;
        this.publicFieldInt_2  = publicFieldInt_2;
    }


    public String getPrivateFieldStr_1() {
        return privateFieldStr_1;
    }

    public void setPrivateFieldStr_1(String privateFieldStr_1) {
        this.privateFieldStr_1 = privateFieldStr_1;
    }

    public String getPrivateFieldStr_2() {
        return privateFieldStr_2;
    }

    public void setPrivateFieldStr_2(String privateFieldStr_2) {
        this.privateFieldStr_2 = privateFieldStr_2;
    }

    public String getPublicFieldStr_1() {
        return publicFieldStr_1;
    }

    public void setPublicFieldStr_1(String publicFieldStr_1) {
        this.publicFieldStr_1 = publicFieldStr_1;
    }

    public String getPublicFieldStr_2() {
        return publicFieldStr_2;
    }

    public void setPublicFieldStr_2(String publicFieldStr_2) {
        this.publicFieldStr_2 = publicFieldStr_2;
    }

    public int getPrivateFieldInt_1() {
        return privateFieldInt_1;
    }

    public void setPrivateFieldInt_1(int privateFieldInt_1) {
        this.privateFieldInt_1 = privateFieldInt_1;
    }

    public int getPrivateFieldInt_2() {
        return privateFieldInt_2;
    }

    public void setPrivateFieldInt_2(int privateFieldInt_2) {
        this.privateFieldInt_2 = privateFieldInt_2;
    }

    public int getPublicFieldInt_1() {
        return publicFieldInt_1;
    }

    public void setPublicFieldInt_1(int publicFieldInt_1) {
        this.publicFieldInt_1 = publicFieldInt_1;
    }

    private int getPublicFieldInt_2() {
        return publicFieldInt_2;
    }

    private void setPublicFieldInt_2(int publicFieldInt_2) {
        this.publicFieldInt_2 = publicFieldInt_2;
    }


}
