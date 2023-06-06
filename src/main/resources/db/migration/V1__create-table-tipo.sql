create table tipo(
     id bigint not null auto_increment,
     nome varchar(50) not null unique,
     constraint tipopk primary key(id)
);