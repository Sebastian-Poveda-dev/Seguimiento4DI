package ui;

import  model.SubarraySum;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Programa para encontrar subarreglo con suma exacta");
        System.out.println("------------------------------------------------");

        // Solicitar al usuario que ingrese los números separados por coma
        System.out.print("Ingrese los elementos del arreglo separados por coma: ");
        String input = scanner.nextLine();

        // Convertir la entrada a un arreglo de enteros
        String[] numbers = input.split(",");
        int[] arr = new int[numbers.length];

        for (int i = 0; i < numbers.length; i++) {
            try {
                arr[i] = Integer.parseInt(numbers[i].trim());
            } catch (NumberFormatException e) {
                System.out.println("Error: '" + numbers[i].trim() + "' no es un número válido.");
                return;
            }
        }

        // Solicitar la suma objetivo
        System.out.print("Ingrese la suma objetivo (S): ");
        int S;
        try {
            S = Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("Error: La suma objetivo debe ser un número entero.");
            scanner.close();
            return;
        }

        // Utilizar el controlador para encontrar el subarreglo
        SubarraySum controller = new SubarraySum();
        int[] result = controller.findSubarrayWithSum(arr, S);

        // Mostrar el resultado
        System.out.println("\nResultado:");
        System.out.println(controller.displaySubarray(arr, result));

        scanner.close();
    }
}