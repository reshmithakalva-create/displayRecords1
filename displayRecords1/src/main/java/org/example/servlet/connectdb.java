
package org.example.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

@WebServlet("/display")
public class connectdb extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();

        try {

            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://127.0.0.1:3307/login_schema",
                    "root",
                    "12345"
            );

            Statement st = con.createStatement();

            ResultSet rs = st.executeQuery(
                    "SELECT idusers, username, pasword FROM users"
            );

            out.println("<html>");
            out.println("<head>");
            out.println("<title>Users</title>");
            out.println("</head>");

            out.println("<body>");

            out.println("<h2>Users Details</h2>");

            out.println("<table border='1'>");

            out.println("<tr>");
            out.println("<th>ID</th>");
            out.println("<th>Username</th>");
            out.println("<th>Password</th>");
            out.println("</tr>");

            while (rs.next()) {

                out.println("<tr>");

                out.println("<td>");
                out.println(rs.getInt("idusers"));
                out.println("</td>");

                out.println("<td>");
                out.println(rs.getString("username"));
                out.println("</td>");

                out.println("<td>");
                out.println(rs.getString("pasword"));
                out.println("</td>");

                out.println("</tr>");
            }

            out.println("</table>");

            out.println("</body>");
            out.println("</html>");

            rs.close();
            st.close();
            con.close();

        } catch (Exception e) {

            out.println("<h2>Error</h2>");
            out.println("<pre>");

            e.printStackTrace(out);

            out.println("</pre>");
        }
    }
}
