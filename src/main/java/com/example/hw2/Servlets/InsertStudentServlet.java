package com.example.hw2.Servlets;

import com.example.hw2.dataBase.DataBaseUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@WebServlet("/InsertStudentServlet")
public class InsertStudentServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        DataBaseUtils db = new DataBaseUtils();
        try {
            Class.forName(DataBaseUtils.driver);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        try {
            Connection connection = DriverManager.getConnection(db.getUrl(), db.getUser(), db.getPassword());
            System.out.println("successful");
            PreparedStatement st = connection.prepareStatement("insert into Student(name, lastname, scores,group_number) values( ?, ?, ?,?)");
            st.setString(1, request.getParameter("name"));
            st.setString(2, request.getParameter("lastname"));
            st.setInt(3, Integer.parseInt(request.getParameter("scores")));
            st.setInt(4, Integer.parseInt(request.getParameter("group_number")));
            st.executeUpdate();
            st.close();
            connection.close();
            response.sendRedirect(request.getParameter("name"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
