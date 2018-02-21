    create table Championship (
        id integer not null,
        name varchar(255),
        primary key (id)
    );

    create table hibernate_sequence (
        next_val bigint
    );

    insert into hibernate_sequence values ( 1 );

    insert into hibernate_sequence values ( 1 );

    create table Team (
        id integer not null,
        name varchar(255),
        primary key (id)
    );

    alter table Team 
        add constraint UK_qp2b64w1p994jswu2fgfqa5yd unique (name);