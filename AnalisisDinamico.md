# Informe de Análisis Dinámico 
## Autores Cristian Santiago Moreno Ruiz/ David Alejandro Contreras Delgado

## 1. Introducción 

El presente documento es el informe de analisis dinamico sobre nuestro proyecto de la solución del problema J Stacking Cups de la maratón de programación del año 2025 ( consultar el [README](./README.md)para más información del enunciado ). Este analisis se llevó a cabo utilizando pruebas unitarias con JUnit y la herramienta JaCoCo para medir el cubrimiento del código. Gracias a esto se pudo identificar como se mencionó el cubrimiento del código del dominio, las debilidades existentes sobre las pruebas de unidad que se tenian y asi se pudo alcanzar el objetivo propuesto en el enunciado ( cubrimiento > 75 % ).

## 2. Resultado Inicial 

Se realizó la respectiva ejecución con la herramienta Jacoco sobre nuestro proyecto para obtener el estado actual en el que nos encontrabamos y se obtuvo que estabamos en un cubrimiento del 29,6 %, donde al tener un total de 5450 de instrucciones solo teniamos cubiertas un número de 1614 de estas. Adjuntamos imagen de la prueba respectiva:
<img width="1091" height="242" alt="image" src="https://github.com/user-attachments/assets/48bebb54-3cf0-4d1c-aca1-a0cc6368c8cc" />

Al obtener esto nos dimos cuenta de la realidad que era nuestra baja cobertura de nuestro código por lo que al hacer el análisis detallado de lo que teniamos y lo que faltaba obtuvimos hallazgos interesantes entre los cuales están que teniamos varios métodos de sobre todo los ciclos 1 y 2, que al ser muy simples no contaban con pruebas unitarias para ellos pues al pensar que son métodos muy básicos no los estabamos probando y esto restaba cobertura. Otro hallazgo importante es que para los ciclos 3 y 4 al haber dos clases de pruebas estabamos realizando algunas pruebas duplicadas para un método entonces se estaban "desperdiciando" pruebas al cubrir un mismo metodo de la misma forma. Por otro lado en ciclo 4 al haber métodos robustos y complejos no se estaban cubriendo de forma efectiva pues no se estaban tomando todos los casos posibles y los casos borde haciendo que la cobertura bajara. Por lo que en general nos dimos cuenta que hacia falta cubrir varias cosas que no se estaban tomando en cuenta previamente.

## 3. Análisis del Problema 

Como se mencionó previamente al obtener los resultados de cobertura y al realizar la busqueda de los puntos especificos de falta de cobertura unido a los hallazgos explicados se llegaron a los siguientes puntos de causas:
- Ausencia de pruebas para la lógica compleja de ciclo 3 y 4
- Ausencia de pruebas de métodos muy básicos o sencillos de ciclo 1 y 2
- Pruebas redundantes que no aportan
- Cobertura superficial donde no se estaban priorizando escenarios mas complejos o específicos del comportamiento del sistema
- 

## 4. Decisiones Tomadas 

Con base en los puntos mencionados en el punto anterior junto con los hallazgos nombrados se tomaron las decisiones de solucionar esto haciendo un refactor de las pruebas unitarias existentes, donde algunas fueron modificadas para ser más específicas para el método en cuestión que se estaba probando, se codificaron nuevas pruebas para estos métodos básicos que mencionamos en los puntos anteriores para subir el porcentaje de cubrimiento, se eliminaron estas mencionadas pruebas duplicadas quen no estaban aportando nada, se incluyeron las pruebas para estos métodos complejos de los dos últimos ciclos y por último se incluyeron casos de pruebas más robustos y representativos para el sistema para así lograr una mejor cobertura. 

Al realizar esto se buscaba aparte de un mayor cubrimiento del código del dominio, lograr mejor calidad de pruebas y si era necesario modificar ciertas cosas en código al revisar estos casos borde y casos específicos y así tener unas pruebas completas y buenas.


## 5. Resultado Final 

Al hacer este mencionado refactor de las pruebas e inclusión de nuevos casos, se logró que pasaran cada una de estas respectivas pruebas y luego el siguiente paso fue el de volver a utilizar la herramienta de Jacoco y se obtuvo lo siguiente:

<img width="1437" height="233" alt="image" src="https://github.com/user-attachments/assets/3c8a2da2-1248-49b5-a52b-acafd412c9d2" />

Se obtuvo como se observa en la imagen evidencia un porcentaje del 75,9% logrando así el objetivo del enunciado del minimo de cobertura para el código de dominio. Por lo que con esto podemos decir que las decisiones tomadas y el analisis de los puntos donde habian falencias fue existoso para subir la calidad y cobertura de nuestro código. Por lo que tambien podemos decir que se incrementó la representación real del sistema por medio de estas modificaciones y adiciones realizadas.

## 6. Conclusiones 

- Al realizar este análisis dinámico se pudieron identificar deficiencias en el diseño que teniamos incialmente en nuestras pruebas y en nuestro sistema por lo que nos damos cuenta que esta es una herramienta poderosa para probar calidad de código y de nuestro proyecto en general.
-  El uso de esta herramienta Jacoco facilita la visualización del estado de un proyecto y ayuda a la toma de decisiones efectiva para mejorar la calidad del software que se está realizando
-  Es evidente que el 75,9% no representa un cobertura perfecta pero es un gran avance para el punto donde nos encontrabamos incialmente.
-  Se puede mejorar la calidad y el cubrimiento del sistema por medio de este analisis dinámico y también con el análisis estático como se pudo observar en ambos informes.
-  El proceso de análisis dinámico mostró que un alto porcentaje de cobertura no depende únicamente de la cantidad de pruebas, sino de su calidad y pertinencia frente a la lógica del sistema.
-  El cubrimiento debe ser general, no unicamente de casos complejos, los casos simples tambien se deben probar y no dar por hecho. 







