CREATE TABLE "maser"."manga" (
    "id" bigint,
    "name" text,
    "year" int,
    "created" timestamp DEFAULT current_date,
    "updated" timestamp DEFAULT current_date,
    PRIMARY KEY ("id")
);

CREATE TABLE "maser"."anime" (
    "id" bigint,
    "name" text,
    "year" int,
    "created" timestamp DEFAULT current_date,
    "updated" timestamp DEFAULT current_date,
    PRIMARY KEY ("id")
);

CREATE TABLE "maser"."manhwa" (
    "id" bigint,
    "name" text,
    "year" int,
    "created" timestamp DEFAULT current_date,
    "updated" timestamp DEFAULT current_date,
    PRIMARY KEY ("id")
);

CREATE TABLE "maser"."television_serie" (
    "id" bigint,
    "name" text,
    "year" int,
    "created" timestamp DEFAULT current_date,
    "updated" timestamp DEFAULT current_date,
    PRIMARY KEY ("id")
);

CREATE TABLE "maser"."movie" (
    "id" bigint,
    "name" text,
    "year" int,
    "created" timestamp DEFAULT current_date,
    "updated" timestamp DEFAULT current_date,
    PRIMARY KEY ("id")
);
