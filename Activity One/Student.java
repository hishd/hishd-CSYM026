public class Student {
    private String id;
    private Name name;
    private String quals;
    private int year;

    public Student(String id, Name name, String quals, int year) {
        this.id = id;
        this.name = name;
        this.quals = quals;
        this.year = year;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Name getName() {
        return name;
    }

    public void setName(Name name) {
        this.name = name;
    }

    public String getQuals() {
        return quals;
    }

    public void setQuals(String quals) {
        this.quals = quals;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

}
