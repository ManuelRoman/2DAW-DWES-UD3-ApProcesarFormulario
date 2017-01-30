DROP DATABASE IF EXISTS procesaformulario;
CREATE DATABASE procesaformulario;
USE procesaformulario;
CREATE TABLE usuario (
  login VARCHAR(10),
  clave VARCHAR(10) NOT NULL,
  PRIMARY KEY (login)
);


INSERT INTO usuario (login,clave) VALUES
('ana','clave'),
('pepe','aaaa'),
('juan','1234');
