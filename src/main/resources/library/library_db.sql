DROP DATABASE IF EXISTS library;
CREATE DATABASE library;
USE library;

CREATE TABLE author
(
    id bigint NOT NULL AUTO_INCREMENT,
    name varchar(255) DEFAULT NULL,
    last_name varchar(255) DEFAULT NULL,
    PRIMARY KEY ( id )
);


CREATE TABLE book
(
    id BIGINT NOT NULL AUTO_INCREMENT,
    name VARCHAR(255),
    author_id BIGINT,
    PRIMARY KEY(id),
    FOREIGN KEY(author_id) REFERENCES author(id)
);


INSERT INTO author(name, last_name)
VALUES
    ('Franz', 'Kafka'),
    ('Charles', 'Dickens'),
    ('Herman', 'Melville'),
    ('Clive', 'Lewis');


INSERT INTO book(name, author_id)
VALUES
    ('The Trial', 1),
    ('The Metamorphosis', 1),
    ('A Tale of Two Cities', 2),
    ('A Christmas Carol', 2),
    ('Great Expectations', 2),
    ('Oliver Twist', 2),
    ('Typee: A Peep at Polynesian Life', 3),
    ('Omoo: A Narrative of Adventures in the South Seas', 3),
    ('Mardi: and a Voyage Thither', 3),
    ('The Lion, the Witch and the Wardrobe', 4),
    ('The Great Divorce', 4),
    ('The Four Loves', 4),
    ('The Chronicles of Narnia', 4);

-- ************************************************

select * from author;
select * from book;
