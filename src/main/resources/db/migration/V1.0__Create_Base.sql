CREATE TABLE "manga" (
    "id" bigint,
    "name" text,
    "year" int,
    "created" timestamp DEFAULT current_date,
    "updated" timestamp DEFAULT current_date,
    PRIMARY KEY ("id")
);

CREATE TABLE "anime" (
    "id" bigint,
    "name" text,
    "year" int,
    "created" timestamp DEFAULT current_date,
    "updated" timestamp DEFAULT current_date,
    PRIMARY KEY ("id")
);

CREATE TABLE "manhwa" (
    "id" bigint,
    "name" text,
    "year" int,
    "created" timestamp DEFAULT current_date,
    "updated" timestamp DEFAULT current_date,
    PRIMARY KEY ("id")
);

CREATE TABLE "television_serie" (
    "id" bigint,
    "name" text,
    "year" int,
    "created" timestamp DEFAULT current_date,
    "updated" timestamp DEFAULT current_date,
    PRIMARY KEY ("id")
);

CREATE TABLE "movie" (
    "id" bigint,
    "name" text,
    "year" int,
    "created" timestamp DEFAULT current_date,
    "updated" timestamp DEFAULT current_date,
    PRIMARY KEY ("id")
);
