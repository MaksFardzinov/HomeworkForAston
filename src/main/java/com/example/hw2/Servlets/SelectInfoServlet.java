package com.example.hw2.Servlets;

import com.example.hw2.dataBase.DataBaseUtils;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

@WebServlet("/SelectDetails")
public class SelectInfoServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        DataBaseUtils db = new DataBaseUtils();
        try {
            Class.forName(DataBaseUtils.driver);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        try{
            String name="";
            String lastname="";
            int group=0;
            String examName="";
            Connection connection = DriverManager.getConnection(db.getUrl(),db.getUser(),db.getPassword());

            System.out.println("successful");
            PreparedStatement st = connection.prepareStatement("select student.name,student.lastname,student.group_number,exam.exam_name\n" +
                    "                    from student\n" +
                    "                    JOIN exam_instance on student.id = exam_instance.student_id\n" +
                    "                    JOIN exam ON exam_instance.exam_id = exam.exam_id where student.id=?");
            st.setInt(1, Integer.parseInt(request.getParameter("id")));
            ResultSet result = st.executeQuery();
            while (result.next()) {
                name = result.getString(1);
                lastname = result.getString(2);
                group = result.getInt(3);
                examName = result.getString(4);
            }
            PrintWriter out = response.getWriter();
            out.println("<html><body>");
                out.println("<h1>" +name + "</h1>");
                out.println("<h1>" +lastname+ "</h1>");
                out.println("<h1>" +group + "</h1>");
                out.println("<h1>" +examName + "</h1>");
            out.println("</body></html>");
            st.close();
            result.close();
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
