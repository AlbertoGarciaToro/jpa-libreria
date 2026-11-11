INSERT  INTO usuario (nombre, email, telefono) VALUES
('Carlos Gómez', 'carlos.gomez@example.com', '5512345678'),
('María Rodríguez', 'maria.rodriguez@example.com', '5523456789'),
('Juan Pérez', 'juan.perez@example.com', '5534567890'),
('Ana Martínez', 'ana.martinez@example.com', '5545678901'),
('Luis Hernández', 'luis.hernandez@example.com', '5556789012'),
('Laura López', 'laura.lopez@example.com', '5567890123'),
('Pedro Sánchez', 'pedro.sanchez@example.com', '5578901234'),
('Sofia Ramírez', 'sofia.ramirez@example.com', '5589012345'),
('Diego Flores', 'diego.flores@example.com', '5590123456'),
('Elena Torres', 'elena.torres@example.com', '5501234567');

INSERT INTO libros (titulo, autor, isbn, precio, stock) VALUES
('Cien años de soledad', 'Gabriel García Márquez', '978-0307474728', 299.00, 50),
('Don Quijote de la Mancha', 'Miguel de Cervantes', '978-8424116032', 350.50, 30),
('El Principito', 'Antoine de Saint-Exupéry', '978-0156013987', 150.00, 100),
('Pedro Páramo', 'Juan Rulfo', '978-8437604183', 180.25, 45),
('Rayuela', 'Julio Cortázar', '978-8437604572', 275.00, 25),
('1984', 'George Orwell', '978-0451524935', 210.00, 60),
('Fahrenheit 451', 'Ray Bradbury', '978-1451673319', 230.80, 40),
('Un mundo feliz', 'Aldous Huxley', '978-0060850524', 245.00, 35),
('La sombra del viento', 'Carlos Ruiz Zafón', '978-0307454720', 320.00, 20),
('El aleph', 'Jorge Luis Borges', '978-8420633114', 195.50, 15);

INSERT INTO ventas (fecha, cantidad, total, usuario_id, libro_id) VALUES
('2026-01-10 10:15:00', 1, 299.00, 1, 1),
('2026-01-12 11:30:00', 2, 701.00, 2, 2),
('2026-01-15 14:20:00', 1, 150.00, 3, 3),
('2026-01-18 16:45:00', 3, 540.75, 4, 4),
('2026-01-20 09:00:00', 1, 275.00, 5, 5),
('2026-02-01 12:10:00', 2, 420.00, 6, 6),
('2026-02-05 15:05:00', 1, 230.80, 7, 7),
('2026-02-10 17:50:00', 1, 245.00, 8, 8),
('2026-02-14 11:15:00', 2, 640.00, 9, 9),
('2026-02-20 18:30:00', 1, 195.50, 10, 10);