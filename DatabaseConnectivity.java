import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;



public class DatabaseConnectivity {

    public static void main(String args[]) throws Exception
    {



        //String filename = args[0];
        //File f =new File(filename);
        String url = "jdbc:mysql://localhost:3306/teamdata";
        String user = "root";
        String pass = "vijay";
        //String query = "select * from Student_Data";
        String query = query = "LOAD DATA LOCAL INFILE '"+ "/home/cvt002/Documents/data.csv" +"' INTO TABLE Student_Data  FIELDS TERMINATED BY ',' (RollNo,Name,Gender,MobileNumber,GmailId,Percentage,DOB)";
        //String query = " INSERT INTO members VALUES (10,'KRISHNA',3,'ALL') ";



        Class.forName("com.mysql.jdbc.Driver"); //
        Connection connection = DriverManager.getConnection(url,user,pass);
        Statement st = connection.createStatement();
        st.execute(query);
        try( ResultSet rs = st.getResultSet()){

            rs.next();
            String data = " ";
            while (rs.next()) {

                data = rs.getInt(1) + " : "
                        + rs.getString(2) + " : " + rs.getInt(3) + " : " + rs.getString(4) + " : " + rs.getString(5) + " : " + rs.getDouble(6) + " : " + rs.getDate(7);
                System.out.println(data);
            }  //DDL,DML,DQL
        }


        //System.out.println(count + " row/s affected");




        st.close();
        connection.close();
    }
}