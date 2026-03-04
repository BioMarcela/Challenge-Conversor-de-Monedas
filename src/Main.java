import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        // Crear objeto Scanner (entrada de usuario)
        Scanner scanner = new Scanner(System.in);

        // Definir URL base y clave de la API
        String URL = "https://v6.exchangerate-api.com/v6/";
        String clave = "1112e118ef6bbcb8ffa341d2";

        // Crear cliente HTTP
        HttpClient cliente = HttpClient.newHttpClient();

        // Iniciar ciclo principal del programa
        while (true) {

            System.out.println("\n=== CONVERSOR DE MONEDA ===");
            System.out.println("1 - Convertir moneda");
            System.out.println("2 - Salir");
            System.out.print("Seleccione una opción: ");

            int opcion = scanner.nextInt();
            scanner.nextLine(); // limpiar buffer

            // Validar opción del menú
            if (opcion < 1 || opcion > 2) {
                System.out.println("Opción inválida.");
                continue;
            }

            // Salir del programa
            if (opcion == 2) {
                System.out.println("Gracias por usar el conversor 😊");
                break;
            }

            // Mostrar monedas disponibles
            System.out.println("\nMonedas disponibles:");
            System.out.println("1 - ARS (Peso argentino)");
            System.out.println("2 - BOB (Boliviano boliviano)");
            System.out.println("3 - BRL (Real brasileño)");
            System.out.println("4 - CLP (Peso chileno)");
            System.out.println("5 - COP (Peso colombiano)");
            System.out.println("6 - USD (Dólar estadounidense)");

            System.out.print("Seleccione moneda base (1-6): ");
            int opcionBase = scanner.nextInt();

            System.out.print("Seleccione moneda destino (1-6): ");
            int opcionDestino = scanner.nextInt();

            System.out.print("Ingrese monto a convertir: ");
            double monto = scanner.nextDouble();
            scanner.nextLine(); // limpiar buffer

            // Validar monedas
            if (opcionBase < 1 || opcionBase > 6 ||
                    opcionDestino < 1 || opcionDestino > 6) {
                System.out.println("Moneda inválida.");
                continue;
            }

            String monedaBase = "";
            String monedaDestino = "";

            // Asignar código de moneda según selección
            switch (opcionBase) {
                case 1 -> monedaBase = "ARS";
                case 2 -> monedaBase = "BOB";
                case 3 -> monedaBase = "BRL";
                case 4 -> monedaBase = "CLP";
                case 5 -> monedaBase = "COP";
                case 6 -> monedaBase = "USD";
            }

            switch (opcionDestino) {
                case 1 -> monedaDestino = "ARS";
                case 2 -> monedaDestino = "BOB";
                case 3 -> monedaDestino = "BRL";
                case 4 -> monedaDestino = "CLP";
                case 5 -> monedaDestino = "COP";
                case 6 -> monedaDestino = "USD";
            }

            // Construir URL final con moneda base
            String urlFinal = URL + clave + "/latest/" + monedaBase;

            // Crear solicitud HTTP (GET)
            HttpRequest solicitud = HttpRequest.newBuilder()
                    .uri(URI.create(urlFinal))
                    .GET()
                    .build();

            try {

                // Enviar solicitud y recibir respuesta
                HttpResponse<String> respuesta =
                        cliente.send(solicitud, HttpResponse.BodyHandlers.ofString());

                // Convertir respuesta a formato JSON
                JsonElement elemento = JsonParser.parseString(respuesta.body());
                JsonObject objeto = elemento.getAsJsonObject();

                // Obtener objeto conversion_rates
                JsonObject conversionRates =
                        objeto.getAsJsonObject("conversion_rates");

                // Obtener tasa y calcular conversión
                if (conversionRates.has(monedaDestino)) {

                    double tasa =
                            conversionRates.get(monedaDestino).getAsDouble();

                    double resultado = monto * tasa;

                    System.out.println("\nResultado:");
                    System.out.println(monto + " " + monedaBase +
                            " equivale a " + resultado + " " + monedaDestino);

                } else {
                    System.out.println("Moneda no encontrada en la API.");
                }

            } catch (IOException | InterruptedException e) {

                System.out.println("Error al conectar con la API: " + e.getMessage());
            }
        }

        scanner.close();
    }
}
