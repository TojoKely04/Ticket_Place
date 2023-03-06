/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  ITU
 * Created: 27 janv. 2023
 */

CREATE USER Ticket IDENTIFIED BY Ticket;
Grant dba to Ticket;

connect Ticket/Ticket

CREATE TABLE EVENEMENT (
    id_evenement VARCHAR(4) PRIMARY KEY,
    nom VARCHAR(50),
    n_zone NUMBER,
    date_evenement VARCHAR(10)
);
Create sequence evenement_seq
INCREMENT BY 1
START WITH 2
MAXVALUE 9999999
MINVALUE 1
NOCYCLE
NOCACHE
NOORDER;

Create table ZonePublic(
    id_zone_public VARCHAR(4) PRIMARY KEY,
    id_evenement VARCHAR(4),
    n_place_total number,
    prix number(10,2),
    FOREIGN KEY(id_evenement) REFERENCES EVENEMENT(id_evenement)
);
Create sequence ZonePublic_seq
INCREMENT BY 1
START WITH 2
MAXVALUE 9999999
MINVALUE 1
NOCYCLE
NOCACHE
NOORDER;

Create table AchatZonePublic(
    id VARCHAR(4) PRIMARY KEY,
    id_evenement VARCHAR(4),
    id_zone_public VARCHAR(4),
    n_place number,
    prix number(10,2),
    FOREIGN KEY(id_evenement) REFERENCES EVENEMENT(id_evenement),
    FOREIGN KEY(id_zone_public) REFERENCES ZONEPUBLIC(id_zone_public)
);
create sequence AchatZonePublic_seq
INCREMENT BY 1
START WITH 6
MAXVALUE 9999999
MINVALUE 1
NOCYCLE
NOCACHE
NOORDER;

Create table ZoneReservee(
    id_zone_reservee VARCHAR(4) PRIMARY KEY,
    id_evenement VARCHAR(4),
    nom VARCHAR(10),
    n_place number,
    num_debut number,
    num_fin number,
    prix number(10,2),
    delai number(10,2),
    FOREIGN KEY(id_evenement) REFERENCES EVENEMENT(id_evenement)
);
Create sequence ZoneReservee_seq
INCREMENT BY 1
START WITH 5
MAXVALUE 9999999
MINVALUE 1
NOCYCLE
NOCACHE
NOORDER;

Create table PROMOTION(
    id_promotion VARCHAR(4) PRIMARY KEY,
    id_evenement VARCHAR(4),
    id_zone_reservee VARCHAR(4),
    reduction number(5,2),
    debut VARCHAR(10),
    fin VARCHAR(10),
    FOREIGN KEY(id_evenement) REFERENCES EVENEMENT(id_evenement),
    FOREIGN KEY(id_zone_reservee) REFERENCES ZONERESERVEE(id_zone_reservee)
);
Create sequence promotion_seq
INCREMENT BY 1
START WITH 2
MAXVALUE 9999999
MINVALUE 1
NOCYCLE
NOCACHE
NOORDER;

Create table Reservation(
    id_reservation VARCHAR(4) PRIMARY KEY,
    id_evenement VARCHAR(4),
    id_zone_reservee VARCHAR(4),
    place VARCHAR(250),
    date_time_reservation VARCHAR(23),
    confirmation number,
    annule number,
    prix number(10,2),
    FOREIGN KEY(id_evenement) REFERENCES EVENEMENT(id_evenement),
    FOREIGN KEY(id_zone_reservee) REFERENCES ZONERESERVEE(id_zone_reservee)
);
Create sequence reservation_seq
INCREMENT BY 1
START WITH 4
MAXVALUE 9999999
MINVALUE 1
NOCYCLE
NOCACHE
NOORDER;

--  Data

INSERT INTO EVENEMENT VALUES('E001','Evenement 1',4,'2023-04-26');

INSERT INTO ZoneReservee VALUES('ZR01','E001','Zone A',100,1,100,2000,300);
INSERT INTO ZoneReservee VALUES('ZR02','E001','Zone B',100,101,200,1700,300);
INSERT INTO ZoneReservee VALUES('ZR03','E001','Zone C',100,201,300,1400,300);
INSERT INTO ZoneReservee VALUES('ZR04','E001','Zone D',100,301,400,1100,300);

INSERT INTO ZonePublic VALUES ('ZP01','E001',1000,500);

INSERT INTO PROMOTION VALUES('P001','E001','ZR02',30,'2023-01-01','2023-01-31');

INSERT INTO RESERVATION VALUES('R001','E001','ZR01','10,11,12,13','2023-01-27T11:03:46.854',0,0,8000);
INSERT INTO RESERVATION VALUES('R002','E001','ZR03','202,205,210,230','2023-01-27T11:03:46.854',1,0,6800);
INSERT INTO RESERVATION VALUES('R003','E001','ZR04','370,371','2023-01-27T11:03:46.854',0,0,2800);

INSERT INTO AchatZonePublic VALUES('A001','E001','ZP01',10,5000);
INSERT INTO AchatZonePublic VALUES('A002','E001','ZP01',4,2000);
INSERT INTO AchatZonePublic VALUES('A003','E001','ZP01',2,1000);
INSERT INTO AchatZonePublic VALUES('A004','E001','ZP01',8,4000);
INSERT INTO AchatZonePublic VALUES('A005','E001','ZP01',6,3000);