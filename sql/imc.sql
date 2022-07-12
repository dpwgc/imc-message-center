create table imc_chat_message
(
    id           bigint auto_increment
        primary key,
    message_id   varchar(127) not null,
    app_id       varchar(127) not null,
    group_id     varchar(127) not null,
    user_id      varchar(127) not null,
    content      text         null,
    create_time  bigint       not null,
    recall_time  bigint       null,
    recall_cause varchar(255) null,
    status       int          null,
    type         int          null,
    constraint imc_chat_message_id_uindex
        unique (id)
);

create table imc_notice_information
(
    id             bigint auto_increment
        primary key,
    information_id varchar(127) not null,
    app_id         varchar(127) not null,
    group_id       varchar(127) not null,
    user_id        varchar(127) not null,
    title          varchar(255) null,
    content        text         null,
    create_time    bigint       null,
    delete_time    bigint       null,
    jump_url       text         null,
    status         int          null,
    type           int          null,
    constraint imc_notice_information_id_uindex
        unique (id)
);