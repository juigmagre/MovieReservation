# Movie Reservation 🎥

## Desarrollo 🛠️
### Paquetes 📦
La aplicación tiene los paquetes ```Movie```, ```User``` y ```Reservation```. Estos paquetes representan las distintas entidades implicadas en la aplicación, y contienen los siguientes paquetes, representando sus respectivas capas:

- ```domain```: Contiene las distintas entidades implicadas en ```entity```, sus respectivos **Value Objects** en ```vo```, y los repositorios de JPA de cada entidad, en ```repository```.
- ```application```: Contiene la lógica de negocio para los distintos casos de uso de las entidades en ```service```, los ```Mapper``` y los **DTO (Data Transfer Object)**. En caso de los filtros, se emplea un Specification en el paquete ```criteria```.
- ```infraestructure```: Contiene los controladores para las peticiones HTTP, situados en ```controller```.

También tiene el paquete ```shared```, que contiene las partes compartidas por todas las entidades.

### Búsqueda de películas 🔍

Para la búsqueda de películas, se ha empleado un [Specification](src/main/java/com/jimg/myalbatross/modules/movie/application/criteria), para poder obtener los resultados tanto por año como por director, ambos a la vez inclusive. También se ha hecho el filtrado
de películas disponibles en dicho specification.

### Gestión de Excepciones 🚫

Las excepciones se han centralizado en una sola, [MyalbatrossException](src/main/java/com/jimg/myalbatross/shared/domain/exception/MyalbatrossException.java), pudiendo manejarlas de forma más sencilla y cómoda. Para gestionar los distintos 
mensajes y códigos de error, se han recogido en un [enum](src/main/java/com/jimg/myalbatross/shared/domain/exception/MyalbatrossError.java) que contiene ambos datos, de manera que si se desea añadir nuevos casos de error, se puede hacer desde ahí. 
Estos errores se mostrarán en la respuesta HTTP en caso de ocurrir.

## Test 🧪
Para los tests, se han hecho tests unitarios. En dichos tests se han probado distintos casos de uso de cada una de las entidades. 

Se han empleado tests parametrizados para aquellos casos en los que el código se repite y se prueban distintas casuísticas.
