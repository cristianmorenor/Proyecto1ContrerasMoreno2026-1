# Simulador de Stacking Cups Con Tapas 
# Autores Cristian Santiago Moreno y David Alejandro Contreras
# Desarrollo orientado por objetos 2026-1

## Descripción del Problema

Este proyecto es una implementación básica con simulador del problema J de la ICPC ( International Collegiate Programming Contest, el cual citando el enunciado nos dice lo siguiente: " You have a collection of n cylindrical cups, where the ith cup is 2i−1 cm tall. The cups have increasing diameters, such that cup i fits inside cup j if and only if i < j. The base of each cup is 1 cm thick (which makes the smallest cup rather useless as it is only 1 cm tall, but you keep it for sentimental reasons). After washing all the cups, you stack them in a tower. Each cup is placed upright (in other words, with the opening at the top) and with the centers of all the cups aligned vertically. The height of the tower is defined as the vertical distance from the lowest point on any of the cups to the highest. You would like to know in what order to place the cups such that the final height (in cm) is your favorite number. Note that all n cups must be used. For example, suppose n = 4 and your favorite number is 9. If you place the cups of heights 7, 3, 5, 1, in that order, the tower will have a total height of 9, as shown in Figure J.1." 
Si necesita más claridad en cuanto a la imagen o en cuanto al enunciado teniendo en cuenta sus inputs o outputs, consultar el siguiente link que lo remitirá al pdf oficial con el enunciado : https://drive.google.com/file/d/1RskN-ze_icvo5kB5he7FlRIbt4ECkt2G/view

## Estructura del Proyecto

El proyecto se encuentra organizado en 4 paquetes principales (Viendole desde el bluej antes de pasarlo a eclipse), donde esta en primer lugar el paquete de StackingItems, el cual tiene contenida toda la lógica del dominio, siendo sus clases principales las de Tower, Cup, Lid y TowerBackGround. El siguiente paquete es el de shapes, el cual es el que contiene el manejo de figuras para representar estas Cups y Lids que son lo básico para la representación visual del simulador. El paquete de contest es el encargado de contener la lógica de solución del problema contenida en la clase TowerContest, y por ultimo el paquete de Test que es el que contiene las pruebas unitarias de los metodos presentes en las clases y las debidas pruebas de aceptación. Como se aclaro esta estructura es viendola desde la organización que teniamos en el bluej y esto se puede consultar en el modelo astah en el diagrama de paquetes, al hacer la transición a la herramienta eclipse cambia esta organización por lo que esta estructura solo es la guia para que entienda la organizacion.

## Funcionalidades

Las funcionalidades que ofrece este simulador y alineados a los diferentes ciclos trabajados es que ofrece en terminos generales es la de Crear torres de tazas, agregar y remover tazas y tapas, reorganizar la torre, consultar informacion de la torre y simular la solución del problema ( ver documento si necesita mas claridad ).

## Herramientas utilizadas para el proyecto

Las herramientas usadas para el desarrollo del proyecto y la debida entrega fueron las de principalmente el lenguaje Java, el editor Bluej, el  Eclipse IDE , JUnit ( Para las pruebas unitarias ), Jacoco ( Para el analisis de cobertura), PMD ( Para calidad de código ) y por último el modelado UML con la herramienta Astah.


## Ejecución

Para hacer la ejecución del programa debe hacer lo siguiente:
1. Se debe importar el programa en Eclipse.
2. Ejecutar las pruebas que hay en el paquete de Test.
3. Ejecutar la clase de TowerContest para simular los casos del problema.
4. Si desea realizar otras acciones de las funcionalidades, puede agregar una torre del tamaño que desea y ejecutar metodo para ver las funcionalidades mencionadas.

## Pruebas

El proyecto como se mencionó cuenta con pruebas unitarias que validan la lógica del dominio y para medir esto se utilizó el Jacoco para ver la cobertura del código que debía ser superior al 75%. Tambien se cuenta con pruebas de aceptación para poder lograr una correcta interacción con el usuario y con la funcionalidad del sistema. Por lo que al realizar todo esto se logró el objetivo de cobertura de métodos y código del dominio.

## Analisis

El análisis dinámico y estático del código permitieron mejorar la cobertura del código y corregir problemas de calidad. Para mas detalles leer los archivos de: [AnalisisDinamico](./AnalisisDinamico.md) [AnalisisEstatico](./AnalisisEstatico.md)


## Retrospectivas

## Ciclo 1
1. ¿Cuáles fueron los mini-ciclos definidos?
Los mini ciclos definidos para este ciclo fue en primer lugar un analisis del problema para entender bien a fondo cual es el objetivo de este proyecto y como vamos a inciar, el siguiente mini-ciclo fue el diseño del modelo con las clases Tower, Cup y Lid para cumplir con los objetivos de este primer ciclo y se desarrollaron en la herramienta astah y por último se contruyeron inicialmente las clases mencionadas en el bluej.
2. ¿Cuál es el estado actual del proyecto en términos de mini-ciclos? ¿por qué?
En cuanto a los mini-ciclos mencionados el proyecto se encuentra masoemnos a la mitad del objetivo que se quería puesto que la clase tower no está completa de acuerdo a los requisitos para esta primera entrega y esto fue debido a falta de tiempo.
3. ¿Cuál fue el tiempo total invertido por cada uno de ustedes? (Horas/Hombre)
El tiempo total fue de: Cristian 5 horas, David 5 horas.
4. ¿Cuál consideran fue el mayor logro? ¿Por qué?
Consideramos que el mayor logro fue el de tener una buena comunicación entre nosotros como equipo pues nos pudimos entender y dividir el trabajo y trabajar al tiempo.
5. ¿Cuál consideran que fue el mayor problema técnico? ¿Qué hicieron para resolverlo?
Podriamos decir que el mayor problema tecnico fue el de control de versiones pues tuvimos problemas de archivos enviados entre nosotros y nos confundimos y perdimos tiempo, para solucionarlo empezamos a usar esta herramienta de github para evitar esto.
6. ¿Qué hicieron bien como equipo? ¿Qué se comprometen a hacer para mejorar los resultados?
Hicimos bien como equipo el de tener una comunicación efectiva y sin problemas entre nosotros y para mejorar resultados nos comprometemos a hacer un mejor manejo de tiempo y planeacion.
7. Considerando las prácticas XP incluidas en los laboratorios. ¿cuál fue la más útil? ¿por qué?
La más util fue la de Pair-Programming puesto que al estar los dos alineados trabajando simultaneamente pudimos plantear correctamente que era lo que queriamos hacer y de que forma, pudimos evitar algunos errores y solucionar problemas y estar de acuerdo con lo que estabamos haciendo.
8. ¿Qué referencias usaron? ¿Cuál fue la más útil? Incluyan citas con estándares adecuados.
La referencias utilizadas fueron principalmente el enunciado del problema: https://drive.google.com/file/d/1RskN-ze_icvo5kB5he7FlRIbt4ECkt2G/view, el material de clase presente en moodle como fueron las diapositivas y las notas de clase, consultamos paginas como el API de java y otras paginas oficiales para estar alineados con los estandares de Java.

## Ciclo 2 
1. ¿Cuáles fueron los mini-ciclos definidos?
Para este ciclo se definieron los mini-ciclos de Implementación de operaciones básicas (push, pop), Manejo de tapas y pruebas unitarias iniciales
2. ¿Cuál es el estado actual del proyecto en términos de mini-ciclos? ¿por qué?
En cuanto a los mini-ciclos definidos consideramos que el estado del proyecto es que esta completo pues cubrimos todo lo que pedia en cuanto a requisitos funcionales, entregables como el bluej y el astah completo y por otro lado completamos lo que faltaba del ciclo 1.
3. ¿Cuál fue el tiempo total invertido por cada uno de ustedes? (Horas/Hombre)
El tiempo total fue de: Cristian 9 horas, David 9 horas.
4. ¿Cuál consideran fue el mayor logro? ¿Por qué?
El mayor logro fue el de igual que el ciclo anterior tener una buena comunicacion y aparte de esto el de haber podido realizar todo lo que se pedia en este ciclo en cuanto a codigo y modelado, por lo que es un cambio respecto al ciclo anterior, lo logramos debido a un mejor manejo de tiempo y mejor planeación.
5. ¿Cuál consideran que fue el mayor problema técnico? ¿Qué hicieron para resolverlo?
El unico problema tecnico fue un error de versiones dentro del github pues en un punto trabajamos sobre lo mismo y eso genero un conflicto en el github pero se soluciono con los debidos comandos.
6. ¿Qué hicieron bien como equipo? ¿Qué se comprometen a hacer para mejorar los resultados?
Hicimos bien como equipo el de tener una comunicación efectiva y sin problemas entre nosotros y para mejorar resultados nos comprometemos a hacer un mejor manejo de tiempo y planeacion.
7. Considerando las prácticas XP incluidas en los laboratorios. ¿cuál fue la más útil? ¿por qué?
La más util fue la de Pair-Programming puesto que al estar los dos alineados trabajando simultaneamente pudimos plantear correctamente que era lo que queriamos hacer y de que forma, pudimos evitar algunos errores y solucionar problemas y estar de acuerdo con lo que estabamos haciendo. Tambien usamos de la codificar pruebas buenas y anteriores a la escritura de código.
8. ¿Qué referencias usaron? ¿Cuál fue la más útil? Incluyan citas con estándares adecuados.
La referencias utilizadas fueron principalmente el enunciado del problema: https://drive.google.com/file/d/1RskN-ze_icvo5kB5he7FlRIbt4ECkt2G/view, el material de clase presente en moodle como fueron las diapositivas y las notas de clase, consultamos paginas como el API de java y otras paginas oficiales para estar alineados con los estandares de Java.

## Ciclo 3
1. ¿Cuáles fueron los mini-ciclos definidos?
Los mini-ciclos definidos fueron los de la implementacion de las clases TowerContest para solucionar el problema de la maraton, complementar el diseño en astah y volver a codificar pruebas para probar todo lo que se desarrolló
2. ¿Cuál es el estado actual del proyecto en términos de mini-ciclos? ¿por qué?
El estado es que esta cubierto todo lo definido y todo lo que se requiere en el enunciado del ciclo 3, puesto que se cubren los requisitos funcionales, se solucuona el problema y se hace la simulacion como se pide por lo que consideramos que el estado es el de estar completo.
3. ¿Cuál fue el tiempo total invertido por cada uno de ustedes? (Horas/Hombre)
El tiempo total fue de: Cristian 7 horas, David 7 horas.
4. ¿Cuál consideran fue el mayor logro? ¿Por qué?
El mayor logro fue el de igual que el ciclo anterior tener una buena comunicacion y aparte de esto el de haber podido realizar todo lo que se pedia en este ciclo en cuanto a codigo y modelado, por lo que es un cambio respecto al ciclo anterior, lo logramos debido a un mejor manejo de tiempo y mejor planeación por lo que asi se pudo lograr la entrega a tiempo y completa de lo que se pedia.
5. ¿Cuál consideran que fue el mayor problema técnico? ¿Qué hicieron para resolverlo?
No hubo ningun problema tecnico en esta entrega.
6. ¿Qué hicieron bien como equipo? ¿Qué se comprometen a hacer para mejorar los resultados?
Hicimos bien como equipo el de tener una comunicación efectiva y sin problemas entre nosotros y para mejorar resultados nos comprometemos a hacer un mejor manejo de tiempo y planeacion para seguir entregando nuestro trabajo completo, ordenado y correcto como lo venimos haciendo
7. Considerando las prácticas XP incluidas en los laboratorios. ¿cuál fue la más útil? ¿por qué?
La más util fue la de Pair-Programming puesto que al estar los dos alineados trabajando simultaneamente pudimos plantear correctamente que era lo que queriamos hacer y de que forma, pudimos evitar algunos errores y solucionar problemas y estar de acuerdo con lo que estabamos haciendo. Tambien usamos de la codificar pruebas buenas y anteriores a la escritura de código para hacer una construccion de logica correcta.
8. ¿Qué referencias usaron? ¿Cuál fue la más útil? Incluyan citas con estándares adecuados.
La referencias utilizadas fueron principalmente el enunciado del problema: https://drive.google.com/file/d/1RskN-ze_icvo5kB5he7FlRIbt4ECkt2G/view, el material de clase presente en moodle como fueron las diapositivas y las notas de clase, consultamos paginas como el API de java y otras paginas oficiales para estar alineados con los estandares de Java.

## Ciclo 4 
1. ¿Cuáles fueron los mini-ciclos definidos?
Los mini-ciclos para este enunciado fueron en primer lugar el de refactorizar el código con lo que nos pedian ahora, rediseñar en el modelo de astah, codificar nuevas pruebas de unidad y pruebas de aceptación y finalmente el de probar que se estuviera cumpliendo los requisitos que se pedian para esta entrega.
2. ¿Cuál es el estado actual del proyecto en términos de mini-ciclos? ¿por qué?
Consideramos que esta casi en su totalidad pues cumplomos con las cosas como codificar las nuevas pruebas, los nuevos requisitos fueron cubiertos pero hizo falta algunas cosas en la herramienta astah por lo que por eso decimos que esta casi en su totalidad.
3. ¿Cuál fue el tiempo total invertido por cada uno de ustedes? (Horas/Hombre)
El tiempo total fue de: Cristian 9 horas, David 9 horas.
4. ¿Cuál consideran fue el mayor logro? ¿Por qué?
El mayor logro fue el de igual que el ciclo anterior tener una buena comunicacion y aparte de esto el de haber podido realizar todo lo que se pedia en este ciclo en cuanto a codigo y modelado, por lo que es un cambio respecto al ciclo anterior, lo logramos debido a un mejor manejo de tiempo y mejor planeación por lo que asi se pudo lograr la entrega a tiempo y completa de lo que se pedia.
5. ¿Cuál consideran que fue el mayor problema técnico? ¿Qué hicieron para resolverlo?
El mayor problema fue el de al hacer la refactorización de una buena parte del código hacer esta misma refactorización en el diseño de astah pues tuvimos incosistencias en varios metodos y puntos del proyecto, para solucionarlo se tuvo que ir uno por uno por mas demorado que fuera para poderlo solucionar.
6. ¿Qué hicieron bien como equipo? ¿Qué se comprometen a hacer para mejorar los resultados?
Hicimos bien como equipo el de tener una comunicación efectiva y sin problemas entre nosotros y para mejorar resultados nos comprometemos a hacer un mejor manejo de tiempo y planeacion para seguir entregando nuestro trabajo completo, ordenado y correcto como lo venimos haciendo
7. Considerando las prácticas XP incluidas en los laboratorios. ¿cuál fue la más útil? ¿por qué?
La más util fue la de Pair-Programming puesto que al estar los dos alineados trabajando simultaneamente pudimos plantear correctamente que era lo que queriamos hacer y de que forma, pudimos evitar algunos errores y solucionar problemas y estar de acuerdo con lo que estabamos haciendo. Tambien usamos de la codificar pruebas buenas y anteriores a la escritura de código para hacer una construccion de logica correcta.
8. ¿Qué referencias usaron? ¿Cuál fue la más útil? Incluyan citas con estándares adecuados.
La referencias utilizadas fueron principalmente el enunciado del problema: https://drive.google.com/file/d/1RskN-ze_icvo5kB5he7FlRIbt4ECkt2G/view, el material de clase presente en moodle como fueron las diapositivas y las notas de clase, consultamos paginas como el API de java y otras paginas oficiales para estar alineados con los estandares de Java.

## Entrega Final 
1. ¿Cuáles fueron los mini-ciclos definidos?
El mini-ciclo definido fue el de hacer la respectiva transición de bluej y como se venia trabajando el proyecto a la herramienta de Eclipse puesto que esto era lo que se requeria. El otro mini-ciclo fue el de hacer el refactor de las cosas pendientes o que necesitaban ajuste y por ultimo la codificacion de las pruebas para hacer la cobertura del código requerido en el enunciado ( 75% )
2. ¿Cuál es el estado actual del proyecto en términos de mini-ciclos? ¿por qué?
El estado actual es que consideramos que esta completo todo lo que se pedia puesto que hicimos la respectiva transición al Eclipse, se completo el diseño en astah, se realizaron los informes respectivos y lo que entregamos funciona.
3. ¿Cuál fue el tiempo total invertido por cada uno de ustedes? (Horas/Hombre)
El tiempo total fue de: Cristian 14 horas, David 14 horas.
4. ¿Cuál consideran fue el mayor logro? ¿Por qué?
El mayor logro fue el de haber hecho la transición efectiva a esta nueva herramienta, haber podido entregar todo en el tiempo establecido y en la forma que se pedia y en general que pudimos entregar el proyecto bien.
5. ¿Cuál consideran que fue el mayor problema técnico? ¿Qué hicieron para resolverlo?
Tuvimos el problema que en la transición al ser un editor distinto algunas cosas fallaban y no entendiamos porque pues esto si estaba funcionando en el bluej y para solucuonarlo tuvimos que ver tutoriales de uso de esta herramienta y hacer refactorizaciones y reorganizaciones concretas.
6. ¿Qué hicieron bien como equipo? ¿Qué se comprometen a hacer para mejorar los resultados?
Hicimos bien como equipo el de tener una comunicación efectiva y sin problemas entre nosotros por lo que pudimos entregar todo en los tiempos establecidos y de manera efectiva. Para mejorar resultados nos comprometemos a hacer un mejor manejo de tiempo y planeacion para seguir entregando nuestro trabajo completo, ordenado y correcto como lo venimos haciendo y asi poder lograr un buen proyecto final
7. Considerando las prácticas XP incluidas en los laboratorios. ¿cuál fue la más útil? ¿por qué?
La más util fue la de Pair-Programming puesto que al estar los dos alineados trabajando simultaneamente pudimos plantear correctamente que era lo que queriamos hacer y de que forma, pudimos evitar algunos errores y solucionar problemas y estar de acuerdo con lo que estabamos haciendo. Tambien usamos de la codificar pruebas buenas y anteriores a la escritura de código para hacer una construccion de logica correcta.
8. ¿Qué referencias usaron? ¿Cuál fue la más útil? Incluyan citas con estándares adecuados.
La referencias utilizadas fueron principalmente el enunciado del problema: https://drive.google.com/file/d/1RskN-ze_icvo5kB5he7FlRIbt4ECkt2G/view, el material de clase presente en moodle como fueron las diapositivas y las notas de clase, consultamos paginas como el API de java y otras paginas oficiales para estar alineados con los estandares de Java, vimos tutotiales sobre el uso de esta nueva herramienta de Eclipse y por último todo el contexto de los demás ciclos.


























