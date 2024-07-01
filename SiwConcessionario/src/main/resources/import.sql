insert into car (id, modello, marca, km, url_image) values(nextval('car_seq'), 'Yaris', 'Toyota', 8000, 'ToyotaYaris.jpeg');
insert into car (id, modello, marca, km, url_image) values(nextval('car_seq'), 'Aygo', 'Toyota', 5000, 'ToyotaAygo.jpg');
insert into car (id, modello, marca, km, url_image) values(nextval('car_seq'), 'Supra', 'Toyota', 0 , 'ToyotaSupra.png');
insert into car (id, modello, marca, km, url_image) values(nextval('car_seq'), 'C3', 'Citroen', 35000 , 'CitroenC3.jpg');
insert into car (id, modello, marca, km, url_image) values(nextval('car_seq'), 'C4', 'Citroen', 15000 , 'CitroenC4.jpg');
insert into car (id, modello, marca, km, url_image) values(nextval('car_seq'), 'C5', 'Citroen', 1200 , 'CitroenC5.jpg');
insert into car (id, modello, marca, km, url_image) values(nextval('car_seq'), 'Astra', 'Opel', 20000 , 'OpelAstra.jpg');
insert into car (id, modello, marca, km, url_image) values(nextval('car_seq'), 'Corsa', 'Opel', 10000 , 'OpelCorsa.jpg');
insert into car (id, modello, marca, km, url_image) values(nextval('car_seq'), 'Classe A', 'Mercedes', 50000 , 'MercedesClasseA.jpg');
insert into car (id, modello, marca, km, url_image) values(nextval('car_seq'), 'Classe C', 'Mercedes', 30000 , 'MercedesClasseC.jpg');
insert into car (id, modello, marca, km, url_image) values(nextval('car_seq'), 'GLA', 'Mercedes', 20000 , 'MercedesGLA.jpeg');
insert into car (id, modello, marca, km, url_image) values(nextval('car_seq'), 'Mini', 'British Leyland', 6000 , 'MrBean.png');


insert into supplier (id, name, surname, birth, url_image) values(nextval('supplier_seq'), 'Maxiauto', 'Service', '8-10-1985' , 'Maxiauto.jpg');
insert into supplier (id, name, surname, birth, url_image) values(nextval('supplier_seq'), 'Target', 'Concessionari', '20-05-1980' , 'TargetConcessionari.jpg');
insert into supplier (id, name, surname, birth, url_image) values(nextval('supplier_seq'), 'Auto', 'Land', '18-08-2008' , 'AutoLand.jpg');
insert into supplier (id, name, surname, birth, url_image) values(nextval('supplier_seq'), 'Car', 'Dealers', '03-11-2000' , 'CarDealers.jpg');
insert into supplier (id, name, surname, birth, url_image) values(nextval('supplier_seq'), 'Mr', 'Bean', '06-01-1955' , 'MrBeanMan.png');



insert into optional (id, name) values(nextval('optional_seq'), 'Climatizzatore multi-zona');
insert into optional (id, name) values(nextval('optional_seq'), 'Sensori di parcheggio');
insert into optional (id, name) values(nextval('optional_seq'), 'Navigatore');
insert into optional (id, name) values(nextval('optional_seq'), 'Park assist');
insert into optional (id, name) values(nextval('optional_seq'), 'Fari a Led');
insert into optional (id, name) values(nextval('optional_seq'), 'Sedili riscaldati');
insert into optional (id, name) values(nextval('optional_seq'), 'Sistema keyless');
insert into optional (id, name) values(nextval('optional_seq'), 'Vetri oscurati');
insert into optional (id, name) values(nextval('optional_seq'), 'Ruota di scorta');
insert into optional (id, name) values(nextval('optional_seq'), 'Telecamera posteriore');
insert into optional (id, name) values(nextval('optional_seq'), 'Cruise Control Adattivo');
insert into optional (id, name) values(nextval('optional_seq'), 'Traction Control');
insert into optional (id, name) values(nextval('optional_seq'), 'Guida assistita');
insert into optional (id, name) values(nextval('optional_seq'), 'Guida autonoma');
insert into optional (id, name) values(nextval('optional_seq'), 'Specchietti in carbonio');
insert into optional (id, name) values(nextval('optional_seq'), 'Alettone aereodinamico');