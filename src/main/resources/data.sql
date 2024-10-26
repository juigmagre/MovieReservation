--Movie data
INSERT INTO movie (id, title, director, release_year, duration)
VALUES ('8586175d-df5c-4597-b789-906f9be2861b', 'The Matrix', 'Lana Wachowski', 2001, 2.6),
       ('b3c3301c-71fb-4c25-b477-b542bd252a63', 'Inception', 'Christopher Nolan', 1986, 1.61),
       ('909656b4-a144-4762-bc76-15096939ebfc', 'Pulp Fiction', 'Quentin Tarantino', 2021, 3.22),
       ('f87b5cc2-fcde-41db-92c5-a0e037fef65e', 'The Godfather', 'Francis Ford Coppola', 1980, 2.94),
       ('1f08e2e4-7473-400f-ad76-787916ef9ce3', 'The Dark Knight', 'Christopher Nolan', 2000, 2.48),
       ('00d4164e-26c6-48f2-99e3-183975dff9fb', 'Fight Club', 'David Fincher', 2005, 2.59),
       ('b864e85f-eb8f-4e54-bb18-dac951cde74e', 'Forrest Gump', 'Robert Zemeckis', 1997, 1.66),
       ('aa37fa28-6e51-4271-8f3e-d6db9cf2b951', 'Interstellar', 'Christopher Nolan', 2000, 3.35),
       ('fa9335d0-131a-4f10-b8bd-a29d867b6a5f', 'The Lord of the Rings', 'Peter Jackson', 2001, 2.42),
       ('c7362da7-55c4-49eb-8bb1-5be3c40a7d0d', 'Gladiator', 'Ridley Scott', 2000, 1.73),
       ('6d2205c7-0ba0-4131-84c7-477e7f4f3509', 'Titanic', 'James Cameron', 2011, 2.67),
       ('0b65295e-68d8-4060-876e-3c34dd4afc96', 'Schindlers List', 'Steven Spielberg', 2021, 1.53),
       ('7755fa9a-50c9-4ba8-bead-d7e59bfc8b2e', 'Braveheart', 'Mel Gibson', 2000, 2.93),
       ('5853134f-f49e-4a98-9ae9-cbf96c554956', 'The Shawshank Redemption', 'Frank Darabont', 1992, 1.65),
       ('f70108fa-9e5d-4edc-8dd4-110c8e561278', 'The Departed', 'Martin Scorsese', 2023, 3.29),
       ('6a9d0f61-7f32-4b9c-97c7-4ef7240e1337', 'Jurassic Park', 'Steven Spielberg', 2012, 1.82),
       ('332fb2cd-1db0-46ab-8cb5-0e4fb6788ef5', 'The Silence of the Lambs', 'Jonathan Demme', 2012, 2.75),
       ('e4792e05-f269-41ea-a56c-eee834d1c88a', 'Goodfellas', 'Martin Scorsese', 2000, 2.12),
       ('93442729-83fe-4a31-950d-10937d3f9c72', 'Saving Private Ryan', 'Steven Spielberg', 2019, 2.49),
       ('a471f0e5-3055-467a-ae45-c14eae5a6d70', 'Avatar', 'James Cameron', 2009, 2.46);

--User Data
INSERT INTO users (id, mail, username, birth_date)
VALUES ('6fdadfa1-ef91-4ec8-bbb3-3fd15d0fa1a7', 'alice@example.com', 'AliceWonder', '1990-05-10'),
       ('8d0c3d80-2a22-4f95-8b0c-3fa49d23573c', 'bob@example.com', 'BobBuilder', '1985-03-22'),
       ('a53d5e4f-6ae8-4a9a-97fc-89d21d82f7ab', 'charlie@example.com', 'CharlieChap', '1993-07-14'),
       ('d620d4a9-85c3-4b34-a437-3f09e301c515', 'dave@example.com', 'DaveWave', '1988-10-04'),
       ('f15e7bcd-8c78-4d19-bd21-2d53d5e4e1d8', 'eve@example.com', 'EveHacker', '1992-12-01');

--Reservation Data
INSERT INTO reservation (id, user_id, movie_id, start_date, end_date, cancelled)
VALUES ('84f6b09a-e8e6-4e13-ae1b-8de527f8a4e7', '6fdadfa1-ef91-4ec8-bbb3-3fd15d0fa1a7',
        '8586175d-df5c-4597-b789-906f9be2861b', '2024-01-15T10:00:00', '2024-01-15T12:36:00', false),
       ('2b9f708b-55c3-4a13-b90b-99fd5a14c9f2', '8d0c3d80-2a22-4f95-8b0c-3fa49d23573c',
        'b3c3301c-71fb-4c25-b477-b542bd252a63', '2024-02-20T14:00:00', '2024-02-20T15:36:00', false),
       ('93e4a6c9-2175-4f4c-bd8d-7e2b5de8d5cb', 'a53d5e4f-6ae8-4a9a-97fc-89d21d82f7ab',
        '909656b4-a144-4762-bc76-15096939ebfc', '2024-03-10T18:00:00', '2024-03-10T21:13:00', true),
       ('1c9d3f55-2a6d-48b9-9e95-8f89a321f708', 'd620d4a9-85c3-4b34-a437-3f09e301c515',
        'f87b5cc2-fcde-41db-92c5-a0e037fef65e', '2024-04-05T20:00:00', '2024-04-05T22:56:00', false),
       ('45a78a2d-5b5b-44d4-8d3e-6e2d9dbe2f7f', 'f15e7bcd-8c78-4d19-bd21-2d53d5e4e1d8',
        '1f08e2e4-7473-400f-ad76-787916ef9ce3', '2024-05-22T16:00:00', '2024-05-22T18:28:00', true),
       ('3b2c7c6d-828b-4c6b-8a2a-9e3791f8e7d4', '6fdadfa1-ef91-4ec8-bbb3-3fd15d0fa1a7',
        '00d4164e-26c6-48f2-99e3-183975dff9fb', '2024-06-15T11:00:00', '2024-06-15T13:35:00', false),
       ('42c6d5ad-7c6b-4c8b-a5b3-3a9d6e5e5d29', '8d0c3d80-2a22-4f95-8b0c-3fa49d23573c',
        'b864e85f-eb8f-4e54-bb18-dac951cde74e', '2024-07-30T17:00:00', '2024-07-30T18:39:00', false),
       ('7c3b5e6e-5e2d-4b5c-8a4d-3a8e7f9c5f3d', 'a53d5e4f-6ae8-4a9a-97fc-89d21d82f7ab',
        'aa37fa28-6e51-4271-8f3e-d6db9cf2b951', '2024-08-25T15:00:00', '2024-08-25T18:21:00', false),
       ('9d7f2d6a-7f6d-4e4a-b3b6-8f9d8d7c7e5a', 'd620d4a9-85c3-4b34-a437-3f09e301c515',
        'fa9335d0-131a-4f10-b8bd-a29d867b6a5f', '2024-09-10T12:00:00', '2024-09-10T14:42:00', false),
       ('5d8e6e7a-8d2f-4a6b-9d3c-7e3a9d2c5a6b', 'f15e7bcd-8c78-4d19-bd21-2d53d5e4e1d8',
        'c7362da7-55c4-49eb-8bb1-5be3c40a7d0d', '2024-10-20T19:00:00', '2024-10-20T20:43:00', true);
