CREATE TABLE "mangas" (
    "id" serial,
    "name" text,
    "year" int,
    "created" timestamp DEFAULT current_date,
    "updated" timestamp DEFAULT current_date,
    PRIMARY KEY ("id")
);

CREATE TABLE "animes" (
    "id" serial,
    "name" text,
    "year" int,
    "created" timestamp DEFAULT current_date,
    "updated" timestamp DEFAULT current_date,
    PRIMARY KEY ("id")
);

CREATE TABLE "manhwas" (
    "id" serial,
    "name" text,
    "year" int,
    "created" timestamp DEFAULT current_date,
    "updated" timestamp DEFAULT current_date,
    PRIMARY KEY ("id")
);

CREATE TABLE "television_series" (
    "id" serial,
    "name" text,
    "year" int,
    "created" timestamp DEFAULT current_date,
    "updated" timestamp DEFAULT current_date,
    PRIMARY KEY ("id")
);

CREATE TABLE "movies" (
    "id" serial,
    "name" text,
    "year" int,
    "created" timestamp DEFAULT current_date,
    "updated" timestamp DEFAULT current_date,
    PRIMARY KEY ("id")
);
