package model;
import structures.*;

public class SubarraySum {

    /**
     * Encuentra un subarreglo con suma exacta S
     * @param arr Arreglo de enteros
     * @param S Suma objetivo
     * @return Arreglo con los índices [i, j] o [-1, -1] si no existe solución
     */
    public int[] findSubarrayWithSum(int[] arr, int S) {
        // HashMap porpio para almacenar la suma acumulada hasta cada índice donde la llave es la suma acumulada y el valor el índice
        HashMap<Integer, Integer> sumMap = new HashMap<>();

        int currentSum = 0;


        for (int i = 0; i < arr.length; i++) {

            currentSum += arr[i];
            // Verificar si algún subarreglo empezando desde el índice 0 tiene la suma S
            // Si la suma acumulada es igual a S, entonces el subarreglo es de 0 a i
            if (currentSum == S) {
                return new int[]{0, i};
            }

            // Si existe un subarreglo con suma (currentSum - S), entonces el resultado son lso indices {((currentSum-S) + 1) , i}
            Integer prevIndex = sumMap.get(currentSum - S);
            if (prevIndex != null) {
                int startIndex = prevIndex + 1;
                return new int[]{startIndex, i};
            }

            // Para guardar la suma acumulada (llave) hasta el inidice (valor)
            sumMap.put(currentSum, i);
        }

        // Si no se encuentra ningún subarreglo, retornar [-1, -1]
        return new int[]{-1, -1};
    }

    /**
     * Muestra los elementos del subarreglo encontrado
     */
    public String displaySubarray(int[] arr, int[] indices) {
        if (indices[0] == -1) {
            return "-1";
        }

        StringBuilder result = new StringBuilder();
        result.append("(").append(indices[0]).append(", ").append(indices[1]).append(")\n");

        int sum = 0;
        result.append("Subarreglo: {");
        for (int i = indices[0]; i <= indices[1]; i++) {
            sum += arr[i];
            result.append(arr[i]);
            if (i < indices[1]) {
                result.append(", ");
            }
        }
        result.append("} → Suma: ").append(sum);
        return result.toString();
    }
}