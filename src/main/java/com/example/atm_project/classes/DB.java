package com.example.atm_project.classes;

import java.sql.*;
import java.time.LocalDate;


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
            statement2.setDate(6, sqlDate);
            statement2.executeUpdate();
            statement2.close();
            Card card = new Card(cardNumber, PIN, CVV, initialDeposit - 50, name);
            Lists.getCardOwnerMap().put(cardNumber, name);
            Lists.getLoginMap().put(cardNumber, PIN);
            Lists.getCardBalanceMap().put(cardNumber, initialDeposit - 50);
            Lists.getCardCVVMap().put(cardNumber, CVV);
            Lists.getCardExpiryMap().put(cardNumber, expiryDate);
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
            //user info
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
                Lists.getCardExpiryMap().put(cardNumber, expiryDate);
            }
            System.out.println("data received from DB");
        } catch (Exception e) {
            System.out.println("problem reading from DB");
        } finally {
            connection.close();
        }


    }

    public static void changePin(Long cardNumber, int newPIN) throws SQLException {
        try {
            Connection connection = getConnection();
            String sql = "UPDATE cards SET Pin = ? WHERE cardNumber = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, newPIN);
            statement.setLong(2, cardNumber);
            int rows = statement.executeUpdate();
            System.out.println("card pin updated successfully");
        } catch (Exception e) {
            System.out.println("problem updating card PIN");
        }
    }

    public static void decreaseBalance(long cardNumber, int amount) {
        try {
            int currentBalance = Integer.parseInt(Lists.getLoggedInCustomerData().get(1));
            int nextBalance = currentBalance - amount;
            if (currentBalance - amount >= 0) {
                Connection connection = getConnection();
                String sql = "UPDATE cards SET balance = ? WHERE cardNumber = ?";
                PreparedStatement statement = connection.prepareStatement(sql);
                statement.setInt(1, nextBalance);
                statement.setLong(2, cardNumber);
                int rows = statement.executeUpdate();
                System.out.println("balance decreased successfully");
                //locally update balance
                //if it's the same card being used
                if(Long.parseLong(Lists.getLoggedInCustomerData().get(3)) == cardNumber){
                    Lists.getLoggedInCustomerData().set(1, String.valueOf(nextBalance));
                }
                Lists.getCardBalanceMap().put(cardNumber, nextBalance);
            } else
                System.out.println("nextBalance < 0");
        } catch (Exception e) {
            System.out.println("problem updating balance in DB");
        }
    }

    public static void increaseBalance(long cardNumber, int amount) {
        try {
            int currentBalance = Lists.getCardBalanceMap().get(cardNumber);
            int nextBalance = currentBalance + amount;
            Connection connection = getConnection();
            String sql = "UPDATE cards SET balance = ? WHERE cardNumber = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, nextBalance);
            statement.setLong(2, cardNumber);
            int rows = statement.executeUpdate();
            System.out.println("balance increased successfully");
            //locally update balance
            if(Long.parseLong(Lists.getLoggedInCustomerData().get(3)) == cardNumber){
                Lists.getLoggedInCustomerData().set(1, String.valueOf(nextBalance));
            }
            Lists.getCardBalanceMap().put(cardNumber, nextBalance);
            connection.close();
        } catch (Exception e) {
            System.out.println("problem updating balance in DB");
        }
    }

    public static void addTransaction(long from, long to, int amount, String type) {
        try {
            Connection connection = getConnection();
            String sql = "INSERT INTO transactions (`from`, `to`, amount, type) VALUES (?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, from);
            statement.setLong(2, to);
            statement.setInt(3, amount);
            statement.setString(4, type);
            statement.executeUpdate();
            //add locally
            Transaction transaction = new Transaction(from, to, amount, type);
            Lists.getTransactions().add(transaction);
        } catch (Exception e) {
            System.out.println("transaction to DB failed");
        }
    }

    public static void getTransactionData() throws SQLException {
        try {
            Connection connection = getConnection();
            //get transactions
            String sql = "SELECT `from`, `to`, amount, type FROM transactions";
            PreparedStatement statement1 = connection.prepareStatement(sql);
            ResultSet rs = statement1.executeQuery();
            while (rs.next()) {
                long from = rs.getLong("from");
                long to = rs.getLong("to");
                int amount = rs.getInt("amount");
                String type = rs.getString("type");
                Transaction transaction = new Transaction(from, to, amount, type);
                Lists.getTransactions().add(transaction);
            }
            System.out.println("Loaded transactions from DB successfully");
            connection.close();
        } catch (Exception e) {
            System.out.println("Can't Load Transactions");
        }
    }
}


