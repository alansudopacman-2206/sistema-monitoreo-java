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

Las operaciones que el sistema debe permitir realizar son: 
consultar la información general de un tanque, iniciar su llenado, iniciar su vaciado, detener su operación, consultar su nivel actual, calcular y consultar su porcentaje de llenado, consultar su estado actual, 
y obtener una lectura mediante el sensor asociado.

Finalmente, el sistema debe respetar una restricción importante para que la simulación sea realista: 
el nivel de un tanque nunca puede ser negativo ni puede superar su capacidad máxima. Esto significa que, aunque se intente llenar un tanque más allá de su límite o vaciarlo por debajo de cero, el sistema debe evitarlo y mantener siempre valores válidos.

## 2. Identificación de objetos
A partir de la descripción del problema, identificamos dos elementos principales que pueden representarse como objetos:

Tanque

Representa un tanque físico de almacenamiento dentro de la instalación. 
Consideramos que debe existir como objeto porque tiene identidad propia (cada tanque se distingue de los demás mediante 
un identificador), conserva un estado propio que cambia con el tiempo (nivel actual y estado de operación), 
y tiene comportamientos claros asociados a él, como llenarse, vaciarse o detenerse. Su responsabilidad dentro del sistema es representar fielmente la situación real de un tanque y garantizar que su nivel 
siempre se mantenga dentro de los límites válidos (entre cero y su capacidad máxima).

SensorNivel

Representa el sensor encargado de medir el 
nivel de un tanque. Lo consideramos un objeto independiente del tanque porque su responsabilidad es distinta: 
no almacena contenido ni cambia de estado como el tanque, 
sino que su función es obtener y reportar una lectura del nivel. 
Separar esta responsabilidad del tanque nos permite mantener el diseño más ordenado, 
ya que si en el futuro se agregan otros tipos de sensores o validaciones de lectura, esos cambios no deberían afectar directamente la lógica interna del tanque. 
Su responsabilidad dentro del sistema es realizar lecturas y permitir verificar si esas lecturas están dentro de un rango considerado válido.

