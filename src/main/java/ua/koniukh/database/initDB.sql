CREATE TABLE IF NOT EXISTS Book
(
    name      varchar (50) NOT NULL,
    dateAdded date,
    available boolean
);

INSERT INTO Book (name, dateAdded, available)
VALUES ('Don Quixote', '2021.11.21', TRUE);
INSERT INTO Book (name, dateAdded, available)
VALUES ('The Great Gatsby', '2021.11.20', FALSE);
INSERT INTO Book (name, dateAdded, available)
VALUES ('Moby Dick', '2021.11.24', TRUE);
INSERT INTO Book (name, dateAdded, available)
VALUES ('War and Peace', '2021.11.23', FALSE);
INSERT INTO Book (name, dateAdded, available)
VALUES ('Hamlet', '2021.11.22', TRUE);
INSERT INTO Book (name, dateAdded, available)
VALUES ('The Odyssey', '2021.11.19', FALSE);