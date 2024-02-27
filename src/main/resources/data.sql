-- Inserción de Veterinarios de ejemplo:
INSERT INTO veterinario (nombre, matricula, email) VALUES ('Dr. Diego', '123ABC', 'drdiego@example.com');
INSERT INTO veterinario (nombre, matricula, email) VALUES ('Dr. Juan', '111AAA', 'drjuan@example.com');
INSERT INTO veterinario (nombre, matricula, email) VALUES ('Dr. Sergio', '999ZZZ', 'drsergio@example.com');
INSERT INTO veterinario (nombre, matricula, email) VALUES ('Dr. Maxi', 'ASD423', 'drmaxi@example.com');

-- Inserción de vacunas de ejemplo:
INSERT INTO vacuna (nombre, fecha_vencimiento) VALUES ('Vacuna contra la rabia', '2026-01-10');
INSERT INTO vacuna (nombre, fecha_vencimiento) VALUES ('Vacuna contra COVID-19', '2025-01-10');
INSERT INTO vacuna (nombre, fecha_vencimiento) VALUES ('Vacuna Quintuple', '2025-01-11');
INSERT INTO vacuna (nombre, fecha_vencimiento) VALUES ('Vacuna parvoviruse', '2025-04-10');
INSERT INTO vacuna (nombre, fecha_vencimiento) VALUES ('Vacuna moquillo', '2024-06-11');

-- Inserción de mascotas de ejemplo:
INSERT INTO mascota (nombre, especie, fecha_nacimiento, sexo, veterinario_id) VALUES ('Leonidas', 'Planta carnivora', '2023-01-10', 'Macho', 4);
INSERT INTO mascota (nombre, especie, fecha_nacimiento, sexo, veterinario_id) VALUES ('Canela', 'Perro', '2023-01-10', 'Hembra', 2);
INSERT INTO mascota (nombre, especie, fecha_nacimiento, sexo, veterinario_id) VALUES ('Bondiola', 'Gato', '2023-01-10', 'Macho', 3);
INSERT INTO mascota (nombre, especie, fecha_nacimiento, sexo, veterinario_id) VALUES ('Miguel de Guemes', 'Perro', '2023-01-10', 'Macho', 3);
INSERT INTO mascota (nombre, especie, fecha_nacimiento, sexo, veterinario_id) VALUES ('Milo', 'Perro', '2023-01-10', 'Macho', 3);
INSERT INTO mascota (nombre, especie, fecha_nacimiento, sexo, veterinario_id) VALUES ('Gala', 'Gato', '2023-01-10', 'Hembra', 3);
INSERT INTO mascota (nombre, especie, fecha_nacimiento, sexo, veterinario_id) VALUES ('Bati', 'Perro', '2023-01-10', 'Macho', 3);

-- Inserción de mascotas con vacunas
INSERT INTO mascota_vacuna (mascota_id, vacuna_id) VALUES (3,2);
INSERT INTO mascota_vacuna (mascota_id, vacuna_id) VALUES (1,3);
INSERT INTO mascota_vacuna (mascota_id, vacuna_id) VALUES (2,2);
INSERT INTO mascota_vacuna (mascota_id, vacuna_id) VALUES (2,4);