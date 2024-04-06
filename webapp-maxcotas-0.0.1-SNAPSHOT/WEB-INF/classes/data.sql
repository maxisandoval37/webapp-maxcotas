-- Inserción de Veterinarios de ejemplo:
INSERT INTO veterinario (matricula, email) VALUES ('123ABC', 'drjuan@example.com');
INSERT INTO veterinario (matricula, email) VALUES ('111AAA', 'drpedro@example.com');
INSERT INTO veterinario (matricula, email) VALUES ('999ZZZ', 'drajosefa@example.com');
INSERT INTO veterinario (matricula, email) VALUES ('ASD423', 'drvillalba@example.com');

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

-- Inserción de usuarios de ejemplo (contraseña user)
INSERT INTO Usuario (username, contrasena, rol, nombre, apellido) VALUES ('admin', '{bcrypt}$2a$12$8L95nO.z7qMQdW3YdXp9ZOqrn.2AWYQVIPHtqGE9U2tLjNWZVLWyq', 'ROL_ADMIN', 'José', 'Pérez');
INSERT INTO Usuario (username, contrasena, rol, nombre, apellido) VALUES ('user', '{bcrypt}$2a$12$8L95nO.z7qMQdW3YdXp9ZOqrn.2AWYQVIPHtqGE9U2tLjNWZVLWyq','ROL_LECTURA', 'María', 'Gómez');
INSERT INTO Usuario (username, contrasena, rol, nombre, apellido) VALUES ('lectura', '{bcrypt}$2a$12$8L95nO.z7qMQdW3YdXp9ZOqrn.2AWYQVIPHtqGE9U2tLjNWZVLWyq','ROL_LECTURA', 'Carlos', 'López');

INSERT INTO Usuario (username, contrasena, rol, nombre, apellido, veterinario_id) VALUES ('veterinario_juan', '{bcrypt}$2a$12$8L95nO.z7qMQdW3YdXp9ZOqrn.2AWYQVIPHtqGE9U2tLjNWZVLWyq','ROL_VETERINARIO', 'Juan', 'Pérez', 1);
INSERT INTO Usuario (username, contrasena, rol, nombre, apellido, veterinario_id) VALUES ('veterinario_pedro', '{bcrypt}$2a$12$8L95nO.z7qMQdW3YdXp9ZOqrn.2AWYQVIPHtqGE9U2tLjNWZVLWyq','ROL_VETERINARIO', 'Pedro', 'González', 2);
INSERT INTO Usuario (username, contrasena, rol, nombre, apellido, veterinario_id) VALUES ('veterinario_josefa', '{bcrypt}$2a$12$8L95nO.z7qMQdW3YdXp9ZOqrn.2AWYQVIPHtqGE9U2tLjNWZVLWyq','ROL_VETERINARIO', 'Josefa', 'Diaz', 3);
INSERT INTO Usuario (username, contrasena, rol, nombre, apellido, veterinario_id) VALUES ('veterinario_sergio', '{bcrypt}$2a$12$8L95nO.z7qMQdW3YdXp9ZOqrn.2AWYQVIPHtqGE9U2tLjNWZVLWyq','ROL_VETERINARIO', 'Sergio', 'Villalba', 4);