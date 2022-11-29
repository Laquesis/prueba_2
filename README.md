# prueba_2
Backend CRUD que usa H2 como base de datos volátil.
URL a la base de datos:

http://localhost:8080/h2-console

JDBC URL
jdbc:h2:mem:cartdb
USER:sa
PASSWORD:password

Datos de prueba iniciales para añadir a la base de datos:

insert into products (id,amount,description)values(90,342, 'pienso');
insert into products (id,amount,description)values(91,342, 'pienso');
insert into products (id,amount,description)values(92,342, 'pienso');
insert into products (id,amount,description)values(93,342, 'pienso');
insert into products (id,amount,description)values(94,342, 'pienso');



insert into carts (products)values(ARRAY[90, 91,93]);
insert into carts (products)values(ARRAY[90, 91,93]);
insert into carts (products)values(ARRAY[90, 91,93]);
insert into carts (products)values(ARRAY[90, 91,93]);

Pruebas realizadas con postman y en el navegador.
Se facilita la collection de requests.

