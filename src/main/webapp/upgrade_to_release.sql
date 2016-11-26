INSERT INTO `menu` (`ID_MENU_PADRE`, `DESCRIPCION`, `URL`, `IMAGEN`, `ACTIVO`, `ORDEN`)
VALUES
	( 0, 'Adopción', 'adopcionAction.action', NULL, '1', 1),
	( 0, 'Uso e interacción', 'usoInteraccionAction.action', NULL, '1', 2),
	( 0, 'Detalle', 'resultadosNegocioAction.action', NULL, '1',  5),
	( 0, 'Resumen Ejecutivo', 'resumenEjecutivoAction.action', NULL, '0',  3);

	
alter table CONFIGURACION MODIFY valor varchar(200);

INSERT INTO `configuracion` (`parametro`, `valor`)
VALUES
	('analytics.aplication', '-'),
	('analytics.key', ''),
	('analytics.id', 'ga:82697517'),
	('analytics.user', '284397870087-lhsklar5gu1ajbvotlu465oh158qnf1v@developer.gserviceaccount.com'),
	('analytics.password', ''),
	('mysql.host', ''),
	('mysql.port', ''),
	('mysql.database', ''),
	('mysql.user', ''),
	('mysql.pwd', ''),
	('android.version.last', '1.0.0'),
	('iphone.version.last', '1.0.0'),
	('appannie.intervalo', '24'),
	('appannie.api_key', '0891cad6fa7ac23d88c8e1c22054dcce384bfc04'),
	('appannie.google_account', 'Google Play'),
	('appannie.ios_account', 'iTunes'),
	('appannie.google_id', ''),
	('appannie.apple_id', ''),
	('analytics.fechaInicio', ''),
	('smtp.socket.server', '54.203.244.196'),
	('smtp.socket.port', '9061'),
	('publicacion.fecha', ''),
	('app.showDescargas', 'No'),
	('analytics.p12', '');
