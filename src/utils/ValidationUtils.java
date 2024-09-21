package utils;

import java.util.Scanner;
import java.util.InputMismatchException;

public class ValidationUtils {

    public static final Scanner scanner = new Scanner(System.in);

    /**
     * Lit une chaîne de caractères depuis l'entrée utilisateur.
     *
     * @return La chaîne de caractères entrée par l'utilisateur.
     */
    public static String readString() {
        String input = scanner.nextLine();
        while (input == null || input.trim().isEmpty()) {
            System.out.println("Erreur : l'entrée ne doit pas être vide. Veuillez entrer une valeur.");
            input = scanner.nextLine();
        }
        return input;
    }

    /**
     * Lit un entier depuis l'entrée utilisateur. Répète la demande jusqu'à ce que
     * une valeur entière valide soit entrée.
     *
     * @return L'entier entré par l'utilisateur.
     */
    public static int readInt() {
        while (true) {
            try {
                return scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Veuillez entrer un nombre entier valide.");
                scanner.next(); // Consomme la valeur invalide
            }
        }
    }

    /**
     * Lit un double depuis l'entrée utilisateur. Répète la demande jusqu'à ce qu'une
     * valeur double valide soit entrée.
     *
     * @return Le double entré par l'utilisateur.
     */
    public static double readDouble() {
        while (true) {
            if (scanner.hasNextDouble()) {
                double value = scanner.nextDouble();
                scanner.nextLine();
                return value;
            } else {
                System.out.println("Erreur : veuillez entrer un nombre valide.");
                scanner.next();
            }
        }
    }


    /**
     * Vérifie si une chaîne de caractères est null ou vide.
     *
     * @param input La chaîne de caractères à vérifier.
     * @return True si la chaîne est null ou vide, false sinon.
     */
    public static boolean isNullOrEmpty(String input) {
        return input == null || input.trim().isEmpty();
    }

    private static boolean isValidName(String input) {
        return input != null && input.matches("[a-zA-Z\\s]+");
    }

    public static String readValidName() {
        String input = scanner.nextLine();
        while (!isValidName(input)) {
            System.out.println("Erreur : le nom ne doit contenir que des lettres. Veuillez réessayer.");
            input = scanner.nextLine();
        }
        return input;
    }


    /**
     * Convertit une chaîne de caractères en entier, en renvoyant une valeur par défaut
     * si la conversion échoue.
     *
     * @param input La chaîne de caractères à convertir.
     * @param defaultValue La valeur par défaut à retourner en cas d'échec de la conversion.
     * @return L'entier converti ou la valeur par défaut.
     */
    public static int parseIntOrDefault(String input, int defaultValue) {
        try {
            return Integer.parseInt(input.trim());
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }

    /**
     * Convertit une chaîne de caractères en double, en renvoyant une valeur par défaut
     * si la conversion échoue.
     *
     * @param input La chaîne de caractères à convertir.
     * @param defaultValue La valeur par défaut à retourner en cas d'échec de la conversion.
     * @return Le double converti ou la valeur par défaut.
     */
    public static double parseDoubleOrDefault(String input, double defaultValue) {
        try {
            return Double.parseDouble(input.trim());
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }
}
