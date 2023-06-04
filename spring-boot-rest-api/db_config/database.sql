create table if not exists users
(
    id         serial PRIMARY KEY,
    first_name varchar(50),
    last_name  varchar(50),
    mail       varchar(50),
    password   varchar(250)
);

create table if not exists follows (
    user_id int,
    follower_id int,
    timestamp TIMESTAMP DEFAULT now(),
    FOREIGN KEY(user_id) REFERENCES users(id) on delete cascade,
    FOREIGN KEY(follower_id) REFERENCES users(id) on delete cascade
);

create table if not exists posts
(
    id         serial PRIMARY KEY,
    message      varchar(100),
    timestamp TIMESTAMP DEFAULT now(),
    user_id    int,
    FOREIGN KEY(user_id) REFERENCES users(id) on delete cascade
);

create table if not exists replies (
    id serial PRIMARY KEY,
    post_id int,
    parent int references posts(id),
    public_post bool,
    FOREIGN KEY(post_id) REFERENCES posts(id) on delete cascade,
    FOREIGN KEY(parent) REFERENCES posts(id) on delete cascade
);

create table if not exists post_reply (
    post_id int,
    reply_id int,
    FOREIGN KEY (post_id) REFERENCES posts(id) on delete cascade,
    FOREIGN KEY (reply_id) REFERENCES replies(id) on delete cascade
);

create table if not exists likes (
    post_id int,
    user_id int,
    FOREIGN KEY (post_id) REFERENCES posts(id) on delete cascade,
    FOREIGN KEY (user_id) REFERENCES users(id) on delete cascade
);

create table if not exists mentions (
    post_id int,
    user_id int,
    FOREIGN KEY (post_id) REFERENCES posts(id) on delete cascade,
    FOREIGN KEY (user_id) REFERENCES users(id) on delete cascade
);

