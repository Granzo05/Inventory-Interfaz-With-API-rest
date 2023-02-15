Interfaz que simula la carga de productos en una base de datos(MySQL)
Funciona utilizando una Api a base de Spring e Hibernate
La interfaz envía los datos a la Api y esta lo carga a la base de datos.

Pasos para su funcionamiento
1) Crear el contenedor en Docker: docker run --hostname=b973809ae7ba --env=MYSQL_DATABASE=Inventory --env=MYSQL_USER=test --env=MYSQL_PASSWORD=test --env=MYSQL_ROOT_PASSWORD=test --env=PATH=/usr/local/sbin:/usr/local/bin:/usr/sbin:/usr/bin:/sbin:/bin --env=GOSU_VERSION=1.16 --env=MYSQL_MAJOR=8.0 --env=MYSQL_VERSION=8.0.32-1.el8 --env=MYSQL_SHELL_VERSION=8.0.32-1.el8 --volume=/var/lib/mysql -p 3306:3306 --runtime=runc -d mysql:latest
2) Ejecutarlo
3) Ejecutar el Main de "InventoryApiRest"
4) Ejecutar el Main de "InventoryControl"
5) Implementar los métodos CRUD que veas necesarios.
6) Podes chequear la base de datos creada en la siguiente url http://localhost:8080/h2-console/login.jsp?jsessionid=5d76c09b6d101fff73889dfcd5fd4c73
7) Datos para iniciar sesión: 
- JBDC URL: jdbc:mysql://localhost:3306/Inventory
- User Name: test
- Password: test
ACLARACION * Estos datos pueden ser modificados al crear el contenedor en docker *
