# Equipo
## Integrantes
/ Nombre / Usuario GitHUb / Rol /
/ --- / --- / --- /
/Alan Lopez Cruz/alansudopacman-2206/Estudiante A/
/Ian Y Rodriguez Ojeda/i1521488/Estudiante B/

##Fecha de inicio de practica
03/Septiembre/2026

## 1. Descripción del problema

El sistema que se busca representar es el monitoreo de un conjunto de tanques de almacenamiento dentro de una pequeña instalación industrial. Cada tanque forma parte de un proceso de automatización sencillo, en el que se necesita conocer en todo momento cuánto contenido tiene almacenado y qué operación está realizando.

La información que el sistema necesita manejar por cada tanque es:

Un identificador que permita distinguirlo de los demás.
Su capacidad máxima, es decir, la cantidad límite de líquido que puede almacenar.
Su nivel actual, que indica cuánto contenido tiene en un momento dado.
Su estado de operación, que indica si el tanque está detenido, llenándose o vaciándose.

Además del tanque, el sistema debe considerar un sensor de nivel, encargado de obtener lecturas del contenido del tanque, de manera similar a como funcionaría en un proceso real de automatización.

Las operaciones que el sistema debe permitir realizar son: consultar la información general de un tanque, iniciar su llenado, iniciar su vaciado, detener su operación, consultar su nivel actual, calcular y consultar su porcentaje de llenado, consultar su estado actual, y obtener una lectura mediante el sensor asociado.

Finalmente, el sistema debe respetar una restricción importante para que la simulación sea realista: el nivel de un tanque nunca puede ser negativo ni puede superar su capacidad máxima. Esto significa que, aunque se intente llenar un tanque más allá de su límite o vaciarlo por debajo de cero, el sistema debe evitarlo y mantener siempre valores válidos.

