#Ejercicio de MongoDB para la asignatura Acceso a Datos. Enunciado propuesto:

Realizar una aplicación que conecte con una base de datos NoSQL (MongoDB), según los requisitos que se enumeran a continuación

Requisitos mínimos y Otras funcionalidades suplementarias (sugerencias)
~~~~~~~~~~~~~~~~~~~~~~~~~~~
~~~~~~~~~~~~~~~~~~~~~~~~~~~

-  Se podrán producir altas de documentos, de uno en uno o en grupo.
-  Se podrán modificar los documentos existentes.
-  Se podrán eliminar documentos.
-  Se podrán llevar a cabo búsquedas simples (por un campo) y complejas (utilizando condiciones para más de un campo).

-  Ampliar la base de datos a más de una colección, en la que al menos en una de ellas se trabaje con datos mapeados desde alguna/s clase/s POJO.
-  Utilizar alguna colección que contenga estructuras complejas (arrays, datos estructurados, . . .).
-  Implementar alguna consulta con agregaciones
-  Implementar alguna consulta con proyecciones
-  Cualquier otra que a ti te parezca conveniente ...

-----------------------------------------------------------------------------------------------------------------------------------------------------------
-----------------------------------------------------------------------------------------------------------------------------------------------------------
-----------------------------------------------------------------------------------------------------------------------------------------------------------

El proyecto está compuesto por una clase main encargada de ejecutar las operaciones básicas del ejercicio (altas de documentos individuales o varias, 
modificación de documentos, eliminación de documentos y búsquedas tanto simples como complejas).

También hay un paquete donde se encuentra la parte encargada del mapeo de POJO's con la clase instanciable VideojuegoMapeo y la clase Mapeo encargada de
mapear la clase en la base de datos de Mongo. Al ejecutar la clase Mapeo se crea una colección nueva en la base de datos llamada videojuegosMapeados donde se pueden ver las dos inserciones realizadas cumpliendo con la parte del enunciado:
Ampliar la base de datos a más de una colección, en la que al menos en una de ellas se trabaje con datos mapeados desde alguna/s clase/s POJO.

