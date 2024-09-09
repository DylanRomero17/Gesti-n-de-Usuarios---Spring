Para poder iniciar sesión en el sistema, se debe contar con un usuario (login) y contraseña en la base de datos. 
Ejecutar los siguientes scripts en MySQL:

CREATE DATABSE adea;

CREATE TABLE adea.usuario (
  `login` varchar(20) NOT NULL,
  `password` varchar(30) NOT NULL,
  `nombre` varchar(50) NOT NULL,
  `cliente` float NOT NULL,
  `email` varchar(50) DEFAULT NULL,
  `fecha_alta` date NOT NULL,
  `fecha_baja` date DEFAULT NULL,
  `status` char(1) NOT NULL,
  `intentos` float NOT NULL,
  `fecha_revocado` date DEFAULT NULL,
  `fecha_vigencia` date DEFAULT NULL,
  `no_acceso` int DEFAULT NULL,
  `apellido_paterno` varchar(50) DEFAULT NULL,
  `apellido_materno` varchar(50) DEFAULT NULL,
  `area` decimal(4,0) DEFAULT NULL,
  `fecha_modificacion` date NOT NULL,
  PRIMARY KEY (`login`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


INSERT INTO `adea`.`usuario`
(`login`,
`password`,
`nombre`,
`cliente`,
fecha_alta,
status,
intentos,
fecha_modificacion
)
VALUES
('user',
'MTIzNDU=',
'user1',
1,
now(),
'A',
0,
now()
);

Una vez ejecutados, iniciar sesión con las siguientes credenciales:

Username: user
Password: 12345

Se iniciará sesión y se podrá empezar a utilizar el sistema.

Nota: Es necesario tener primero un usuario en la base de datos, ya que el inicio de sesión es parte de la funcionalidad del sistema.
