# El Problema de la Cena de los Filósofos  
**Moisés Ortiz**  
2º DAM  

---

## Índice  

- [Introducción](#introducción)  
- [Análisis del problema](#análisis-del-problema)  
- [Diseño de la solución](#diseño-de-la-solución)  
- [Implementación](#implementación)  
- [Resultados de la ejecución](#resultados-de-la-ejecución)  
- [Conclusiones](#conclusiones)  

---

## Introducción  

### Descripción breve del problema  
Cinco filósofos están sentados alrededor de una mesa circular, donde alternan entre pensar y comer. Cada filósofo tiene un plato de arroz frente a él y un solo palillo a su izquierda. Para comer, un filósofo necesita dos palillos: el de su izquierda y el de su derecha. El problema consiste en diseñar un sistema que permita a los filósofos comer sin interrumpir a los demás y sin entrar en interbloqueo.

### Objetivo de la implementación  
El objetivo de la práctica es utilizar semáforos para controlar cuándo los filósofos comen y sueltan los palillos, garantizando que todos puedan comer en algún momento y evitando que se queden sin palillos.

---

## Análisis del problema  

### Descripción de los componentes  
Los filósofos estarán encargados de comer, pensar, coger y soltar los palillos. Cada filósofo necesitará acceso a los dos palillos adyacentes para poder comer. Si no están disponibles, deberá esperar a que se liberen.

### Desafíos de concurrencia  
Pueden surgir problemas como:
- Todos los filósofos intentan coger los palillos al mismo tiempo.
- Problemas al soltar los palillos para que otros los usen.
- Interbloqueo (deadlock) e inanición (starvation).

---

## Diseño de la solución  

### Diagrama de clases  
<p align="center">
<img width="2464" height="3566" alt="diagrama_de_clases" src="https://github.com/user-attachments/assets/2528997c-5463-4c86-8e5a-d095f5109be8" />
</p>

### Explicación de los semáforos  
Los palillos son controlados por semáforos. Cada palillo tiene un semáforo asociado. Para evitar deadlocks, todos los filósofos son “zurdos”: primero cogen el palillo de su izquierda y luego el de su derecha. Tras comer, sueltan ambos palillos para que otros los usen.

---

## Implementación  

### Método `run` de la clase “Filosofo” y sincronización con semáforos  
<p align="center">
<img width="746" height="442" alt="imagen" src="https://github.com/user-attachments/assets/49fea4f8-1777-4fca-91b8-a9f9e2d96172" />
</p>

Cada filósofo:
1. Comienza pensando.
2. Intenta coger los palillos (primero el izquierdo, luego el derecho).
3. Come cuando tiene ambos.
4. Suelta los palillos al terminar.
5. Muestra mensajes indicando el estado y los palillos usados.

### Comentarios sobre la implementación y prevención de interbloqueo e inanición  
- Se muestra el estado de cada filósofo en tiempo real.
- Todos los filósofos son “zurdos” para evitar deadlock.
- Se controla en todo momento qué palillos están libres u ocupados.

---

## Resultados de la ejecución  

### Capturas de pantalla  
<p align="center">
<img width="787" height="254" alt="imagen" src="https://github.com/user-attachments/assets/1da8629c-6605-4c5f-931d-1db8c6f15448" />
</p>

### Análisis de la Salida  
Cada filósofo piensa y espera a que los palillos estén disponibles antes de comer. Al terminar, suelta ambos palillos para que otros los usen. Se observa en todo momento qué palillos están libres.

---

## Conclusiones  

### Lecciones aprendidas  
- Los semáforos son útiles para sincronizar hilos en entornos concurrentes.
- La programación concurrente es fundamental en aplicaciones con múltiples hilos compartiendo recursos.

### Posibles mejoras  
- Usar constantes para el número de filósofos y palillos.
- Añadir más timeouts para mayor robustez.
- Implementar variantes como filósofos diestros o alternancia en el orden de toma de palillos.

---

**Fin del documento**
