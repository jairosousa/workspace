create table posts
(
    id uuid default random_uuid() not null primary key,
    usuario       varchar(100),
    content    varchar(256),
    created_at TIMESTAMP WITH TIME ZONE
);