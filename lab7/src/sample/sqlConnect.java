package sample;

import java.sql.*;
import java.sql.Connection;
import java.io.*;
import java.util.ArrayList;

public class sqlConnect {

   private Connection conn = null;
    private Statement statement = null;
    private String url = "jdbc:sqlite:./lab7_DB.db";


    public sqlConnect() throws ClassNotFoundException, SQLException {
        Class.forName("org.sqlite.JDBC");
        conn = DriverManager.getConnection(url);
    }

    public ArrayList<client> selectClient()
    {
        String sql = "SELECT id_client, first_name_client, last_name_client FROM Client";
        ArrayList<client> ret = new ArrayList<client>();

        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                ret.add(new client(rs.getInt("clientNumber"),rs.getString("firstName"),rs.getString("lastName")));
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return ret;
    }
    public void addClient(String first_name_client, String last_name_client)
    {
        String sql = "INSERT INTO Client(first_name_client,last_name_client) VALUES(?,?)";

        try{
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, first_name_client);
            pstmt.setString(2, last_name_client);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public void removeClient(int client_id)
    {
        String sql = "DELETE FROM Client WHERE client_id = ?";

        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, client_id);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public ArrayList<payment> selectPayment()
    {
        String sql = "SELECT id_payment, payment_date, amount, id_client FROM Payment";
        ArrayList<payment> ret = new ArrayList<payment>();

        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                ret.add(new payment(rs.getInt("paymentID"),rs.getString("paymentDate"),rs.getInt("amount"),rs.getInt("clientID")));
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return ret;
    }
    public ArrayList<recive> selectRecive()
    {
        String sql = "SELECT id_recivables, pay_to, amount_to_pay, id_client FROM Receivables";
        ArrayList<recive> ret = new ArrayList<recive>();

        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                ret.add(new recive(rs.getInt("reciveID"),rs.getString("payTo"),rs.getInt("toPay"),rs.getInt("clientID")));
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return ret;
    }

}

