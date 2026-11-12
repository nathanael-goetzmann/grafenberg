DROP DATABASE IF EXISTS grafenberg01;
CREATE DATABASE grafenberg01;
USE grafenberg01;

CREATE TABLE bloc (
	bloc_id INTEGER PRIMARY KEY,
	bloc_charges DECIMAL(7,2) NOT NULL
);

CREATE TABLE type_operation (
	top_id INTEGER PRIMARY KEY,
	top_nom VARCHAR(20) NOT NULL,
	top_charges DECIMAL(7,2) NOT NULL,
	top_bloc_id INTEGER NOT NULL REFERENCES bloc (bloc_id)
);

CREATE TABLE intervention (
	inter_id INTEGER PRIMARY KEY AUTO_INCREMENT,
	inter_date DATE NOT NULL,
	inter_nuit BOOLEAN NOT NULL,
	inter_duree INTEGER NOT NULL,
	inter_top_id INTEGER NOT NULL REFERENCES type_operation (top_id)
);

CREATE TABLE categorie_personnel (
	cat_code CHAR(1) PRIMARY KEY,
	cat_cout_horaire DECIMAL(7,2) NOT NULL
);

CREATE TABLE personnel (
	per_id INTEGER PRIMARY KEY AUTO_INCREMENT,
	per_nom VARCHAR(20) NOT NULL,
	per_cat_code CHAR(1) NOT NULL REFERENCES categorie_personnel (cat_code)
);

CREATE TABLE affecter (
	aff_inter_id INTEGER REFERENCES intervention (inter_id),
	aff_per_id INTEGER REFERENCES personnel (per_id),
	PRIMARY KEY (aff_inter_id, aff_per_id)
);

INSERT INTO bloc VALUES
(1, 1524.00),
(2, 915.00),
(3, 1700.00);

INSERT INTO type_operation VALUES
(1, 'Orthopédie', 152.00, 2),
(2, 'Plastique', 225.00, 2),
(3, 'Neurologie', 343.00, 3),
(4, 'Urologie', 549.00, 1);

INSERT INTO intervention VALUES
(DEFAULT, '2026-09-01', FALSE, 40, 1),
(DEFAULT, '2026-09-02', FALSE, 60, 2),
(DEFAULT, '2026-09-03', TRUE, 80, 3),
(DEFAULT, '2026-09-04', FALSE, 50, 4),
(DEFAULT, '2026-09-05', FALSE, 30, 1),
(DEFAULT, '2026-09-06', FALSE, 70, 2),
(DEFAULT, '2026-09-07', FALSE, 60, 3),
(DEFAULT, '2026-09-08', FALSE, 40, 4),
(DEFAULT, '2026-09-09', TRUE, 80, 1),
(DEFAULT, '2026-09-10', FALSE, 50, 2),
(DEFAULT, '2026-09-11', FALSE, 60, 3),
(DEFAULT, '2026-09-12', FALSE, 40, 4),
(DEFAULT, '2026-09-13', FALSE, 80, 1),
(DEFAULT, '2026-09-14', FALSE, 50, 2),
(DEFAULT, '2026-09-15', TRUE, 70, 3),
(DEFAULT, '2026-09-16', FALSE, 60, 4),
(DEFAULT, '2026-09-17', FALSE, 40, 1),
(DEFAULT, '2026-09-18', FALSE, 50, 2),
(DEFAULT, '2026-09-19', FALSE, 80, 3),
(DEFAULT, '2026-09-20', FALSE, 60, 4),
(DEFAULT, '2026-09-21', FALSE, 40, 1),
(DEFAULT, '2026-09-22', TRUE, 80, 2),
(DEFAULT, '2026-09-23', FALSE, 50, 3),
(DEFAULT, '2026-09-24', FALSE, 70, 4),
(DEFAULT, '2026-09-25', FALSE, 30, 1),
(DEFAULT, '2026-09-27', FALSE, 60, 2),
(DEFAULT, '2026-09-28', FALSE, 50, 3),
(DEFAULT, '2026-09-29', TRUE, 80, 4),
(DEFAULT, '2026-09-30', FALSE, 40, 1),
(DEFAULT, '2026-10-01', TRUE, 70, 2);

INSERT INTO categorie_personnel VALUES
('A', 24.00),
('B', 40.00),
('C', 73.00);

INSERT INTO personnel VALUES
(DEFAULT, 'Roy', 'A'),
(DEFAULT, 'Anna', 'A'),
(DEFAULT, 'Elen', 'A'),
(DEFAULT, 'Marcus', 'A'),
(DEFAULT, 'Dimitri', 'A'),
(DEFAULT, 'Edelgard', 'A'),
(DEFAULT, 'Marth', 'A'),
(DEFAULT, 'Caeda', 'A'),
(DEFAULT, 'Lyn', 'A'),
(DEFAULT, 'Ike', 'A'),
(DEFAULT, 'Adrian', 'B'),
(DEFAULT, 'Simon', 'B'),
(DEFAULT, 'Richter', 'B'),
(DEFAULT, 'Maria', 'B'),
(DEFAULT, 'Hector', 'B'),
(DEFAULT, 'Charlotte', 'B'),
(DEFAULT, 'John', 'B'),
(DEFAULT, 'Eric', 'B'),
(DEFAULT, 'Soma', 'B'),
(DEFAULT, 'Yoko', 'B'),
(DEFAULT, 'Ortega', 'C'),
(DEFAULT, 'Iris', 'C'),
(DEFAULT, 'Fiolina', 'C'),
(DEFAULT, 'Eri', 'C'),
(DEFAULT, 'Marco', 'C'),
(DEFAULT, 'Tarma', 'C'),
(DEFAULT, 'Yangus', 'C'),
(DEFAULT, 'Jessica', 'C'),
(DEFAULT, 'Angelo', 'C'),
(DEFAULT, 'Veronica', 'C');

INSERT INTO affecter VALUES
(1, 23), (1, 17),
(2, 2), (2, 11),
(3, 26), (3, 14),
(4, 16), (4, 12), (4, 1),
(5, 3), (5, 13),
(6, 14), (6, 6), (6, 18),
(7, 21), (7, 28),
(8, 4), (8, 9),
(9, 15), (9, 19),
(10, 22), (10, 2),
(11, 14), (11, 11),
(12, 1), (12, 5), (12, 29),
(13, 10), (13, 14), (13, 3),
(14, 1), (14, 2),
(15, 25), (15, 22), (15, 10), (15, 30),
(16, 13), (16, 5), (16, 7),
(17, 9), (17, 23),
(18, 1), (18, 8),
(19, 17), (19, 16), (19, 22),
(20, 10), (20, 13), (20, 6),
(21, 12), (21, 23),
(22, 9), (22, 30), (22, 10),
(23, 2), (23, 13), (23, 5), (23, 11), (23, 25), (23, 21), (23, 19),
(24, 16), (24, 18),
(25, 24), (25, 17),
(26, 4), (26, 19), (26, 9),
(27, 16), (27, 7),
(28, 19), (28, 4), (28, 15), (28, 27),
(29, 1), (29, 3), (29, 30),
(30, 16), (30, 13);

DROP USER IF EXISTS 'javagraf'@'%';
CREATE USER 'javagraf'@'%' IDENTIFIED BY 'kO7ù6Ha*Hj43';
GRANT SELECT, INSERT, EXECUTE ON grafenberg01.* TO 'javagraf'@'%';

SELECT aff_inter_id, per_nom
FROM affecter
JOIN personnel ON per_id = aff_per_id
ORDER BY aff_inter_id;

SELECT aff_inter_id, COUNT(*)
FROM affecter
GROUP BY aff_inter_id
ORDER BY aff_inter_id;