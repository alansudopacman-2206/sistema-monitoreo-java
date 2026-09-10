# Equipo
## Integrantes
| Nombre | Usuario GitHub | Rol |
| --- | --- | --- |
| Alan Lopez Cruz | alansudopacman-2206 | Estudiante A |
| Ian Y Rodriguez Ojeda | i1521488 | Estudiante B |

## Fecha de inicio de práctica
03/Septiembre/2026

## 1. Descripción del problema

Lo que queremos representar es una pequeña instalación industrial que tiene varios tanques de almacenamiento, cada uno con su propio sensor para saber cuánto contenido tiene en un momento dado. La idea es simular, desde consola, el funcionamiento básico de esos tanques dentro de un proceso automatizado.

Por cada tanque necesitamos manejar:

- Un identificador para poder distinguirlo de los demás.
- La capacidad máxima que puede almacenar.
- El nivel actual que tiene en ese momento.
- El estado en el que se encuentra (detenido, llenando o vaciando).

Además de eso, el sistema también debe contemplar un sensor de nivel que se encarga de leer el contenido del tanque, tal como pasaría en un proceso real de automatización.

El programa tiene que poder hacer lo siguiente: mostrar la información general de un tanque, empezar a llenarlo, empezar a vaciarlo, detenerlo, decir cuál es su nivel actual, calcular y mostrar su porcentaje de llenado, decir en qué estado está, y obtener una lectura a través del sensor que tiene asociado.

Por último, hay una restricción que es clave para que la simulación tenga sentido: el nivel de un tanque no puede bajar de cero ni pasarse de su capacidad máxima. O sea que si alguien intenta llenarlo de más o vaciarlo de menos, el programa tiene que evitarlo y mantener siempre un valor válido.

## 2. Identificación de objetos

Viendo el problema, nos dimos cuenta de que hay dos cosas que claramente pueden ser objetos:

**Tanque**

Es el tanque de almacenamiento como tal. Nos parece que tiene que ser un objeto porque cada uno tiene su propia identidad (se distinguen por su id), tiene un estado que va cambiando (el nivel y si está llenando, vaciando o detenido), y tiene comportamientos propios como llenarse o vaciarse. Su trabajo dentro del sistema es representar bien lo que pasaría con un tanque real y asegurarse de que su nivel nunca se salga de los límites permitidos.

**SensorNivel**

Es el sensor que mide el nivel del tanque. Decidimos que fuera un objeto aparte porque hace algo distinto al tanque: no guarda contenido ni cambia de estado, solo se encarga de leer y reportar el nivel. Nos pareció buena idea separarlo porque si más adelante se necesitan otros tipos de sensores o validaciones distintas, eso no debería afectar la lógica interna del tanque. Su función dentro del sistema es hacer lecturas y decir si esas lecturas están dentro de un rango que consideremos válido.

## 3. Estado y comportamiento

| Objeto propuesto | Responsabilidad | Información que debe conservar | Comportamientos que debe realizar |
| --- | --- | --- | --- |
| Tanque | Controlar el estado de un tanque de almacenamiento y asegurar que su nivel se mantenga dentro de los límites permitidos | Identificador, capacidad máxima, nivel actual, estado de operación | Llenarse, vaciarse, detenerse, decir su nivel actual, decir su porcentaje de llenado y decir su estado actual |
| SensorNivel | Obtener y reportar una lectura del nivel del tanque al que está asociado | Identificador del sensor, última lectura tomada | Realizar una lectura del tanque asociado, dar el valor medido y decir si esa lectura está dentro de un rango válido |

## 4. Relaciones entre los objetos

Tanque y SensorNivel tienen que colaborar entre sí para que todo funcione. El sensor necesita "conocer" al tanque que tiene asignado, porque su trabajo es justamente leer el nivel de ese tanque en específico. Por eso, SensorNivel necesita tener una referencia al objeto Tanque correspondiente, para poder consultar su nivel actual cada vez que hace una lectura.

Esto tiene sentido porque, en la vida real, un sensor siempre está conectado a un tanque en particular; no tendría caso que un sensor diera lecturas sin estar asociado a ninguno.

También cuidamos que ninguna de las dos clases repitiera responsabilidades. Por ejemplo, el SensorNivel no debería guardar su propio valor del nivel de forma independiente, porque si el tanque cambia de nivel (al llenarse o vaciarse) y el sensor no se entera, se desincronizarían. Por eso el sensor siempre consulta directamente al tanque al momento de leer, de modo que Tanque sigue siendo el único que controla y valida su propio nivel, y SensorNivel solo se encarga de obtener y reportar esa lectura.

## 5. Diseño de clases

| Clase | Atributos propuestos | Tipo de dato | Métodos propuestos | Responsabilidad |
| --- | --- | --- | --- | --- |
| Tanque | id, capacidadMaxima, nivelActual, estado | String, double, double, EstadoTanque (enum) | llenar(double cantidad), vaciar(double cantidad), detener(), getNivelActual(), getPorcentajeLlenado(), getEstado(), getInfoGeneral() | Representar el estado físico de un tanque de almacenamiento y controlar su contenido, cuidando que su nivel se mantenga siempre entre 0 y su capacidad máxima |
| SensorNivel | id, ultimaLectura, tanqueAsociado | String, double, Tanque | realizarLectura(), esLecturaValida(), getUltimaLectura(), getTanqueAsociado() | Obtener y reportar la lectura del nivel del tanque que tiene asociado, verificando si esa lectura es válida, sin alterar ni duplicar el estado interno del tanque |

## 6. Diagrama UML inicial

![Diagrama UML inicial](uml-inicial.png)

## 7. Justificación del diseño

**1. ¿Por qué propusieron esas clases?**

Elegimos Tanque y SensorNivel, más la enumeración EstadoTanque, porque son las piezas principales del problema:
- Tanque representa el recipiente físico y controla sus límites y operaciones.
- SensorNivel representa el dispositivo que mide y reporta el estado del tanque, de forma independiente.
- EstadoTanque nos sirve para limitar los estados posibles (DETENIDO, LLENANDOSE, VACIANDOSE) usando un enum en vez de manejar texto libre, que podría dar lugar a errores o inconsistencias.

**2. ¿Cuál es la responsabilidad principal de cada clase?**

- Tanque: mantener su estado interno consistente, ejecutando el llenado, vaciado y detención sin dejar que su nivel salga del rango entre 0 y su capacidad máxima.
- SensorNivel: consultar y reportar en tiempo real la lectura del tanque que tiene asociado, y verificar si esa lectura es válida, sin tocar el estado del tanque.
- EstadoTanque: acotar los estados que puede tener un tanque a un conjunto cerrado y válido.

**3. ¿Por qué determinados atributos fueron definidos como privados?**

Todos los atributos (id, capacidadMaxima, nivelActual, estado, ultimaLectura, tanqueAsociado) los pusimos como private para aplicar encapsulamiento. Así evitamos que desde fuera se pueda modificar directamente algo crítico, como poner un nivelActual negativo o mayor a la capacidad, obligando a que cualquier cambio pase por los métodos de la clase que sí validan esos límites.

**4. ¿Qué información decidieron proporcionar mediante los constructores?**

- Tanque(id, capacidadMaxima, nivelInicial): pedimos estos tres datos para que el tanque quede creado en un estado válido desde el inicio.
- SensorNivel(id, tanqueAsociado): pedimos el identificador del sensor y la referencia al tanque que va a monitorear, para que no pueda existir un sensor sin tanque asignado.

**5. ¿Qué objetos se relacionan entre sí y por qué?**

Hay una relación de un solo sentido: SensorNivel apunta hacia Tanque. El sensor guarda una referencia al tanque para poder llamar a sus métodos de consulta (como getNivelActual()). Esto representa lo que pasaría en la realidad, donde un sensor depende de que exista un tanque para poder medir algo.

**6. ¿Qué decisiones tomaron para evitar duplicar responsabilidades?**

Decidimos que SensorNivel no guardara su propia copia del nivel del tanque. En vez de eso, cada vez que se llama a realizarLectura(), el sensor va y consulta directamente al Tanque. Así el Tanque sigue siendo la única fuente confiable de su nivel, y no hay riesgo de que se desactualice la información del sensor cuando el tanque cambia.

**7. ¿Qué parte del diseño fue discutida entre ambos integrantes y qué decisión tomaron?**

Platicamos si la validación de límites (que el nivel no baje de 0 ni pase la capacidad máxima) debía estar en el SensorNivel o en el Tanque. Al final decidimos que esa responsabilidad fuera únicamente del Tanque, porque es quien controla su propio estado; el SensorNivel solo se limita a consultar y decir si la lectura obtenida está dentro de lo esperado, sin meterse en la validación de límites.