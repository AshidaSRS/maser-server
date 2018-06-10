
create table "recommendations" (
   "id" serial,
   "recommender_id" bigint not null references users (id),
   "entertainment_id" bigint not null references entertainments(id),
   "recommended_id" bigint not null references users(id),
   "created" timestamp default current_date,
   "updated" timestamp default current_date,
   primary key ("id")
);
