-- =====================================================================
-- 1. REGISTROS DE USUARIOS (Estrategia Salt & Pepper / Pass original: 1234)
-- =====================================================================
INSERT IGNORE INTO usuarios (id, pass, role, salt, user) 
VALUES (1, '76b5cb57dc38084a9538a7c645bc8bb749d211833503f88f1af6ba3bf56b5a37', 'ADMIN', 'a1b2c3d4e5f67890', 'admin');

INSERT IGNORE INTO usuarios (id, pass, role, salt, user) 
VALUES (2, '3b7b258fb96fa5cb2e1e3b6fb6e2c34cb2c2194689b9f71c998f49eb0db46cb3', 'USER', 'f0e9d8c7b6a54321', 'user');


-- =====================================================================
-- 2. REGISTROS DE MARCAS
-- =====================================================================
INSERT IGNORE INTO marcas (id, nombre) VALUES (1, 'Toyota');
INSERT IGNORE INTO marcas (id, nombre) VALUES (2, 'BMW');
INSERT IGNORE INTO marcas (id, nombre) VALUES (3, 'Ford');


-- =====================================================================
-- 3. REGISTROS DE COCHES
-- =====================================================================
INSERT IGNORE INTO coches (id, modelo, matricula, potencia, precio, marca_id) 
VALUES (1, 'Corolla', '1234ABC', 140, 24500.00, 1);

INSERT IGNORE INTO coches (id, modelo, matricula, potencia, precio, marca_id) 
VALUES (2, 'Yaris', '5678DEF', 116, 18200.00, 1);

INSERT IGNORE INTO coches (id, modelo, matricula, potencia, precio, marca_id) 
VALUES (3, 'Serie 3', '9012GHI', 184, 45000.00, 2);