package student_service;

import student_model.Student;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class ReadingCsv {
    //Delimiters used in the CSV file

    private static final String COMMA_DELIMITER = ",";
    private static String url = "jdbc:mysql://localhost:3306/teamdata";
    private static String user = "root";
    private static String pass = "vijay";
     //
    private static Connection connection = null;

    private static PreparedStatement preparedStatement;


    public static void main(String[] args) throws Exception {
        BufferedReader br = null;
        String filename = args[0];
        File f = new File(filename);
        Class.forName("com.mysql.jdbc.Driver");
        connection = DriverManager.getConnection(url, user, pass);

        String query = " INSERT INTO Student_Data VALUES (?,?,?,?,?,?,?) ";
        preparedStatement = connection.prepareStatement(query);




        if (f.exists()) {
            try {
                //Reading the csv file
                br = new BufferedReader(new FileReader(f));

                //Create List for holding Employee objects
                List<Student> studentsList = new ArrayList<Student>();

                String line = "";
                //Read to skip the header

                DateFormat format = new SimpleDateFormat("yyyy-mm-dd");
                //Reading from the second line
                while ((line = br.readLine()) != null) {
                    String[] studentDetails = line.split(COMMA_DELIMITER);
                    //String newLine = "\n";

                    if (studentDetails.length > 0) {
                        //Save the employee details in Employee object

                        Student student = new Student((Integer.parseInt(studentDetails[0])),
                                (studentDetails[1]), (studentDetails[2]),
                                Long.valueOf(studentDetails[3]), studentDetails[4], Double.valueOf(studentDetails[5]), format.parse(studentDetails[6]));
                        studentsList.add(student);
                    }
                }
                //Lets print the Employee List
                for (Student s : studentsList) {
                    System.out.println("##################################################################");
                    System.out.println(s.getRollNo() + "  \n" + s.getName() + "  \n"
                            + s.getGender() + "  \n" + s.getMobileNumber() + " \n" + s.getGmailId() + " \n" + s.getPercentage() + " \n" + s.getDob());
                }

                if (studentsList.isEmpty()) {
                    System.out.println("List is Empty");
                } else {

                    preparedStatement.execute(query);
                }
            } catch (Exception ss) {
                ss.printStackTrace();
            } finally {
                try {
                    br.close();
                } catch (IOException ie) {
                    System.out.println("Error occured while closing the BufferedReader");
                    ie.printStackTrace();
                } catch (java.lang.NumberFormatException nfe) {
                    System.out.println("The Number Format Does Not Matched");
                    nfe.printStackTrace();
                }

            }


        } else {
            System.out.println("File Does Not Exists");
        }
        preparedStatement.close();
        connection.close();

    }


}



