💱 Conversor de Moneda en Java

Aplicación de consola desarrollada en Java que permite convertir monedas en tiempo real utilizando la API de ExchangeRate-API.

📌 Descripción

Este proyecto consiste en un conversor de monedas que:

Permite seleccionar moneda base y moneda destino.

Se conecta a una API externa mediante HttpClient.

Procesa la respuesta en formato JSON usando la librería Gson.

Realiza la conversión en tiempo real.

Incluye validaciones de entrada y manejo de errores.

🚀 Tecnologías utilizadas

Java 17+

HttpClient

HttpRequest

HttpResponse

Gson (para parseo de JSON)

API: ExchangeRate-API

🛠️ Funcionamiento

El usuario selecciona una opción del menú:

Convertir moneda

Salir

Selecciona:

Moneda base

Moneda destino

Monto a convertir

El programa:

Construye la URL dinámica con la moneda base.

Envía una solicitud HTTP GET.

Recibe la respuesta en formato JSON.

Extrae la tasa de cambio.

Calcula y muestra el resultado.

👩‍💻 Autora

Marcela Aros
Proyecto académico – Programación en Java
