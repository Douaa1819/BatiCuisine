package utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;
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
                int value = scanner.nextInt();
                if (value <= 0) {
                    System.out.println("Veuillez entrer un nombre entier supérieur à zéro.");
                    continue;
                }
                return value;
            } catch (InputMismatchException e) {
                System.out.println("Veuillez entrer un nombre entier valide.");
                scanner.next();
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
        scanner.useLocale(Locale.US);
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


    public static String readPhoneNumber() {
        while (true) {
            String numeroTelephone = scanner.nextLine();
            if (numeroTelephone.length() < 10) {
                System.out.println("Le numéro de téléphone doit contenir au moins 10 caractères.");
                continue;
            }
            if (!numeroTelephone.matches("[0-9\\s\\-\\.\\+()]*")) {
                System.out.println("Le numéro de téléphone ne doit contenir que des chiffres et des caractères spéciaux autorisés (espace, tiret, point, plus, parenthèses).");
                continue;
            }

            return numeroTelephone;
        }
    }

    public static int readProfessionnelInput() {
        while (true) {
            String input = scanner.nextLine();
            if (input.isEmpty()) {
                return 0;
            }
            if (input.equals("0")) {
                return 0;
            } else if (input.equals("1")) {
                return 1;
            }

            System.out.println("Veuillez entrer 0 ou 1.");
        }
    }





    public static LocalDate readDate() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String dateStr = scanner.nextLine();
            try {
                LocalDate date = LocalDate.parse(dateStr, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                if (date.isBefore(LocalDate.now())) {
                    System.out.println("Erreur : la date ne peut pas être dans le passé. Veuillez entrer une date future (format : jj/mm/aaaa) : ");
                    continue;
                }
                return date;
            } catch (DateTimeParseException e) {
                System.out.println("Erreur : format de date invalide. Veuillez réessayer (format : jj/mm/aaaa) : ");
            }
        }
    }


    public static boolean readYesNo() {
        while (true) {
            String input = scanner.nextLine().trim().toLowerCase();
            if (input.equals("y")) {
                return true;
            } else if (input.equals("n")) {
                return false;
            }
            System.out.println("Erreur : entrée invalide. Veuillez réessayer.");
        }
    }


    public static double readValidTva() {
        double tva = -1;
        while (tva <= 0) {
            if (scanner.hasNextDouble()) {
                tva = scanner.nextDouble();
                if (tva <= 0) {
                    System.out.println("Erreur : le taux de TVA doit être supérieur à 0.");
                }
            } else {
                System.out.println("Erreur : veuillez entrer un nombre valide.");
                scanner.next();
            }
        }
        return tva;
    }

}