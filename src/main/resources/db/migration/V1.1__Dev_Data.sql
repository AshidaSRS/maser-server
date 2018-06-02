INSERT INTO "maser"."entertainments"("id","name","rate","model","created","updated")
VALUES
(1,E'Test Manga',8,E'manga',E'2018-05-29 00:00:00',E'2018-05-29 00:00:00'),
(2,E'Test Anime',14,E'anime',E'2018-05-29 00:00:00',E'2018-05-29 00:00:00'),
(3,E'Test Movie',22,E'movie',E'2018-05-29 00:00:00',E'2018-05-29 00:00:00'),
(4,E'Test Manhwa',32,E'manhwa',E'2018-05-29 00:00:00',E'2018-05-29 00:00:00'),
(5,E'Test Anime 2',2,E'anime',E'2018-05-30 00:00:00',E'2018-05-30 00:00:00'),
(6,E'Test Anime sdfsadf effsdfsadfsf ewf sfsd fwe fe',5,E'anime',E'2018-05-30 00:00:00',E'2018-05-30 00:00:00');

INSERT INTO "maser"."users"("id","alias","telegram","created","updated")
VALUES
(1,E'@Ashida',E'1',E'2018-06-03 00:00:00',E'2018-06-03 00:00:00'),
(2,E'@Cuwano',E'2',E'2018-06-03 00:00:00',E'2018-06-03 00:00:00');

INSERT INTO "maser"."user_contents"("id","user_id","entertainment_id","created")
VALUES
(1,1,1,E'2018-06-03 00:00:00'),
(2,1,2,E'2018-06-03 00:00:00'),
(3,2,1,E'2018-06-03 00:00:00'),
(4,2,4,E'2018-06-03 00:00:00');