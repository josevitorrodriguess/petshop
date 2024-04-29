CREATE DATABASE petshop;
USE petshop;

CREATE TABLE client (
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    address VARCHAR(255) NOT NULL,
    number VARCHAR(255) NOT NULL
);

CREATE TABLE pet(
    id INT AUTO_INCREMENT NOT NULL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    age INT NOT NULL,
    species VARCHAR(255) NOT NULL,
    race VARCHAR(255) NOT NULL,
    clientId INT NOT NULL,
    FOREIGN KEY (clientId) REFERENCES client(id) ON DELETE CASCADE
);

CREATE TABLE service (
    id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    description VARCHAR(255) NOT NULL,
    price DOUBLE NOT NULL
);

CREATE TABLE scheduling(
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    date DATETIME NOT NULL,
    clientId INT NOT NULL,
    petId INT NOT NULL,
    serviceId INT NOT NULL,
    FOREIGN KEY (clientId) REFERENCES client(id) ON DELETE CASCADE,
    FOREIGN KEY (petId) REFERENCES pet(id) ON DELETE CASCADE,
    FOREIGN KEY (serviceId) REFERENCES service(id) ON DELETE CASCADE
);

CREATE TABLE payment(
id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
schedulingId INT NOT NULL,
payment BOOLEAN NOT NULL,
FOREIGN KEY (schedulingId) REFERENCES scheduling(id) ON DELETE CASCADE
);