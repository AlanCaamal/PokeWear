PokeWear

Aplicación para reloj inteligente (Wear OS) que consume la PokéAPI para mostrar aleatoriamente un Pokémon.

Nombre del proyecto: PokeWear

Nombre del alumno(a):

Eloisa Hernandez de la Cruz

Fernanda Hernandez de la Cruz

Juan Daniel Almeida Ovando

Alan Javier Caamal Hernandez

Descripción breve de la aplicación
PokeWear es una aplicación desarrollada para Wear OS que permite al usuario obtener un Pokémon de forma aleatoria presionando un botón. Cada vez que se presiona el botón "Atrapar", la app genera un ID aleatorio, consulta la información correspondiente en la PokéAPI y muestra en pantalla la imagen (sprite) y el nombre del Pokémon obtenido. La app maneja estados de carga y errores de conexión mediante una máquina de estados (CaptureState: Idle, Loading, Success, Error).

Tecnologías utilizadas

•	Kotlin

•	Jetpack Compose for Wear OS

•	Retrofit2 + Gson Converter (consumo de API REST)

•	Coil (carga de imágenes/sprites)

•	Kotlin Coroutines (llamadas asíncronas)

•	Android Studio

API utilizado

PokéAPI endpoint utilizado: GET https://pokeapi.co/api/v2/pokemon/{id}

Instrucciones para ejecutar el proyecto

1.	Repositorio: https://github.com/AlanCaamal/PokeWear.git

2.	Abrir el proyecto en Android Studio.

3.	Esperar a que Gradle sincronice las dependencias.

4.	Ejecutar la app en un emulador de Wear OS (Wear OS Small Round o similar) o en un dispositivo físico con Wear OS.

5.	Presionar el botón "Atrapar" para obtener un Pokémon aleatorio.

Captura de pantalla de la aplicación funcionando
<img width="1919" height="1079" alt="captura_pokewear" src="https://github.com/user-attachments/assets/e7f1f58f-6a63-4e9f-bfb5-d01ebdf0346c" />

Uso de Inteligencia Artificial

Se utilizó Claude como herramienta de apoyo durante el desarrollo del proyecto, para la generación y corrección de código, así como para resolver dudas técnicas sobre Wear OS y el consumo de la PokéAPI.

Enlace del chat de IA: https://claude.ai/share/a33e5aa2-7f5f-4e5b-bffa-a1bffa0dae07

