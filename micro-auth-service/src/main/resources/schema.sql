create table if not exists oauth_client_details
(
    client_id               varchar(255) not null,
    client_secret           varchar(255) not null,
    web_server_redirect_uri varchar(2048) default null,
    scope                   varchar(255)  default null,
    access_token_validity   int(11)       default null,
    refresh_token_validity  int(11)       default null,
    resource_ids            varchar(1024) default null,
    authorized_grant_types  varchar(1024) default null,
    authorities             varchar(1024) default null,
    additional_information  varchar(4096) default null,
    autoapprove             varchar(255)  default null,
    primary key (client_id)
);

create table if not exists oauth_access_token
(
    authentication_id varchar(255) not null primary key,
    token_id          varchar(255) not null,
    token             blob         not null,
    user_name         varchar(255) not null,
    client_id         varchar(255) not null,
    authentication    blob         not null,
    refresh_token     varchar(255) not null
);

create table if not exists oauth_refresh_token
(
    token_id       varchar(255) not null,
    token          blob         not null,
    authentication blob         not null
);

create table if not exists permission
(
    id   int(11) not null auto_increment PRIMARY KEY,
    name varchar(512) default null,
    unique key permission_name (name)
);

create table if not exists role
(
    id   int(11) not null auto_increment,
    name varchar(255) default null,
    primary key (id),
    unique key role_name (name)
);

create table if not exists user
(
    id                    int(11)       not null auto_increment,
    username              varchar(100)  not null,
    password              varchar(1024) not null,
    email                 varchar(1024),
    enabled               boolean not null,
    accountNonExpired     boolean not null,
    credentialsNonExpired boolean not null,
    accountNonLocked      boolean not null,
    primary key (id),
    unique key username (username)
);

create table if not exists permission_role
(
    permission_id int(11) default null,
    role_id       int(11) default null,
    foreign key (permission_id) references permission (id),
    foreign key (role_id) references role (id)
);

create table if not exists role_user
(
    role_id int(11) default null,
    user_id int(11) default null,
    foreign key (role_id) references role (id),
    foreign key (user_id) references user (id)
);
