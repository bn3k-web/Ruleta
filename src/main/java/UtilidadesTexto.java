import java.util.Scanner;

public class UtilidadesTexto {

    static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int opcion;

        do {
            menu();
            System.out.print("Escribe una opción: ");
            opcion = sc.nextInt();
            sc.nextLine();
            redirectador(opcion, sc);
        } while (opcion != 5);
    }
    private static void menu(){
        String mostrarMenu = "1. Verificar si una frase es Revés-Derecho \n"
                + "2. Contar Vocales en una frase \n"
                + "3. Encriptar una frase \n"
                + "4. Desencriptar una frase \n"
                + "5. Salir";
        System.out.println(mostrarMenu);
    }
    public static boolean verificarPalindromo(String palindromo){
        palindromo = palindromo.replaceAll("\\s+", "").toLowerCase();
        String PalindromoRevertido = new StringBuilder(palindromo).reverse().toString();
        return palindromo.equals(PalindromoRevertido);
    }
    public static int contarVocales(String texto) {
        int contador = 0;
        texto = texto.toLowerCase();
        for (int i = 0; i < texto.length(); i++) {
            char c = texto.charAt(i);
            if ("aeiou".indexOf(c) != -1) {
                contador++;
            }
        }
        return contador;
    }
    public static String encriptarTexto(String texto){
        return texto.replace("a", "@")
                .replace("e", "&")
                .replace("i", "¡")
                .replace("o", ">")
                .replace("u", "#");
    }
    public static String desencriptarTexto(String texto){
        return texto.replace("@", "a")
                .replace("&", "e")
                .replace("¡", "i")
                .replace(">", "o")
                .replace("#", "u");

    }
    public static void redirectador(int opcion, Scanner sc){
        switch (opcion) {
            case 1 -> {
                System.out.print("Ingresa la frase: ");
                String frase1 = sc.nextLine();
                System.out.println("¿Es palíndromo?: " + verificarPalindromo(frase1));
            }
            case 2 -> {
                System.out.print("Ingresa la frase: ");
                String frase2 = sc.nextLine();
                System.out.println("Cantidad de vocales: " + contarVocales(frase2));
            }
            case 3 -> {
                System.out.print("Ingresa la frase: ");
                String frase3 = sc.nextLine();
                System.out.println("Texto encriptado: " + encriptarTexto(frase3));
            }
            case 4 -> {
                System.out.print("Ingresa el texto encriptado: ");
                String frase4 = sc.nextLine();
                System.out.println("Texto desencriptado: " + desencriptarTexto(frase4));
            }
            case 5 -> System.out.println("Saliendo...");
            default -> System.out.println("Opción no válida.");
        }
    }
}

