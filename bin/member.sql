use java_db;

create table member(
  idx int(11) NOT NULL AUTO_INCREMENT,       
  name varchar(20) NOT NULL,
  attend int(11) NULL,
  absent int(11) DEFAULT NULL,
  late int(11) DEFAULT NULL,
  addr text,
  phone varchar(20) DEFAULT NULL,
  email varchar(40) DEFAULT NULL,
  PRIMARY KEY(idx)
)