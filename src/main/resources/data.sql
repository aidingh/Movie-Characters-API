



INSERT INTO characters(alias, full_name, gender, picture_url) VALUES ('Adam', 'Hellsinborg', 'Man', 'Dota.org');
INSERT INTO characters(alias, full_name, gender, picture_url) VALUES ('Gini', 'Aidin Ghassemloi', 'male', 'pokemonpics.org');
INSERT INTO movie(movie_title, genre, release_year, director,picture,trailer_link) VALUES ('killerbiller', 'Drama', '2000', 'Rickard','link.nu','link.nu');

INSERT INTO movie_characters(movie_id, characters_id) VALUES (1,1);


INSERT INTO franchise(name, description) VALUES ('STAR TRILOGY', 'mega awesome movie');

INSERT INTO movie(movie_title, genre, release_year, director,picture,trailer_link,franchise_id) VALUES ('death', 'horror', '2000', 'aidin','seed.nu','death.nu',1);

