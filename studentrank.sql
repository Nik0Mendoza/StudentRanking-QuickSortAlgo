CREATE DATABASE studentrank;
USE studentrank;
CREATE TABLE student(
	studentID VARCHAR (17) NOT NULL PRIMARY KEY,
    programID INT NOT NULL,
    deptID INT NOT NULL,
    yearID INT NOT NULL,
    sectionID INT NOT NULL,
    listerID INT,
    firstName VARCHAR(45) NOT NULL,
    lastName VARCHAR(45) NOT NULL,
    middleInitial CHAR(3),
    suffix CHAR(45)
);

CREATE TABLE grade(
	studentID VARCHAR (17) NOT NULL,
    yearID INT NOT NULL,
    semID INT NOT NULL,
    gwa FLOAT,
    listerID INT NOT NULL,
    PRIMARY KEY (studentID, yearID, semID, listerID)
);

CREATE TABLE lister(
	listerID INT AUTO_INCREMENT NOT NULL PRIMARY KEY,
    listerDesc VARCHAR(45)
);

CREATE TABLE department(
	deptID INT AUTO_INCREMENT NOT NULL PRIMARY KEY,
    deptDesc VARCHAR(45)
);

CREATE TABLE program(
	programID INT AUTO_INCREMENT NOT NULL PRIMARY KEY,
    deptID INT NOT NULL,
    programDesc VARCHAR(45)
);

CREATE TABLE semester(
	semID INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    semDesc VARCHAR(45) NOT NULL
);

CREATE TABLE year(
	yearID INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    yearDesc VARCHAR(45) NOT NULL
);

CREATE TABLE section(
	sectionID INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    sectionDesc VARCHAR(45) NOT NULL
);

ALTER TABLE student
    ADD CONSTRAINT student_listerID FOREIGN KEY (listerID) REFERENCES lister(listerID),
    ADD CONSTRAINT student_programID FOREIGN KEY (programID) REFERENCES program(programID),
    ADD CONSTRAINT student_yearID FOREIGN KEY (yearID) REFERENCES year(yearID),
    ADD CONSTRAINT student_sectionID FOREIGN KEY (sectionID) REFERENCES section(sectionID),
	ADD CONSTRAINT student_deptID FOREIGN KEY (deptID) REFERENCES department(deptID);

ALTER TABLE grade
    ADD CONSTRAINT grade_studentID FOREIGN KEY (studentID) REFERENCES student(studentID),
    ADD CONSTRAINT grade_yearID FOREIGN KEY (yearID) REFERENCES year(yearID),
    ADD CONSTRAINT grade_semID FOREIGN KEY (semID) REFERENCES semester(semID);
    
ALTER TABLE program
    ADD CONSTRAINT program_deptID FOREIGN KEY (deptID) REFERENCES department(deptID);
    
INSERT INTO department VALUES (1, 'DCS');
    
INSERT INTO program VALUES (1, 1, 'BSCS');

INSERT INTO lister VALUES 
(1, ''),
(2, 'President''s Lister'),
(3, 'Dean''s Lister');

INSERT INTO semester VALUES
(1, '1st'),
(2, '2nd');

INSERT INTO year VALUES
(1, '1'),
(2, '2'),
(3, '3'),
(4, '4');

INSERT INTO section  VALUES
(1, '1'),
(2, '2'),
(3, '3'),
(4, '4'),
(5, '5'),
(6, '1N');

INSERT INTO student VALUES 
('2020-01098-MN-0', 1, 1, 2, 5, 2, 'Ysabelle', 'Mongas', 'B', ''),
('2020-06569-MN-0', 1, 1, 2, 5, 2, 'Dominic', 'Mendoza', 'A', ''),
('2020-05551-MN-0', 1, 1, 2, 5, 2, 'Joriz Ivann', 'Villanueva', 'F', ''),
('2020-07561-MN-0', 1, 1, 2, 5, 2, 'Baron David', 'Suzon', 'A', ''),
('2020-04457-MN-0', 1, 1, 2, 5, 2, 'Maila Jane', 'Padilla', 'C', '');

