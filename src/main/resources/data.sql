

INSERT INTO characters(alias, full_name, gender, picture_url) VALUES ('Adam', 'Hellsinborg', 'male', 'Dota.org');
INSERT INTO characters(alias, full_name, gender, picture_url) VALUES ('Gini', 'Aidin Ghassemloi', 'male', 'pokemonpics.org');
INSERT INTO characters(alias, full_name, gender, picture_url) VALUES ('Rickard', 'RIcks', 'male', 'Dota.org');
INSERT INTO characters(alias, full_name, gender, picture_url) VALUES ('Jacob', 'Cruz', 'male', 'licklick.org');
INSERT INTO characters(alias, full_name, gender, picture_url) VALUES ('Johanna', 'Svensson', 'female', 'lol.nu');
INSERT INTO characters(alias, full_name, gender, picture_url) VALUES ('Bilbo', 'Anus', 'male', 'hejhej.org');
INSERT INTO characters(alias, full_name, gender, picture_url) VALUES ('XXX', 'Kasper Illesson', 'male', 'hejhej.org');

INSERT INTO movie(movie_title, genre, release_year, director,picture,trailer_link) VALUES ('killerbiller', 'Drama', '2000', 'Rickard','link.nu','link.nu');
INSERT INTO movie(movie_title, genre, release_year, director,picture,trailer_link) VALUES ('Batman', 'Thriller', '2022', 'Revees','Bat.nu','Batman.nu');
INSERT INTO movie(movie_title, genre, release_year, director,picture,trailer_link) VALUES ('Joker', 'Horror', '1999', 'Nolan','link.nu','link.nu');
INSERT INTO movie(movie_title, genre, release_year, director,picture,trailer_link) VALUES ('Avengers', 'Action', '2044', 'Revees','Aveng.nu','Avengers.nu');


INSERT INTO franchise(name, description) VALUES ('STAR TRILOGY', 'mega awesome movie');
INSERT INTO franchise(name, description) VALUES ('DC Comics', 'bat bat bat');
INSERT INTO franchise(name, description) VALUES ('Atomic guy', 'kinda sucks tbh');
INSERT INTO franchise(name, description) VALUES ('The infidels', 'bom bom bom');


INSERT INTO movie_characters(movie_id, characters_id) VALUES (1,2);
INSERT INTO movie_characters(movie_id, characters_id) VALUES (1,3);
INSERT INTO movie_characters(movie_id, characters_id) VALUES (2,3);
INSERT INTO movie_characters(movie_id, characters_id) VALUES (3,6);
INSERT INTO movie_characters(movie_id, characters_id) VALUES (4,5);
INSERT INTO movie_characters(movie_id, characters_id) VALUES (2,7);


SELECT * FROM movie_characters;
SELECT * FROM characters;
SELECT * FROM movie;
SELECT * FROM franchise;