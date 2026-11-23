-- Création des tables, et insertion de données de test.

DROP DATABASE IF EXISTS grafenberg1;
CREATE DATABASE grafenberg1;
USE grafenberg1;

CREATE TABLE bloc (
	bloc_id SMALLINT PRIMARY KEY CHECK (bloc_id > 0),
	bloc_charges DECIMAL(7,2) NOT NULL CHECK (bloc_charges > 0)
);

CREATE TABLE type_operation (
	top_id SMALLINT PRIMARY KEY CHECK (top_id > 0),
	top_nom VARCHAR(20) NOT NULL CHECK (top_nom <> ""),
	top_charges DECIMAL(7,2) NOT NULL CHECK (top_charges > 0),
	top_bloc_id SMALLINT NOT NULL REFERENCES bloc (bloc_id)
);

CREATE TABLE intervention (
	inter_id INTEGER PRIMARY KEY AUTO_INCREMENT,
	inter_date DATE NOT NULL,
	inter_nuit BOOLEAN NOT NULL,
	inter_duree SMALLINT NOT NULL CHECK (inter_duree > 0),
	inter_top_id SMALLINT NOT NULL REFERENCES type_operation (top_id)
);

CREATE TABLE categorie_personnel (
	cat_code CHAR(1) PRIMARY KEY CHECK (cat_code <> ""),
	cat_cout_horaire DECIMAL(7,2) NOT NULL CHECK (cat_cout_horaire > 0)
);

CREATE TABLE personnel (
	per_id INTEGER PRIMARY KEY AUTO_INCREMENT,
	per_nom VARCHAR(20) NOT NULL CHECK (per_nom <> ""),
	per_cat_code CHAR(1) NOT NULL REFERENCES categorie_personnel (cat_code)
);

CREATE TABLE affecter (
	aff_inter_id INTEGER REFERENCES intervention (inter_id),
	aff_per_id INTEGER REFERENCES personnel (per_id),
	PRIMARY KEY (aff_inter_id, aff_per_id)
);

CREATE TABLE param_jours_feries (
	param_jf_id SMALLINT PRIMARY KEY CHECK (param_jf_id > 0),
	param_jf_libelle VARCHAR(30) NOT NULL CHECK (param_jf_libelle <> ""),
	param_jf_date DATE NOT NULL
);

INSERT INTO bloc VALUES
(1, 1524.15),
(2, 915.75),
(3, 1700.45);

INSERT INTO type_operation VALUES
(1, 'Orthopédie', 152.50, 2),
(2, 'Plastique', 225.30, 2),
(3, 'Neurologie', 343.15, 3),
(4, 'Urologie', 549.85, 1);

INSERT INTO intervention VALUES
(DEFAULT, '2025-07-14', FALSE, 5, 1),
(DEFAULT, '2025-08-05', FALSE, 3, 2),
(DEFAULT, '2025-08-16', TRUE, 7, 3),
(DEFAULT, '2025-09-26', FALSE, 3, 4),
(DEFAULT, '2025-09-30', FALSE, 5, 1),
(DEFAULT, '2025-10-11', FALSE, 6, 2),
(DEFAULT, '2025-11-07', FALSE, 4, 3),
(DEFAULT, '2025-11-13', FALSE, 8, 4),
(DEFAULT, '2025-11-23', TRUE, 3, 1),
(DEFAULT, '2025-12-14', FALSE, 5, 2),
(DEFAULT, '2025-12-28', FALSE, 4, 3),
(DEFAULT, '2026-01-01', FALSE, 6, 4),
(DEFAULT, '2026-01-13', FALSE, 7, 1),
(DEFAULT, '2026-01-19', FALSE, 5, 2),
(DEFAULT, '2026-02-03', TRUE, 3, 3),
(DEFAULT, '2026-02-14', FALSE, 5, 4),
(DEFAULT, '2026-03-12', FALSE, 4, 1),
(DEFAULT, '2026-03-24', FALSE, 8, 2),
(DEFAULT, '2026-04-05', FALSE, 7, 3),
(DEFAULT, '2026-04-11', FALSE, 4, 4),
(DEFAULT, '2026-04-17', FALSE, 5, 1),
(DEFAULT, '2026-04-26', TRUE, 7, 2),
(DEFAULT, '2026-05-16', FALSE, 6, 3),
(DEFAULT, '2026-05-21', FALSE, 6, 4),
(DEFAULT, '2026-06-18', FALSE, 4, 1),
(DEFAULT, '2026-07-06', FALSE, 5, 2),
(DEFAULT, '2026-07-19', FALSE, 7, 3),
(DEFAULT, '2026-07-31', TRUE, 8, 4),
(DEFAULT, '2026-08-02', FALSE, 4, 1),
(DEFAULT, '2026-08-17', TRUE, 5, 2);

INSERT INTO categorie_personnel VALUES
('A', 24.25),
('B', 40.40),
('C', 73.75);

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

INSERT INTO param_jours_feries VALUES
(1, "Jour de l'an", "2027-01-01"),
(2, "Lundi de Pâques", "2027-04-06"),
(3, "Fête du travail", "2027-05-01"),
(4, "Victoire 1945", "2027-05-08"),
(5, "Ascension", "2027-05-14"),
(6, "Lundi de Pentecôte", "2027-05-25"),
(7, "Fête nationale", "2027-07-14"),
(8, "Assomption", "2027-08-15"),
(9, "Toussaint", "2027-11-01"),
(10, "Armistice 1918", "2027-11-11"),
(11, "Jour de Noël", "2027-12-25");

-- Définition d'un utilisateur pour l'application Java.
-- Le principe du moindre privilège est appliqué.

DROP USER IF EXISTS 'javagraf'@'%';
CREATE USER 'javagraf'@'%' IDENTIFIED BY 'kO7ù6Ha*Hj43';
GRANT SELECT, EXECUTE ON grafenberg1.* TO 'javagraf'@'%';
GRANT INSERT ON grafenberg1.intervention TO 'javagraf'@'%';
GRANT INSERT ON grafenberg1.affecter TO 'javagraf'@'%';

-- Création de fonctions pour calculer le coût d'une intervention.

DROP FUNCTION IF EXISTS f_cout_intervention_selon_inter_id;
DROP FUNCTION IF EXISTS f_cout_materiel_selon_inter_id;
DROP FUNCTION IF EXISTS f_cout_personnel_selon_inter_id;

DELIMITER //

-- Coût du matériel (charges du type d'opération)

CREATE FUNCTION f_cout_materiel_selon_inter_id (f_inter_id INTEGER)
RETURNS DECIMAL(9, 2) NOT DETERMINISTIC
BEGIN
	DECLARE f_top_charges DECIMAL(7, 2);
	DECLARE f_inter_heures SMALLINT;
	
	SELECT inter_duree, top_charges INTO f_inter_heures, f_top_charges FROM intervention
	JOIN type_operation ON top_id = inter_top_id
	WHERE inter_id = f_inter_id;
	
	RETURN f_top_charges * f_inter_heures;
END//

-- Coût du personnel impliqué

<<<<<<< HEAD
CREATE FUNCTION f_cout_personnel_selon_inter_id (f_inter_id INTEGER)
RETURNS DECIMAL(9, 2) NOT DETERMINISTIC
BEGIN
	DECLARE f_inter_date DATE;
	DECLARE f_inter_nuit BOOLEAN;
	DECLARE f_inter_duree SMALLINT;
	DECLARE f_sum_cat_cout_horaire DECIMAL(7, 2);
	
	SELECT inter_date, inter_nuit, inter_duree INTO f_inter_date, f_inter_nuit, f_inter_duree FROM intervention
	WHERE inter_id = f_inter_id;
	
	SELECT SUM(cat_cout_horaire) INTO f_sum_cat_cout_horaire FROM categorie_personnel
	JOIN personnel ON per_cat_code = cat_code
	WHERE per_id IN (
		SELECT aff_per_id FROM affecter
		WHERE aff_inter_id = f_inter_id
	);
	
	IF
		DAYNAME(f_inter_date) IN ("Saturday", "Sunday")
		OR DATE_FORMAT(f_inter_date, "%m-%d") IN (SELECT DATE_FORMAT(param_jf_date, "%m-%d") FROM param_jours_feries)
	THEN
		SET f_sum_cat_cout_horaire = f_sum_cat_cout_horaire * 3;
	ELSEIF
		f_inter_nuit = TRUE
	THEN
		SET f_sum_cat_cout_horaire = f_sum_cat_cout_horaire * 2;
	END IF;
	
	RETURN f_sum_cat_cout_horaire * f_inter_duree;
=======
CREATE PROCEDURE p_calculer_cout_personnel (
    IN p_inter_id INTEGER,
    OUT p_total DECIMAL(9,2)
)
BEGIN
    DECLARE p_inter_date DATE;
    DECLARE p_inter_nuit BOOLEAN;
    DECLARE p_inter_duree SMALLINT;

    DECLARE EXIT HANDLER FOR NOT FOUND
    BEGIN
        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = 'Intervention introuvable';
    END;

    SELECT inter_date, inter_nuit, inter_duree
    INTO p_inter_date, p_inter_nuit, p_inter_duree
    FROM intervention
    WHERE inter_id = p_inter_id;

    SELECT COALESCE(SUM(c.cat_cout_horaire), 0)
    INTO p_total
    FROM affecter a
    JOIN personnel p ON p.per_id = a.aff_per_id
    JOIN categorie_personnel c ON c.cat_code = p.per_cat_code
    WHERE a.aff_inter_id = p_inter_id;

    SET p_total = p_total * p_inter_duree;

    IF WEEKDAY(p_inter_date) IN (5, 6)
       OR DATE_FORMAT(p_inter_date, '%m-%d') IN (
           SELECT DATE_FORMAT(param_jf_date, '%m-%d')
           FROM param_jours_feries
       )
    THEN
        SET p_total = p_total * 3;
    ELSEIF p_inter_nuit = TRUE THEN
        SET p_total = p_total * 2;
    END IF;
>>>>>>> 092c9a953992fb83d5223370ebab095231e959ba
END//

-- Somme des charges du bloc, du matériel et du coût horaire du personnel.

CREATE FUNCTION f_cout_intervention_selon_inter_id (f_inter_id INTEGER)
RETURNS DECIMAL(9, 2) NOT DETERMINISTIC
BEGIN
	DECLARE f_bloc_charges DECIMAL(7,2);
	
	SELECT bloc_charges INTO f_bloc_charges FROM bloc
	WHERE bloc_id = (
		SELECT top_bloc_id FROM type_operation
		WHERE top_id = (
			SELECT inter_top_id FROM intervention
			WHERE inter_id = f_inter_id
		)
	);
	
	RETURN f_bloc_charges + f_cout_personnel_selon_inter_id(f_inter_id) + f_cout_materiel_selon_inter_id(f_inter_id);
END//

DELIMITER ;

-- Requêtes SQL diverses à des fins de test.

SHOW GRANTS FOR 'javagraf'@'%';

SHOW STATUS WHERE variable_name = 'threads_connected';

SELECT aff_inter_id, per_nom
FROM affecter
JOIN personnel ON per_id = aff_per_id
ORDER BY aff_inter_id;

SELECT aff_inter_id, COUNT(*)
FROM affecter
GROUP BY aff_inter_id
ORDER BY aff_inter_id;