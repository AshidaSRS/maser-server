create table "entertainments" (
    "id" serial,
    "name" text,
    "rate" int,
    "model" text not null,
    "created" timestamp default current_date,
    "updated" timestamp default current_date,
    primary key ("id")
);

create table "users" (
    "id" serial,
    "alias" text not null,
    "telegram_id" bigint not null,
    "created" timestamp default current_date,
    "updated" timestamp default current_date,
    primary key ("id")
);

create table "user_contents" (
   "id" serial,
   "user_id" bigint not null references users (id),
   "entertainment_id" bigint not null references entertainments(id),
   "created" timestamp default current_date,
   primary key ("id")
);
