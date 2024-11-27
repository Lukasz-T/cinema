DROP ALL OBJECTS;

CREATE TABLE MOVIES
(
    movie_id BIGINT,
    title    VARCHAR(500) NOT NULL,
    imdb_id  VARCHAR(9)   NOT NULL,

    PRIMARY KEY (movie_id)
);

CREATE TABLE RATINGS
(
    rating_id BIGINT,
    rating    SMALLINT(1) NOT NULL,
    movie_id  INTEGER(12) NOT NULL,

    FOREIGN KEY (movie_id) REFERENCES MOVIES (movie_id),
    PRIMARY KEY (rating_id)

);

CREATE TABLE SHOWTIMES
(
    showtime_id BIGINT,
    movie_id    INTEGER(12)   NOT NULL,
    showtime    TIMESTAMP     NOT NULL,
    price       DECIMAL(6, 2) NOT NULL,

    FOREIGN KEY (movie_id) REFERENCES MOVIES (movie_id),
    PRIMARY KEY (showtime_id)


);
INSERT INTO MOVIES (title, imdb_id)
VALUES ('THE FAST AND THE FURIOUS', 'TT0232500'),
       ('2 FAST 2 FURIOUS', 'TT0322259'),
       ('THE FAST AND THE FURIOUS: TOKYO DRIFT', 'TT0463985'),
       ('FAST & FURIOUS', 'TT1013752'),
       ('FAST FIVE', 'TT1596343'),
       ('FAST & FURIOUS 6', 'TT1905041'),
       ('FURIOUS 7', 'TT2820852'),
       ('THE FATE OF THE FURIOUS', 'TT4630562'),
       ('F9: The Fast Saga', 'tt5433138');
commit;

INSERT INTO SHOWTIMES (movie_id, showtime, price)
VALUES (1, '2024-11-02 18:00:00.00', 12),
       (2, '2024-11-02 18:00:00.00', 13),
       (3, '2024-11-04 18:00:00.00', 14),
       (4, '2024-11-04 18:00:00.00', 15),
       (5, '2024-11-06 18:00:00.00', 15),
       (6, '2024-11-06 18:00:00.00', 16),
       (7, '2024-11-08 18:00:00.00', 18),
       (8, '2024-11-10 18:00:00.00', 21),
       (9, '2024-11-12 18:00:00.00', 14);


INSERT INTO RATINGS (rating, movie_id)
VALUES (2, 1),
       (5, 2),
       (3, 3),
       (1, 4),
       (2, 5),
       (3, 6),
       (4, 7),
       (5, 8),
       (1, 9);