-- Create the database
CREATE DATABASE IF NOT EXISTS Rubrica;
USE Rubrica;

-- User table
CREATE TABLE IF NOT EXISTS User (
    username VARCHAR(50) NOT NULL PRIMARY KEY,
    hash     CHAR(64)    NOT NULL    -- SHA-256 hash stored as 64-character hex string
    -- Note: In a real application, always use a unique salt per user to enhance security
);

-- Contact table
CREATE TABLE IF NOT EXISTS Contact (
    firstName VARCHAR(50) NOT NULL,
    lastName  VARCHAR(50) NOT NULL,
    address   VARCHAR(255),
    phone     VARCHAR(20) PRIMARY KEY,
    age       INT,
    username  VARCHAR(50)  NOT NULL,
    FOREIGN KEY (username) REFERENCES User(username) ON DELETE CASCADE
);

-- Insert users with SHA-256 hashed passwords
INSERT INTO User (username, hash) VALUES
	-- user1 has password "user1"
    ('user1', '0a041b9462caa4a31bac3567e0b6e6fd9100787db2ab433d96f6d178cabfce90'),
    -- user2 has password "user2"
    ('user2', '6025d18fe48abd45168528f18a82e265dd98d421a7084aa09f61b341703901a3');

-- Insert contacts with username
INSERT INTO Contact (firstName, lastName, address, phone, age, username) VALUES
    ('Mario',     'Rossi',    'Via Roma 10, Milano',                 '3331234567', 30, 'user1'),
    ('Luca',      'Bianchi',  'Corso Venezia 5, Torino',             '3459876543', 25, 'user1'),
    ('Anna',      'Verdi',    'Piazza Duomo 1, Firenze',             '3298765432', 28, 'user2'),
    ('Giulia',    'Neri',     'Via Garibaldi 12, Napoli',            '3312345678', 34, 'user2'),
    ('Marco',     'Conti',    'Via Torino 8, Genova',                '3398765432', 40, 'user1'),
    ('Francesca', 'Gallo',    'Piazza San Marco 3, Venezia',         '3387654321', 22, 'user2'),
    ('Alessandro','Ricci',    'Via Dante 14, Bologna',               '3376543210', 29, 'user1'),
    ('Sara',      'Lombardi', 'Via Matteotti 7, Palermo',            '3365432109', 27, 'user1'),
    ('Paolo',     'Ferrari',  'Corso Italia 21, Verona',             '3354321098', 31, 'user2'),
    ('Elisa',     'Greco',    'Via Mazzini 18, Bari',                '3343210987', 26, 'user1'),
    ('Davide',    'Moretti',  'Via Marconi 11, Parma',               '3332109876', 33, 'user2'),
    ('Valentina', 'Costa',    'Piazza Repubblica 9, Trieste',        '3321098765', 24, 'user2'),
    ('Federico',  'Barbieri', 'Via Cavour 5, Perugia',               '3310987654', 38, 'user1'),
    ('Claudia',   'Bianco',   'Via Verdi 2, Lecce',                  '3309876543', 35, 'user2'),
    ('Matteo',    'Romano',   'Corso Garibaldi 16, Catania',         '3398765430', 28, 'user1'),
    ('Chiara',    'Fontana',  'Via Balbi 13, Genova',                '3387654329', 30, 'user2'),
    ('Stefano',   'Marini',   'Via XX Settembre 4, Ancona',          '3376543218', 32, 'user1'),
    ('Elena',     'Martini',  'Piazza Garibaldi 7, Modena',          '3365432107', 29, 'user2'),
    ('Andrea',    'Fabbri',   'Via Manzoni 6, Udine',                '3354321096', 36, 'user1'),
    ('Laura',     'Serra',    'Via Mazzini 10, Brescia',             '3343210985', 27, 'user2'),
    ('Giovanni',  'Marchetti','Corso Vittorio Emanuele 14, Pescara', '3332109874', 33, 'user1'),
    ('Marta',     'Riccardi', 'Via Santa Lucia 8, Salerno',          '3321098763', 25, 'user2'),
    ('Roberto',   'Sartori',  'Via XXV Aprile 1, Cagliari',          '3310987652', 31, 'user1'),
    ('Francesca', 'De Luca',  'Via Garibaldi 20, Reggio Emilia',     '3309876541', 29, 'user2'),
    ('Simone',    'Cattaneo', 'Piazza Dante 3, Parma',               '3398765400', 34, 'user1'),
    ('Eleonora',  'Bellini',  'Via Leopardi 15, Trento',             '3387654322', 26, 'user2'),
    ('Riccardo',  'Fontana',  'Corso Italia 7, Ravenna',             '3376543211', 28, 'user1'),
    ('Lorenzo',   'Ferri',    'Piazza Matteotti 9, Ferrara',         '3354321099', 35, 'user2'),
    ('Giulia',    'Costa',    'Via Garibaldi 11, Taranto',           '3343210988', 30, 'user1');
