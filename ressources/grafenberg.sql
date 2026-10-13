DROP DATABASE IF EXISTS grafenberg;
CREATE DATABASE grafenberg;
USE grafenberg;

CREATE TABLE bloc (
	bloc_num SMALLINT PRIMARY KEY,
	bloc_charges DECIMAL(7,2) NOT NULL
);

CREATE TABLE type_operation (
	top_num SMALLINT PRIMARY KEY,
	top_charges DECIMAL(7,2) NOT NULL,
	top_bloc_num SMALLINT NOT NULL REFERENCES bloc (bloc_num)
);

CREATE TABLE intervention (
	inter_num INTEGER PRIMARY KEY,
	inter_date DATE NOT NULL,
	inter_nuit BOOLEAN NOT NULL,
	inter_duree SMALLINT NOT NULL,
	inter_top_num SMALLINT NOT NULL REFERENCES type_operation (top_num)
);

CREATE TABLE categorie_personnel (
	cat_code CHAR(1) PRIMARY KEY,
	cat_cout_horaire SMALLINT NOT NULL
);

CREATE TABLE personnel (
	per_num SMALLINT PRIMARY KEY,
	per_nom VARCHAR(20) NOT NULL,
	per_cat_code CHAR(1) NOT NULL REFERENCES categorie_personnel (cat_code)
);

CREATE TABLE affecter (
	aff_inter_num INTEGER REFERENCES intervention (inter_num),
	aff_per_num SMALLINT REFERENCES personnel (per_num),
	PRIMARY KEY (aff_inter_num, aff_per_num)
);

INSERT INTO bloc VALUES
(1, 1524.00),
(2, 915.00),
(3, 1700.00);

INSERT INTO type_operation VALUES
(1, 152.00, 2),
(2, 225.00, 2),
(3, 343.00, 3),
(4, 549.00, 1);

INSERT INTO intervention VALUES
(1, '2026-09-01', FALSE, 4, 1),
(2, '2026-09-02', FALSE, 6, 2),
(3, '2026-09-03', TRUE, 8, 3),
(4, '2026-09-04', FALSE, 5, 4),
(5, '2026-09-05', FALSE, 3, 1),
(6, '2026-09-06', FALSE, 7, 2),
(7, '2026-09-07', FALSE, 6, 3),
(8, '2026-09-08', FALSE, 4, 4),
(9, '2026-09-09', TRUE, 8, 1),
(10, '2026-09-10', FALSE, 5, 2),
(11, '2026-09-11', FALSE, 6, 3),
(12, '2026-09-12', FALSE, 4, 4),
(13, '2026-09-13', FALSE, 8, 1),
(14, '2026-09-14', FALSE, 5, 2),
(15, '2026-09-15', TRUE, 7, 3),
(16, '2026-09-16', FALSE, 6, 4),
(17, '2026-09-17', FALSE, 4, 1),
(18, '2026-09-18', FALSE, 5, 2),
(19, '2026-09-19', FALSE, 8, 3),
(20, '2026-09-20', FALSE, 6, 4),
(21, '2026-09-21', FALSE, 4, 1),
(22, '2026-09-22', TRUE, 8, 2),
(23, '2026-09-23', FALSE, 5, 3),
(24, '2026-09-24', FALSE, 7, 4),
(25, '2026-09-25', FALSE, 3, 1),
(26, '2026-09-27', FALSE, 6, 2),
(27, '2026-09-28', FALSE, 5, 3),
(28, '2026-09-29', TRUE, 8, 4),
(29, '2026-09-30', FALSE, 4, 1),
(30, '2026-10-01', TRUE, 7, 2);

INSERT INTO categorie_personnel VALUES
('A', 24.00),
('B', 40.00),
('C', 73.00);

INSERT INTO personnel VALUES
(1, 'Roy', 'A'),
(2, 'Anna', 'A'),
(3, 'Elen', 'A'),
(4, 'Marcus', 'A'),
(5, 'Dimitri', 'A'),
(6, 'Edelgard', 'A'),
(7, 'Marth', 'A'),
(8, 'Caeda', 'A'),
(9, 'Lyn', 'A'),
(10, 'Ike', 'A'),
(11, 'Adrian', 'B'),
(12, 'Simon', 'B'),
(13, 'Richter', 'B'),
(14, 'Maria', 'B'),
(15, 'Hector', 'B'),
(16, 'Charlotte', 'B'),
(17, 'John', 'B'),
(18, 'Eric', 'B'),
(19, 'Soma', 'B'),
(20, 'Yoko', 'B'),
(21, 'Ortega', 'C'),
(22, 'Iris', 'C'),
(23, 'Fiolina', 'C'),
(24, 'Eri', 'C'),
(25, 'Marco', 'C'),
(26, 'Tarma', 'C'),
(27, 'Yangus', 'C'),
(28, 'Jessica', 'C'),
(29, 'Angelo', 'C'),
(30, 'Veronica', 'C');

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
GRANT SELECT, EXECUTE ON grafenberg.* TO 'javagraf'@'%';