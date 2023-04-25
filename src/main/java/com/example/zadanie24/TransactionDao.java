package com.example.zadanie24;

import java.sql.*;

public class TransactionDao {

    private Connection connection;

    TransactionDao() {
        try {
            this.connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/householdfinance",
                    "root", "jakiesHaslo");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    void addTransaction(Transaction transaction) {
        try {
            String sql = "insert into transaction (type, description, amount, date) values (?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, transaction.getType().getPlDescription());
            preparedStatement.setString(2, transaction.getDescription());
            preparedStatement.setDouble(3, transaction.getAmount());
            preparedStatement.setString(4, transaction.getDate());
            int numberOfAddedTransactions = preparedStatement.executeUpdate();
            System.out.println("Zapisane transakcje: " + numberOfAddedTransactions);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    void updateTransaction(Transaction transaction) {
        try {
            String sql = "update transaction set type = ?, description = ?, amount = ?, date = ? where id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, transaction.getType().getPlDescription());
            preparedStatement.setString(2, transaction.getDescription());
            preparedStatement.setDouble(3, transaction.getAmount());
            preparedStatement.setString(4, transaction.getDate());
            preparedStatement.setInt(5, transaction.getId());
            int numberOfUpdatedTransactions = preparedStatement.executeUpdate();
            System.out.println("Zmodyfikowano transakcje: " + numberOfUpdatedTransactions);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    void deleteTransaction(int id) {
        try {
            String sql = "delete from transaction where id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            int numberOfDeletedTransactions = preparedStatement.executeUpdate();
            System.out.println("Usunięto transakcje: " + numberOfDeletedTransactions);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    void printTransactionOfType(Type type) {
        try {
            String sql = "select * from transaction where type = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, type.getPlDescription());
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String description = resultSet.getString("description");
                double amount = resultSet.getDouble("amount");
                String date = resultSet.getString("date");
                Transaction transaction = new Transaction(id, type, description, amount, date);
                System.out.println(transaction);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
