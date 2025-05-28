-- Create the database
CREATE DATABASE IF NOT EXISTS Rubrica;
USE Rubrica;

CREATE TABLE IF NOT EXISTS Contact (
    firstName VARCHAR(50) NOT NULL,
    lastName  VARCHAR(50) NOT NULL,
    address   VARCHAR(255),
    phone     VARCHAR(20) PRIMARY KEY,
    age       INT
);

-- Create some contacts
INSERT INTO Contact (firstName, lastName, address, phone, age) VALUES
('Mario', 'Rossi', 'Via Roma 10, Milano', '3331234567', 30),
('Luca', 'Bianchi', 'Corso Venezia 5, Torino', '3459876543', 25),
('Anna', 'Verdi', 'Piazza Duomo 1, Firenze', '3298765432', 28),
('Giulia', 'Neri', 'Via Garibaldi 12, Napoli', '3312345678', 34),
('Marco', 'Conti', 'Via Torino 8, Genova', '3398765432', 40),
('Francesca', 'Gallo', 'Piazza San Marco 3, Venezia', '3387654321', 22),
('Alessandro', 'Ricci', 'Via Dante 14, Bologna', '3376543210', 29),
('Sara', 'Lombardi', 'Via Matteotti 7, Palermo', '3365432109', 27),
('Paolo', 'Ferrari', 'Corso Italia 21, Verona', '3354321098', 31),
('Elisa', 'Greco', 'Via Mazzini 18, Bari', '3343210987', 26),
('Davide', 'Moretti', 'Via Marconi 11, Parma', '3332109876', 33),
('Valentina', 'Costa', 'Piazza Repubblica 9, Trieste', '3321098765', 24),
('Federico', 'Barbieri', 'Via Cavour 5, Perugia', '3310987654', 38),
('Claudia', 'Bianco', 'Via Verdi 2, Lecce', '3309876543', 35),
('Matteo', 'Romano', 'Corso Garibaldi 16, Catania', '3398765430', 28),
('Chiara', 'Fontana', 'Via Balbi 13, Genova', '3387654329', 30),
('Stefano', 'Marini', 'Via XX Settembre 4, Ancona', '3376543218', 32),
('Elena', 'Martini', 'Piazza Garibaldi 7, Modena', '3365432107', 29),
('Andrea', 'Fabbri', 'Via Manzoni 6, Udine', '3354321096', 36),
('Laura', 'Serra', 'Via Mazzini 10, Brescia', '3343210985', 27),
('Giovanni', 'Marchetti', 'Corso Vittorio Emanuele 14, Pescara', '3332109874', 33),
('Marta', 'Riccardi', 'Via Santa Lucia 8, Salerno', '3321098763', 25),
('Roberto', 'Sartori', 'Via XXV Aprile 1, Cagliari', '3310987652', 31),
('Francesca', 'De Luca', 'Via Garibaldi 20, Reggio Emilia', '3309876541', 29),
('Simone', 'Cattaneo', 'Piazza Dante 3, Parma', '3398765400', 34),
('Eleonora', 'Bellini', 'Via Leopardi 15, Trento', '3387654322', 26),
('Riccardo', 'Fontana', 'Corso Italia 7, Ravenna', '3376543211', 28),
('Lorenzo', 'Ferri', 'Piazza Matteotti 9, Ferrara', '3354321099', 35),
('Giulia', 'Costa', 'Via Garibaldi 11, Taranto', '3343210988', 30);
