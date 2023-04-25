package com.example.zadanie24;

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
                    Transaction transaction = create();
                    transactionDao.addTransaction(transaction);
                }
                case UPDATE -> {
                    Transaction updatedTransaction = update();
                    transactionDao.updateTransaction(updatedTransaction);
                }
                case DELETE -> {
                    int id = getTransactionIdToDelete();
                    transactionDao.deleteTransaction(id);
                }
                case PRINT_ALL_INCOMES -> transactionDao.printTransactionOfType(Type.INCOME);
                case PRINT_ALL_EXPENSES -> transactionDao.printTransactionOfType(Type.EXPENSE);
                case EXIT -> {
                    transactionDao.close();
                    System.out.println("Koniec programu, do zobaczenia!");
                }
                default -> System.out.println("Niepoprawna opcja! Wybierz ponownie.");
            }
        } while (option != EXIT);
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
        Type type = findTypeByDescriptionPl(typePl);
        System.out.println("Opis");
        String description = scanner.nextLine();
        System.out.println("Kwota");
        double amount = scanner.nextDouble();
        scanner.nextLine();
        System.out.println("Data (yyyy-MM-dd)");
        String date = scanner.nextLine();
        return new Transaction(type, description, amount, date);
    }

    private Type findTypeByDescriptionPl(String descriptionPl) {
        Type[] values = Type.values();
        for (Type value : values) {
            if (value.getPlDescription().equals(descriptionPl)) {
                return value;
            }
        }
        return null;
    }
}
