class ActivityOne {
    public static void main(String[] args) {
        StudentList studentList = new StudentList();
        try {
            final String fileName = "studentdata.txt";
            studentList.add(new Student("101", new Name("Hishara"), "MSc, BSc, HND, Dip", 2020));
            studentList.add(new Student("102", new Name("Imasha"), "MSc, BSc, HND, Dip", 2020));
            studentList.writeToFile(fileName, studentList.getAllStudents());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}