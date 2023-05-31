package com.example.atm_project.classes;

import javafx.fxml.Initializable;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class DB {
    public static Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/atm", "root", "ConoKimo999@");
        } catch (Exception e) {
            System.out.println("Connection Problem");
        }
        return connection;
    }

    public static void addCustomer(String name, String address, long cardNumber, int PIN, int CVV, int initialDeposit) throws SQLException {
        Connection connection = getConnection();
        String sql = "INSERT INTO customers (address, name, cardNumber) VALUES (?, ?, ?)";
        String sql2 = "INSERT INTO cards (name, cardNumber, PIN, CVV, balance, expiryDate) VALUES (?, ?, ?, ? , ?, ?)";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, address);
            statement.setString(2, name);
            statement.setLong(3, cardNumber);
            statement.executeUpdate();
            statement.close();
            PreparedStatement statement2 = connection.prepareStatement(sql2);
            statement2.setString(1, name);
            statement2.setLong(2, cardNumber);
            statement2.setInt(3, PIN);
            statement2.setInt(4, CVV);
            statement2.setInt(5, initialDeposit - 50);
            LocalDate expiryDate = LocalDate.now().plusYears(5);
            java.sql.Date sqlDate = java.sql.Date.valueOf(expiryDate);
            statement2.setDate(6,sqlDate);
            statement2.executeUpdate();
            statement2.close();
            Customer customer = new Customer(name, address);
            Card card = new Card(cardNumber, PIN, CVV, initialDeposit - 50, name);
            customer.getCards().add(card);
            Lists.getCardOwnerMap().put(cardNumber, name);
            Lists.getLoginMap().put(cardNumber, PIN);
            Lists.getCardBalanceMap().put(cardNumber, initialDeposit - 50);
            Lists.getCardCVVMap().put(cardNumber, CVV);
            Lists.getCardExpiryMap().put(cardNumber,expiryDate);
            System.out.println("insertion into db successful");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            connection.close();
        }


    }

    public static void getData() throws SQLException {
        Connection connection = getConnection();
        try {
            //read from cards
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from cards");
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                Long cardNumber = resultSet.getLong("cardNumber");
                java.sql.Date sqlDate = resultSet.getDate("expiryDate");
                LocalDate expiryDate = sqlDate.toLocalDate();
                int PIN = resultSet.getInt("PIN");
                int CVV = resultSet.getInt("CVV");
                int balance = resultSet.getInt("balance");
                Lists.getCardOwnerMap().put(cardNumber, name);
                Lists.getLoginMap().put(cardNumber, PIN);
                Lists.getCardBalanceMap().put(cardNumber, balance);
                Lists.getCardCVVMap().put(cardNumber, CVV);
                Lists.getCardExpiryMap().put(cardNumber,expiryDate);
            }
            System.out.println("data received from DB");
        } catch (Exception e) {
            System.out.println("problem reading from DB");
        } finally {
            connection.close();
        }


    }
}
