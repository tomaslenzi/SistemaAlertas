# Como ejecutar la aplicacion
`.\mvnw clean test`

# Depenndecias del proyecto
jdk 17



# Ejercicio - Sistema de Alertas
Seguramente conocés la funcionalidad de Notificaciones de Facebook: es esa campanita arriba en el menú donde te muestra las nuevas alertas que el sistema tiene para mostrarte sobre distintos temas: un amigo cumple años, una página que seguís compartió una publicación, un amigo publicó una foto, alguien comentó un posteo tuyo, una sugerencia de amistad, etc.

Facebook no es el único. En general todas las aplicaciones tienen un sistema de alertas o notificaciones. En este ejercicio, te vamos a pedir que hagas una versión muy simplificada.

Se pide programar un sistema para enviar alertas a usuarios que tenga la siguiente funcionalidad:
Se pueden registrar usuarios que recibirán alertas.
Se pueden registrar temas sobre los cuales se enviarán alertas.
Los usuarios pueden optar sobre cuales temas quieren recibir alertas.
Se puede enviar una alerta sobre un tema y lo reciben todos los usuarios que han optado recibir alertas de ese tema.
Se puede enviar una alerta sobre un tema a un usuario específico, solo lo recibe ese único usuario.
Una alerta puede tener una fecha y hora de expiración. Las alertas que tienen expiración, no se muestran al usuario si han expirado.
Hay dos tipos de alertas: Informativas y Urgentes.
Un usuario puede marcar una alerta como leída.
Se pueden obtener todas las alertas no expiradas de un usuario que aún no ha leído.
Se pueden obtener todas las alertas no expiradas para un tema. Se informa para cada alerta si es para todos los usuarios o para uno específico.
Tanto para el punto 9 como el 10, el ordenamiento de las alertas es el siguiente: las Urgentes van al inicio, siendo la última en llegar la primera en obtenerse (LIFO). Y a continuación las informativas, siendo la primera en llegar la primera en obtenerse (FIFO). Ej: Dadas las siguientes alertas Informativas y Urgentes que llegan en el siguiente orden: I1,I2,U1,I3,U2,I4 se ordenarán de la siguiente forma --> U2,U1,I1,I2,I3,I4
Aclaraciones importantes:
La aplicación se ejecuta desde línea de comando. En ningún caso pedimos que escribas código de front end, tampoco que hagas impresiones a la consola.
Debe tener Tests Unitarios.
No debés hacer ningún tipo de persistencia de datos (base de datos, archivos). Todo debe resolverse con estructuras en memoria.
Si tenés que hacer algún supuesto sobre algo que no esté claro en el ejercicio, por favor anotalo para que lo tengamos en cuenta al revisar el código.
Al responder el ejercicio, por favor entregá código que funcione y se pueda probar. Se minucioso en los detalles y en la claridad del código que escribas para que al revisor le sea fácil entenderlo.