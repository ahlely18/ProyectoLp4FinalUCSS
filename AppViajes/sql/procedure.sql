use bdviaje;
DELIMITER $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `spadicion`(IN `codviaje` CHAR(6), IN `pasajero` VARCHAR(30), IN `tipo` CHAR(1), IN `asiento` INT(2), IN `pago` DECIMAL(8))
begin
DECLARE nro int(2);
declare boleto char(6);
select ifnull(max(bolnro),0) + 1  from pasajeros into nro;
set boleto = lpad(nro,6,'0');	
insert into pasajeros values (boleto,codviaje,pasajero,asiento,tipo,pago);
end$$
DELIMITER ;

DELIMITER $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `spcreateaccount`(IN `CODE` VARCHAR(10), IN `USUARIO` VARCHAR(30), IN `NOMBRE` VARCHAR(29), IN `CORREO` VARCHAR(30), IN `TELEFONO` VARCHAR(11), IN `ACCESO` VARCHAR(2))
insert into usuario values (CODE,USUARIO,NOMBRE,CORREO,TELEFONO,ACCESO)$$
DELIMITER ;