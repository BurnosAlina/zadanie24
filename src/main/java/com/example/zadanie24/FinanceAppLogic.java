package com.example.zadanie24;

import java.sql.Date;
import java.util.List;
import java.util.Scanner;

public class FinanceAppLogic {

    private static final int EXIT = 0;
    private static final int ADD = 1;
    private static final int UPDATE = 2;
    private static final int DELETE = 3;
    private static final int PRINT_ALL_INCOMES = 4;
    private static final int PRINT_ALL_EXPENSES = 5;

    private Scanner scanner = new Scanner(System.in);

    void runApp() {
        TransactionDao transactionDao = new TransactionDao();
        int option;
        do {
            option = printAndGetOption();
            switch (option) {
                case ADD -> {
                    try {
                        Transaction transaction = create();
                        transactionDao.addTransaction(transaction);
                    } catch (NotMatchingDescriptionException | IncorrectDateFormatException e) {
                        System.out.println(e.getMessage());
                    }
                }
                case UPDATE -> {
                    try {
                        Transaction updatedTransaction = update();
                        transactionDao.updateTransaction(updatedTransaction);
                    } catch (NotMatchingDescriptionException | IncorrectDateFormatException e) {
                        System.out.println(e.getMessage());
                    }
                }
                case DELETE -> {
                    int id = getTransactionIdToDelete();
                    transactionDao.deleteTransaction(id);
                }
                case PRINT_ALL_INCOMES -> {
                    List<Transaction> incomes = transactionDao.getTransactionsOfType(Type.INCOME);
                    printTransactionOfType(incomes);
                }
                case PRINT_ALL_EXPENSES -> {
                    List<Transaction> expenses = transactionDao.getTransactionsOfType(Type.EXPENSE);
                    printTransactionOfType(expenses);
                }
                case EXIT -> {
                    transactionDao.close();
                    System.out.println("Koniec programu, do zobaczenia!");
                }
                default -> System.out.println("Niepoprawna opcja! Wybierz ponownie.");
            }
        } while (option != EXIT);
    }

    private void printTransactionOfType(List<Transaction> transactions) {
        for (Transaction transaction : transactions) {
            System.out.println(transaction);
        }
    }

    private int printAndGetOption() {
        System.out.println("Wybierz opcję");
        System.out.println(ADD + " - Dodaj transakcję");
        System.out.println(UPDATE + " - Modyfikuj transakcję");
        System.out.println(DELETE + " - Usuń transakcję");
        System.out.println(PRINT_ALL_INCOMES + " - Wyświetl wszystkie przychody");
        System.out.println(PRINT_ALL_EXPENSES + " - Wyświetl wszystkie wydatki");
        System.out.println(EXIT + " - Wyjście");
        int option = scanner.nextInt();
        scanner.nextLine();
        return option;
    }

    private int getTransactionIdToDelete() {
        System.out.println("Podaj id transakcji, którą chcesz usunąć");
        int id = scanner.nextInt();
        scanner.nextLine();
        return id;
    }

    private Transaction update() {
        System.out.println("Podaj id transakcji, którą chcesz modyfikować");
        int id = scanner.nextInt();
        scanner.nextLine();
        Transaction transaction = create();
        transaction.setId(id);
        return transaction;
    }

    private Transaction create() {
        System.out.println("Typ transakcji (wydatek/przychód)");
        String typePl = scanner.nextLine();
        Type type = Type.findTypeByDescriptionPl(typePl);
        System.out.println("Opis");
        String description = scanner.nextLine();
        System.out.println("Kwota");
        double amount = scanner.nextDouble();
        scanner.nextLine();
        System.out.println("Data (yyyy-MM-dd)");
        Date date;
        try {
            String dateString = scanner.nextLine();
            date = Date.valueOf(dateString);
        } catch (IllegalArgumentException e) {
            throw new IncorrectDateFormatException("Nieprawidłowa data, prawidłowy format: yyyy-MM-dd");
        }
        return new Transaction(type, description, amount, date);
    }
}
