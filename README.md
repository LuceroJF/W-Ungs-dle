# Wungsdle - Juego de Palabras

## Desarrolladores
Este proyecto fue desarrollado por:
* **Lucero Juan**
* **Dino Martin**
* **Lizarraga Jorge**
* **Mansilla Diego**

**Cátedra:** Programación 3 (Proyecto Universitario)

---

## Descripción del Proyecto
Este programa es una recreación del popular juego **Wordle**, diseñada como una aplicación de escritorio robusta. El objetivo principal es adivinar una palabra oculta de 5 letras en un máximo de 6 intentos, utilizando pistas visuales basadas en colores.

## Reglas del Juego
Para ganar, el jugador debe descubrir la palabra secreta siguiendo estas normas:

1.  **Intentos:** Tienes 6 oportunidades para adivinar la palabra.
2.  **Longitud:** Cada palabra ingresada debe tener exactamente 5 letras.
3.  **Retroalimentación por Colores:** Después de cada intento, el color de las celdas cambiará para mostrar qué tan cerca estás de la palabra:
    * 🟩 **Verde:** La letra está en la palabra y en la posición correcta.
    * 🟨 **Amarillo:** La letra está en la palabra, pero en la posición incorrecta.
    * ⬜ **Gris:** La letra no forma parte de la palabra secreta.
4.  **Diccionario:** Solo se aceptan palabras válidas dentro del diccionario del juego.

---

## Especificaciones Técnicas
El desarrollo se basó en estándares de ingeniería de software para garantizar un código limpio y escalable:

* **Lenguaje:** Java.
* **Interfaz Gráfica (GUI):** Implementada con **Java Swing**, proporcionando una experiencia de usuario fluida y visualmente atractiva.
* **Arquitectura:** Se utilizó el patrón de diseño **Modelo-Vista-Controlador (MVC)** para separar la lógica de negocio, la representación visual y la interacción del usuario.
* **Patrones de Diseño:**
    * **Singleton:** Implementado específicamente para la gestión de la música y efectos de sonido.
    * **POO (Programación Orientada a Objetos):** Uso riguroso de clases, objetos, herencia y polimorfismo.
* **Persistencia de Datos:** En lugar de utilizar una base de datos convencional, el sistema implementa un sistema de almacenamiento en archivos de texto (**archivo .txt**) para gestionar y persistir la información del **ranking** del juego.
* **Pruebas Unitarias:** Se incorporaron tests unitarios utilizando el framework **JUnit** para verificar la integridad y el correcto funcionamiento de los algoritmos del juego.

---

## Instalación y Ejecución
Para facilitar el acceso a usuarios finales, el proyecto incluye un archivo ejecutable:

1.  **Versión Ejecutable (.exe):** Ideal para usuarios que no tengan entornos de desarrollo configurados.
2.  **Código Fuente:** El proyecto puede ser importado en cualquier IDE compatible con Java y ejecutado desde la clase principal.

