# Simulador de Stacking Cups Con Tapas 
# Autores Cristian Santiago Moreno y David Alejandro Contreras
# Desarrollo orientado por objetos 2026-1

##Descripción del Problema: Este proyecto es una implementación básica con simulador del problema J de la ICPC ( International Collegiate Programming Contest, el cual citando el enunciado nos dice lo siguiente: " You have a collection of n cylindrical cups, where the ith cup is 2i−1 cm tall. The cups have increasing diameters, such that cup i fits inside cup j if and only if i < j. The base of each cup is 1 cm thick (which makes the smallest cup rather useless as it is only 1 cm tall, but you keep it for sentimental reasons). After washing all the cups, you stack them in a tower. Each cup is placed upright (in other words, with the opening at the top) and with the centers of all the cups aligned vertically. The height of the tower is defined as the vertical distance from the lowest point on any of the cups to the highest. You would like to know in what order to place the cups such that the final height (in cm) is your favorite number. Note that all n cups must be used. For example, suppose n = 4 and your favorite number is 9. If you place the cups of heights 7, 3, 5, 1, in that order, the tower will have a total height of 9, as shown in Figure J.1." 
Si necesita más claridad en cuanto a la imagen o en cuanto al enunciado teniendo en cuenta sus inputs o outputs, consultar el siguiente link que lo remitirá al pdf oficial con el enunciado : https://drive.google.com/file/d/1RskN-ze_icvo5kB5he7FlRIbt4ECkt2G/view

##Estructura del Proyecto: El proyecto se encuentra organizado en 4 paquetes principales (Viendole desde el bluej antes de pasarlo a eclipse), donde esta en primer lugar el paquete de StackingItems, el cual tiene contenida toda la lógica del dominio, siendo sus clases principales las de Tower, Cup, Lid y TowerBackGround. El siguiente paquete es el de shapes, el cual es el que contiene el manejo de figuras para representar estas Cups y Lids que son lo básico para la representación visual del simulador. El paquete de contest es el encargado de contener la lógica de solución del problema contenida en la clase TowerContest, y por ultimo el paquete de Test que es el que contiene las pruebas unitarias de los metodos presentes en las clases y las debidas pruebas de aceptación. Como se aclaro esta estructura es viendola desde la organización que teniamos en el bluej y esto se puede consultar en el modelo astah en el diagrama de paquetes, al hacer la transición a la herramienta eclipse cambia esta organización por lo que esta estructura solo es la guia para que entienda la organizacion.

##Funcionalidades: Las funcionalidades que ofrece este simulador y alineados a los diferentes ciclos trabajados es que ofrece en terminos generales es la de Crear torres de tazas, agregar y remover tazas y tapas, reorganizar la torre, consultar informacion de la torre y simular la solución del problema ( ver documento si necesita mas claridad ).

##Herramientas utilizadas para el proyecto: Las herramientas usadas para el desarrollo del proyecto y la debida entrega fueron las de principalmente el lenguaje Java, el editor Bluej, el  Eclipse IDE , JUnit ( Para las pruebas unitarias ), Jacoco ( Para el analisis de cobertura), PMD ( Para calidad de código ) y por último el modelado UML con la herramienta Astah.


##Ejecución: Para hacer la ejecución del programa debe hacer lo siguiente:
1. Se debe importar el programa en Eclipse.
2. Ejecutar las pruebas que hay en el paquete de Tes.
3. Ejecutar la clase de TowerContest para simular los casos del problema.
4. Si desea realizar otras acciones de las funcionalidades, puede agregar una torre del tamaño que desea y ejecutar metodo para ver las funcionalidades mencionadas.

##Pruebas: El proyecto como se mencionó cuenta con pruebas unitarias que validan la lógica del dominio y para medir esto se utilizó el Jacoco para ver la cobertura del código que debía ser superior al 75%.

##Analisis: El análisis dinámico y estático del código permitieron mejorar la cobertura del código y corregir problemas de calidad. Para mas detalles leer los archivos de AnalisisDinamico.md y AnalisisEstatico.md


##Retrospectivas








