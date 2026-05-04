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
Este programa, denominado **Wungsdle**, es una recreación del popular juego **Wordle**, diseñada como una aplicación de escritorio. El objetivo principal es adivinar una palabra oculta de 5 letras en un máximo de 6 intentos, utilizando pistas visuales basadas en colores.

## Reglas del Juego
Para ganar, el jugador debe descubrir la palabra secreta siguiendo estas normas:

1.  **Intentos:** Tienes 6 oportunidades para adivinar la palabra.
2.  **Longitud:** Cada palabra ingresada debe tener exactamente 5 letras.
3.  **Retroalimentación por Colores:** Después de cada intento, el color de las celdas cambiará:
    * 🟩 **Verde:** La letra está en la palabra y en la posición correcta.
    * 🟨 **Amarillo:** La letra está en la palabra, pero en la posición incorrecta.
    * ⬜ **Gris:** La letra no forma parte de la palabra secreta.
4.  **Diccionario:** Solo se aceptan palabras válidas dentro del diccionario del juego.

---

## Especificaciones Técnicas
El desarrollo se basó en estándares de ingeniería de software para garantizar un sistema escalable y organizado:

* **Lenguaje:** Java.
* **Interfaz Gráfica (GUI):** Implementada con **Java Swing**, proporcionando una experiencia de usuario interactiva[cite: 1].
* **Arquitectura:** Se utilizó el patrón de diseño **Modelo-Vista-Controlador (MVC)** para separar la lógica, la interfaz y el control de eventos.
* **Patrones de Diseño:**
* **Singleton:** Implementado para la gestión centralizada de la música y efectos de sonido.
* **POO (Programación Orientada a Objetos):** Uso de clases y objetos para estructurar el sistema.
* **Persistencia de Datos:** En lugar de una base de datos convencional, el sistema utiliza un **archivo de texto (.txt)** para almacenar y persistir la información del **ranking** de jugadores.

---

## Instalación y Ejecución
Para facilitar el acceso a diferentes usuarios, el proyecto incluye:

1.  **Versión Ejecutable (.exe):** Ideal para usuarios que no cuentan con entornos de desarrollo configurados.
2.  **Código Fuente:** El proyecto puede ser importado y ejecutado en cualquier IDE compatible con Java.

---
*Este documento resume el trabajo realizado para la materia Programación 3.*
