# Equipo
## Integrantes
| Nombre | Usuario GitHub | Rol |
| --- | --- | --- |
| Alan Lopez Cruz | alansudopacman-2206 | Estudiante A |
| Ian Y Rodriguez Ojeda | i1521488 | Estudiante B |

## Fecha de inicio de práctica
03/Septiembre/2026

## 1. Descripción del problema

Es un sistema para monitorear tanques de almacenamiento. Cada uno tendrá
una identificación (id) para identificarlo, una capacidad máxima, un nivel actual de contenido y tendrá un
estado (LLENANDO, Vaciando, Detenido), además cada tanque tendrá un sensor que se encargará de medir el nivel.

El sistema tendrá que poder llenar, vaciar y detenerlo y mostrar la información (nivel, porcentaje de llenado y estado), además el nivel del tanque nunca puede ser menor a 0 o mayor a su capacidad maxima.
## 2. Identificación de objetos
**Tanque**
Es el tanque de almacenamiento. Se penso como un objeto porque incluira una identidad (id), un estado que 
cambia cone el tiempo (nivel, llenado, vaciado o detenido) y comportamientos. Su responsabilidad es representar el tanque real y 
asegurarse de que su nivel nunca salga de sus límites permitidos.
**SensorNivel**
Es el senor que mide el nivel del tanque. Este mide y guarda la información sobre cuál es el nivel actual, este debe estar asociado
aún tanque para obtener la información antes mencionada.
## 3. Estado y comportamiento

| Objeto propuesto | Responsabilidad | Información que debe conservar | Comportamientos que debe realizar |
| --- | --- | --- | --- |
| Tanque | Controlar el estado de un tanque de almacenamiento y asegurar que su nivel se mantenga dentro de los límites permitidos | Identificador, capacidad máxima, nivel actual, estado de operación | Llenarse, vaciarse, detenerse, decir su nivel actual, decir su porcentaje de llenado y decir su estado actual |
| SensorNivel | Obtener y reportar una lectura del nivel del tanque al que está asociado | Identificador del sensor, última lectura tomada | Realizar una lectura del tanque asociado, dar el valor medido y decir si esa lectura está dentro de un rango válido |

## 4. Relaciones entre los objetos

Tanque y SensorNivel tienen que colaborar entre sí para que todo funcione. El sensor necesita "conocer" al tanque que tiene asignado, porque su trabajo es justamente leer el nivel de ese tanque en específico. Por eso, SensorNivel necesita tener una referencia al objeto Tanque correspondiente, para poder consultar su nivel actual cada vez que hace una lectura.

Esto tiene sentido porque, en la vida real, un sensor siempre está conectado a un tanque en particular; no tendría caso que un sensor diera lecturas sin estar asociado a ninguno.

También cuidamos que ninguna de las dos clases repitiera responsabilidades. Por ejemplo, el SensorNivel no debería guardar su propio valor del nivel de forma independiente, porque si el tanque cambia de nivel (al llenarse o vaciarse) y el sensor no se entera, sé dé sincronizarían. Por eso el sensor siempre consulta directamente al tanque al momento de leer, de modo que Tanque sigue siendo el único que controla y valida su propio nivel, y SensorNivel solo se encarga de obtener y reportar esa lectura.

## 5. Diseño de clases

| Clase | Atributos propuestos | Tipo de dato | Métodos propuestos | Responsabilidad |
| --- | --- | --- | --- | --- |
| Tanque | id, capacidadMaxima, nivelActual, estado | String, double, double, EstadoTanque (enum) | llenar(double cantidad), vaciar(double cantidad), detener(), getNivelActual(), getPorcentajeLlenado(), getEstado(), getInfoGeneral() | Representar el estado físico de un tanque de almacenamiento y controlar su contenido, cuidando que su nivel se mantenga siempre entre 0 y su capacidad máxima |
| SensorNivel | id, ultimaLectura, tanqueAsociado | String, double, Tanque | realizarLectura(), esLecturaValida(), getUltimaLectura(), getTanqueAsociado() | Obtener y reportar la lectura del nivel del tanque que tiene asociado, verificando si esa lectura es válida, sin alterar ni duplicar el estado interno del tanque |

## 6. Diagrama UML inicial

![Diagrama UML inicial](https://github.com/alansudopacman-2206/sistema-monitoreo-java/blob/master/images/uml-inicial.png)

## 7. Justificación del diseño

**1. ¿Por qué propusieron esas clases?**

Elegimos Tanque y SensorNivel, más la enumeración EstadoTanque, porque son las piezas principales del problema:
- Tanque representa el recipiente físico y controla sus límites y operaciones.
- SensorNivel representa el dispositivo que mide y reporta el estado del tanque, de forma independiente.
- EstadoTanque nos sirve para limitar los estados posibles (DETENIDO, LLENÁNDOSE, VACIÁNDOSE) usando un enum en vez de manejar texto libre, que podría dar lugar a errores o inconsistencias.

**2. ¿Cuál es la responsabilidad principal de cada clase?**

- Tanque: mantener su estado interno consistente, ejecutando el llenado, vaciado y detención sin dejar que su nivel salga del rango entre 0 y su capacidad máxima.
- SensorNivel: consultar y reportar en tiempo real la lectura del tanque que tiene asociado, y verificar si esa lectura es válida, sin tocar el estado del tanque.
- EstadoTanque: acotar los estados que puede tener un tanque a un conjunto cerrado y válido.

**3. ¿Por qué determinados atributos fueron definidos como privados?**

Todos los atributos (id, capacidadMaxima, nivelActual, estado, ultimaLectura, tanqueAsociado) los pusimos como prívate para aplicar encapsulamiento. Así evitamos que desde fuera se pueda modificar directamente algo crítico, como poner un nivelActual negativo o mayor a la capacidad, obligando a que cualquier cambio pase por los métodos de la clase que sí validan esos límites.

**4. ¿Qué información decidieron proporcionar mediante los constructores?**

- Tanque(id, capacidadMaxima, nivelInicial): pedimos estos tres datos para que el tanque quede creado en un estado válido desde el inicio.
- SensorNivel(id, tanqueAsociado): pedimos el identificador del sensor y la referencia al tanque que va a monitorear, para que no pueda existir un sensor sin tanque asignado.

**5. ¿Qué objetos se relacionan entre sí y por qué?**

Hay una relación de un solo sentido: SensorNivel apunta hacia Tanque. El sensor guarda una referencia al tanque para poder llamar a sus métodos de consulta (como getNivelActual()). Esto representa lo que pasaría en la realidad, donde un sensor depende de que exista un tanque para poder medir algo.

**6. ¿Qué decisiones tomaron para evitar duplicar responsabilidades?**

Decidimos que SensorNivel no guardara su propia copia del nivel del tanque. En vez de eso, cada vez que se llama a realizarLectura(), el sensor va y consulta directamente al Tanque. Así el Tanque sigue siendo la única fuente confiable de su nivel, y no hay riesgo de que se des actualice la información del sensor cuando el tanque cambia.

**7. ¿Qué parte del diseño fue discutida entre ambos integrantes y qué decisión tomaron?**

Platicamos si la validación de límites (que el nivel no baje de 0 ni pase la capacidad máxima) debía estar en el SensorNivel o en el Tanque. Al final decidimos que esa responsabilidad fuera únicamente del Tanque, porque es quien controla su propio estado; el SensorNivel solo se limita a consultar y decir si la lectura obtenida está dentro de lo esperado, sin meterse en la validación de límites.


