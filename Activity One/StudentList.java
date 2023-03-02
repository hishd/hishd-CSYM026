import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

// using an ArrayList collect many students
public class StudentList {

    private ArrayList<Student> studentList;

    // create an empty arraylist
    public StudentList() {
        // your code goes here
        studentList = new ArrayList<>();
    }

    // add a student s to the collection
    public void add(Student s) {
        // your code goes here
        studentList.add(s);
    }

    // returns a report with one line per person
    // traverses the array list,
    // getting one element at a time
    public String getAllStudents() {
        // your code goes here
        // use for each loop
        StringBuilder studentRecords = new StringBuilder();
        studentList.forEach(student -> {
            studentRecords.append(
                    String.format(
                            "%s;%s;%d;%s\n", student.getId(), student.getName().getFullName(), student.getYear(),
                            student.getQuals()));
        });

        return studentRecords.toString();
    }

    // returns the number of elements in the list
    public int getSize() {
        return studentList.size();
    }

    // returns the Student object at specified index position
    public Student getAtIndex(int index) {
        // your code goes here
        return studentList.get(index);
    }

    // returns the Student object with a specified id
    // searches through the array
    // and stopping by returning when a match is found
    public Student findById(String id) {
        // your code goes here
        return studentList.stream().filter(item -> item.getId() == id).findFirst().orElse(null);
    }

    // counts the number of people in a specified year
    // demonstrates making a count with arraylists
    public int getCountOfPeopleAtYear(int year) {
        // your code goes here
        return (int) studentList.stream().filter(item -> item.getYear() == year).count();
    }

    // works out how many people in each year,
    // then creates and returns a report
    //
    // demonstrates calculating a frequency report
    // i.e. how often each year occurs
    // it uses the value of the year as an index
    public String getYearsFrequencyReport() {
        // work out max year
        // work out how many people at each year
        // your code goes here
        Map<Integer, List<Student>> groupedData = studentList.stream().collect(Collectors.groupingBy(Student::getYear));
        StringBuilder report = new StringBuilder();
        groupedData.forEach((key, value) -> {
            report.append(String.format("Year: %s, Count: %d", key, value.size()));
        });
        return report.toString();
    }

    // calculates the maximum year that anyone is in
    // demonstrates finding a max with array lists
    public int getMaxYear() {
        // your code here
        Student s = studentList.stream().max(Comparator.comparing(student -> student.getYear())).get();
        return s.getYear();
    }

    /**
     * writes supplied text to file
     * 
     * @param filename the name of the file to be written to
     * @param report   the text to be written to the file
     * @throws IOException
     */
    public void writeToFile(String fileName, String report) throws IOException {

        FileWriter fw = new FileWriter(fileName, true);
        // your code here
        // catch the following exceptions FileNotFound, and IOException
        BufferedWriter bw = new BufferedWriter(fw);
        bw.append(report);
        bw.close();
    }

    /**
     * reads file with given name, extracting student data, creating student objects
     * and adding them to the list of students
     * Blank lines are skipped
     * Validation for integer year, missing items
     * 
     * @param filename the name of the input file
     * @throws IOException
     */
    public void readFile(String filename) throws IOException {
        // your code here
        BufferedReader bufferedReader = new BufferedReader(new FileReader(filename));
        bufferedReader.lines().forEach(line -> {
            processLine(line);
        });
        bufferedReader.close();
        System.out.println(getAllStudents());
    }

    /**
     * Processes line, extracts data, creates Student object
     * and adds to list
     * Checks for non-numeric year and missing items
     * Will still crash if name entered without a space
     * 
     * @param line - the line to be processed
     */
    private void processLine(String line) {
        try {
            String parts[] = line.split(";");
            Name name = new Name(parts[1]);
            String id = parts[0];
            String yearNum = parts[2];
            yearNum = yearNum.trim(); // remove any spaces
            int year = Integer.parseInt(yearNum);

            // the qualifications are at the end of the line
            int qualLength = parts.length - 3;
            String quals[] = new String[qualLength];
            System.arraycopy(parts, 3, quals, 0, qualLength);

            // create Student object and add to the list
            Student s = new Student(id, name, quals.toString(), year);
            this.add(s);
        }

        // for these two formatting errors, ignore lines in error and try and carry on

        // this catches trying to convert a String to an integer
        catch (NumberFormatException nfe) {
            String error = "Number conversion error in '" + line + "'                 - "
                    + nfe.getMessage();
            System.out.println(error);
        }
        // this catches missing items if only one or two items
        // other omissions will result in other errors
        catch (ArrayIndexOutOfBoundsException air) {
            String error = "Not enough items in  : '" + line
                    + "' index position : " + air.getMessage();
            System.out.println(error);
        }
    }
}