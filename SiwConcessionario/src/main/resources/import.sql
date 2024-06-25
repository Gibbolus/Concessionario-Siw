insert into car (id, modello, marca, km, url_image) values(nextval('car_seq'), 'Yaris', 'Toyota', 8000, 'ToyotaYaris.jpeg');
insert into car (id, modello, marca, km, url_image) values(nextval('car_seq'), 'Aygo', 'Toyota', 5000, 'ToyotaAygo.jpg');
insert into car (id, modello, marca, km, url_image) values(nextval('car_seq'), 'Supra', 'Toyota',0 , 'ToyotaSupra.png');
insert into car (id, modello, marca, km, url_image) values(nextval('car_seq'), 'C3', 'Citroen',35000 , 'CitroenC3.jpg');
insert into car (id, modello, marca, km, url_image) values(nextval('car_seq'), 'C4', 'Citroen',15000 , 'CitroenC4.jpg');




insert into supplier (id, name, surname, birth, url_image) values(nextval('supplier_seq'), 'Maxiauto', 'Service', '8-10-1965' , 'Maxiauto.jpg');



insert into optional (id, name) values(nextval('optional_seq'), 'telecamera posteriore');
insert into optional (id, name) values(nextval('optional_seq'), 'guida assistita');
insert into optional (id, name) values(nextval('optional_seq'), 'ruota di scorta');
insert into optional (id, name) values(nextval('optional_seq'), 'specchietti in carbonio');
