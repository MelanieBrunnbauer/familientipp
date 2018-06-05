insert into soccer_team (country, fifa_code) values
('Russland','RUS'),
('Uruguay','URU'),
('Portugal','POR'),
('Spanien','ESP'),
('Marokko','MAR'),
('Iran','IRN'),
('Frankreich','FRA'),
('Australien','AUS'),
('Peru','PER'),
('Dänemark','DEN'),
('Argentinien','ARG'),
('Island','ISL'),
('Kroatien','CRO'),
('Nigeria','NGA'),
('Brasilien','BRA'),
('Schweiz','SUI'),
('Costa Rica','CRC'),
('Serbien','SRB'),
('Deutschland','GER'),
('Mexiko','MEX'),
('Schweden','SWE'),
('Südkorea','KOR'),
('Belgien','BEL'),
('Panama','PAN'),
('Tunesien','TUN'),
('England','ENG'),
('Polen','POL'),
('Senegal','SEN'),
('Kolumbien','COL'),
('Japan','JPN'),
('Saudi Arabien','KSA'),
('Ägypten','EGY');

Insert into app_user (first_name, last_name) values
('Ulla', 'Brunnbauer'),
('Matthias', 'Brunnbauer'),
('Christian', 'Brunnbauer'),
('Julia', 'Brunnbauer'),
('Benedikt', 'Brunnbauer'),
('Isabella', 'Brandis'),
('Susanne', 'Brunnbauer'),
('Anabel','Brunnbauer'),
('Michael', 'Brunnbauer'),
('Melanie', 'Brunnbauer'),
('Anton', 'Brunnbauer');

set datestyle=dmy;
Insert into game (kickoff, home_team_id, guest_team_id) values
('14/06/2018 17:00',1,31),
('15/06/2018 14:00',32,2),
('15/06/2018 17:00',5,6),
('15/06/2018 20:00',3,4),
('16/06/2018 12:00',7,8),
('16/06/2018 15:00',11,12),
('16/06/2018 18:00',9,10),
('16/06/2018 21:00',13,14),
('17/06/2018 14:00',17,18),
('17/06/2018 17:00',19,20),
('17/06/2018 20:00',15,16),
('18/06/2018 14:00',21,22),
('18/06/2018 17:00',23,24),
('18/06/2018 20:00',25,26),
('19/06/2018 14:00',29,30),
('19/06/2018 17:00',27,28),

('19/06/2018 20:00',1,32),
('20/06/2018 14:00',3,5),
('20/06/2018 17:00',2,31),
('20/06/2018 20:00',6,4),
('21/06/2018 14:00',10,8),
('21/06/2018 17:00',7,9),
('21/06/2018 20:00',11,13),
('22/06/2018 14:00',15,17),
('22/06/2018 17:00',14,12),
('22/06/2018 20:00',18,16),
('23/06/2018 14:00',23,25),
('23/06/2018 17:00',22,20),
('23/06/2018 20:00',19,21),
('24/06/2018 14:00',26,24),
('24/06/2018 17:00',30,28),
('24/06/2018 20:00',27,29),

('25/06/2018 16:00',31,32),
('25/06/2018 16:00',2,1),
('25/06/2018 20:00',6,3),
('25/06/2018 20:00',4,5),
('26/06/2018 16:00',8,9),
('26/06/2018 16:00',10,7),
('26/06/2018 20:00',14,11),
('26/06/2018 20:00',12,13);