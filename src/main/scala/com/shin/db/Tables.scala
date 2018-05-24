package com.shin.db
// AUTO-GENERATED Slick data model
/** Stand-alone Slick data model for immediate use */
object Tables extends {
  val profile = slick.jdbc.MySQLProfile
} with Tables

/** Slick data model trait for extension, choice of backend or usage in the cake pattern. (Make sure to initialize this late.) */
trait Tables {
  val profile: slick.jdbc.JdbcProfile
  import profile.api._
  import slick.model.ForeignKeyAction
  // NOTE: GetResult mappers for plain SQL are only generated for tables where Slick knows how to map the types of all columns.
  import slick.jdbc.{GetResult => GR}

  /** DDL for all tables. Call .create to execute. */
  lazy val schema: profile.SchemaDescription = Array(Anime.schema, Manga.schema, Manhwa.schema, Movie.schema, SchemaVersion.schema, TelevisionSerie.schema).reduceLeft(_ ++ _)
  @deprecated("Use .schema instead of .ddl", "3.0")
  def ddl = schema

  /** Entity class storing rows of table Anime
   *  @param name Database column name SqlType(VARCHAR), Length(45,true)
   *  @param created Database column created SqlType(TIMESTAMP)
   *  @param updated Database column updated SqlType(TIMESTAMP)
   *  @param id Database column id SqlType(BIGINT), AutoInc, PrimaryKey */
  case class AnimeRow(name: String, created: Option[java.sql.Timestamp], updated: Option[java.sql.Timestamp], id: Option[Long] = None)
  /** GetResult implicit for fetching AnimeRow objects using plain SQL queries */
  implicit def GetResultAnimeRow(implicit e0: GR[String], e1: GR[Option[java.sql.Timestamp]], e2: GR[Option[Long]]): GR[AnimeRow] = GR{
    prs => import prs._
    val r = (<<?[Long], <<[String], <<?[java.sql.Timestamp], <<?[java.sql.Timestamp])
    import r._
    AnimeRow.tupled((_2, _3, _4, _1)) // putting AutoInc last
  }
  /** Table description of table anime. Objects of this class serve as prototypes for rows in queries. */
  class Anime(_tableTag: Tag) extends profile.api.Table[AnimeRow](_tableTag, Some("maser"), "anime") {
    def * = (name, created, updated, Rep.Some(id)) <> (AnimeRow.tupled, AnimeRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(name), created, updated, Rep.Some(id)).shaped.<>({r=>import r._; _1.map(_=> AnimeRow.tupled((_1.get, _2, _3, _4)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column name SqlType(VARCHAR), Length(45,true) */
    val name: Rep[String] = column[String]("name", O.Length(45,varying=true))
    /** Database column created SqlType(TIMESTAMP) */
    val created: Rep[Option[java.sql.Timestamp]] = column[Option[java.sql.Timestamp]]("created")
    /** Database column updated SqlType(TIMESTAMP) */
    val updated: Rep[Option[java.sql.Timestamp]] = column[Option[java.sql.Timestamp]]("updated")
    /** Database column id SqlType(BIGINT), AutoInc, PrimaryKey */
    val id: Rep[Long] = column[Long]("id", O.AutoInc, O.PrimaryKey)
  }
  /** Collection-like TableQuery object for table Anime */
  lazy val Anime = new TableQuery(tag => new Anime(tag))

  /** Entity class storing rows of table Manga
   *  @param id Database column id SqlType(BIGINT), PrimaryKey
   *  @param name Database column name SqlType(VARCHAR), Length(45,true), Default(None)
   *  @param created Database column created SqlType(TIMESTAMP)
   *  @param updated Database column updated SqlType(TIMESTAMP) */
  case class MangaRow(id: Long, name: Option[String] = None, created: Option[java.sql.Timestamp], updated: Option[java.sql.Timestamp])
  /** GetResult implicit for fetching MangaRow objects using plain SQL queries */
  implicit def GetResultMangaRow(implicit e0: GR[Long], e1: GR[Option[String]], e2: GR[Option[java.sql.Timestamp]]): GR[MangaRow] = GR{
    prs => import prs._
    val r = (<<[Long], <<?[String], <<?[java.sql.Timestamp], <<?[java.sql.Timestamp])
    import r._
    MangaRow.tupled((_1, _2, _3, _4)) // putting AutoInc last
  }
  /** Table description of table manga. Objects of this class serve as prototypes for rows in queries. */
  class Manga(_tableTag: Tag) extends profile.api.Table[MangaRow](_tableTag, Some("maser"), "manga") {
    def * = (id, name, created, updated) <> (MangaRow.tupled, MangaRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(id), name, created, updated).shaped.<>({r=>import r._; _1.map(_=> MangaRow.tupled((_1.get, _2, _3, _4)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column id SqlType(BIGINT), PrimaryKey */
    val id: Rep[Long] = column[Long]("id", O.PrimaryKey)
    /** Database column name SqlType(VARCHAR), Length(45,true), Default(None) */
    val name: Rep[Option[String]] = column[Option[String]]("name", O.Length(45,varying=true), O.Default(None))
    /** Database column created SqlType(TIMESTAMP) */
    val created: Rep[Option[java.sql.Timestamp]] = column[Option[java.sql.Timestamp]]("created")
    /** Database column updated SqlType(TIMESTAMP) */
    val updated: Rep[Option[java.sql.Timestamp]] = column[Option[java.sql.Timestamp]]("updated")
  }
  /** Collection-like TableQuery object for table Manga */
  lazy val Manga = new TableQuery(tag => new Manga(tag))

  /** Entity class storing rows of table Manhwa
   *  @param name Database column name SqlType(VARCHAR), Length(45,true)
   *  @param created Database column created SqlType(TIMESTAMP)
   *  @param updated Database column updated SqlType(TIMESTAMP)
   *  @param id Database column id SqlType(BIGINT), AutoInc, PrimaryKey */
  case class ManhwaRow(name: String, created: Option[java.sql.Timestamp], updated: Option[java.sql.Timestamp], id: Option[Long] = None)
  /** GetResult implicit for fetching ManhwaRow objects using plain SQL queries */
  implicit def GetResultManhwaRow(implicit e0: GR[String], e1: GR[Option[java.sql.Timestamp]], e2: GR[Option[Long]]): GR[ManhwaRow] = GR{
    prs => import prs._
    val r = (<<?[Long], <<[String], <<?[java.sql.Timestamp], <<?[java.sql.Timestamp])
    import r._
    ManhwaRow.tupled((_2, _3, _4, _1)) // putting AutoInc last
  }
  /** Table description of table manhwa. Objects of this class serve as prototypes for rows in queries. */
  class Manhwa(_tableTag: Tag) extends profile.api.Table[ManhwaRow](_tableTag, Some("maser"), "manhwa") {
    def * = (name, created, updated, Rep.Some(id)) <> (ManhwaRow.tupled, ManhwaRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(name), created, updated, Rep.Some(id)).shaped.<>({r=>import r._; _1.map(_=> ManhwaRow.tupled((_1.get, _2, _3, _4)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column name SqlType(VARCHAR), Length(45,true) */
    val name: Rep[String] = column[String]("name", O.Length(45,varying=true))
    /** Database column created SqlType(TIMESTAMP) */
    val created: Rep[Option[java.sql.Timestamp]] = column[Option[java.sql.Timestamp]]("created")
    /** Database column updated SqlType(TIMESTAMP) */
    val updated: Rep[Option[java.sql.Timestamp]] = column[Option[java.sql.Timestamp]]("updated")
    /** Database column id SqlType(BIGINT), AutoInc, PrimaryKey */
    val id: Rep[Long] = column[Long]("id", O.AutoInc, O.PrimaryKey)
  }
  /** Collection-like TableQuery object for table Manhwa */
  lazy val Manhwa = new TableQuery(tag => new Manhwa(tag))

  /** Entity class storing rows of table Movie
   *  @param name Database column name SqlType(VARCHAR), Length(45,true)
   *  @param year Database column year SqlType(INT)
   *  @param created Database column created SqlType(TIMESTAMP)
   *  @param updated Database column updated SqlType(TIMESTAMP)
   *  @param id Database column id SqlType(BIGINT), AutoInc, PrimaryKey */
  case class MovieRow(name: String, year: Int, created: Option[java.sql.Timestamp], updated: Option[java.sql.Timestamp], id: Option[Long] = None)
  /** GetResult implicit for fetching MovieRow objects using plain SQL queries */
  implicit def GetResultMovieRow(implicit e0: GR[String], e1: GR[Int], e2: GR[Option[java.sql.Timestamp]], e3: GR[Option[Long]]): GR[MovieRow] = GR{
    prs => import prs._
    val r = (<<?[Long], <<[String], <<[Int], <<?[java.sql.Timestamp], <<?[java.sql.Timestamp])
    import r._
    MovieRow.tupled((_2, _3, _4, _5, _1)) // putting AutoInc last
  }
  /** Table description of table movie. Objects of this class serve as prototypes for rows in queries. */
  class Movie(_tableTag: Tag) extends profile.api.Table[MovieRow](_tableTag, Some("maser"), "movie") {
    def * = (name, year, created, updated, Rep.Some(id)) <> (MovieRow.tupled, MovieRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(name), Rep.Some(year), created, updated, Rep.Some(id)).shaped.<>({r=>import r._; _1.map(_=> MovieRow.tupled((_1.get, _2.get, _3, _4, _5)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column name SqlType(VARCHAR), Length(45,true) */
    val name: Rep[String] = column[String]("name", O.Length(45,varying=true))
    /** Database column year SqlType(INT) */
    val year: Rep[Int] = column[Int]("year")
    /** Database column created SqlType(TIMESTAMP) */
    val created: Rep[Option[java.sql.Timestamp]] = column[Option[java.sql.Timestamp]]("created")
    /** Database column updated SqlType(TIMESTAMP) */
    val updated: Rep[Option[java.sql.Timestamp]] = column[Option[java.sql.Timestamp]]("updated")
    /** Database column id SqlType(BIGINT), AutoInc, PrimaryKey */
    val id: Rep[Long] = column[Long]("id", O.AutoInc, O.PrimaryKey)
  }
  /** Collection-like TableQuery object for table Movie */
  lazy val Movie = new TableQuery(tag => new Movie(tag))

  /** Entity class storing rows of table SchemaVersion
   *  @param installedRank Database column installed_rank SqlType(INT), PrimaryKey
   *  @param version Database column version SqlType(VARCHAR), Length(50,true), Default(None)
   *  @param description Database column description SqlType(VARCHAR), Length(200,true)
   *  @param `type` Database column type SqlType(VARCHAR), Length(20,true)
   *  @param script Database column script SqlType(VARCHAR), Length(1000,true)
   *  @param checksum Database column checksum SqlType(INT), Default(None)
   *  @param installedBy Database column installed_by SqlType(VARCHAR), Length(100,true)
   *  @param installedOn Database column installed_on SqlType(TIMESTAMP)
   *  @param executionTime Database column execution_time SqlType(INT)
   *  @param success Database column success SqlType(BIT) */
  case class SchemaVersionRow(installedRank: Int, version: Option[String] = None, description: String, `type`: String, script: String, checksum: Option[Int] = None, installedBy: String, installedOn: java.sql.Timestamp, executionTime: Int, success: Boolean)
  /** GetResult implicit for fetching SchemaVersionRow objects using plain SQL queries */
  implicit def GetResultSchemaVersionRow(implicit e0: GR[Int], e1: GR[Option[String]], e2: GR[String], e3: GR[Option[Int]], e4: GR[java.sql.Timestamp], e5: GR[Boolean]): GR[SchemaVersionRow] = GR{
    prs => import prs._
    val r = (<<[Int], <<?[String], <<[String], <<[String], <<[String], <<?[Int], <<[String], <<[java.sql.Timestamp], <<[Int], <<[Boolean])
    import r._
    SchemaVersionRow.tupled((_1, _2, _3, _4, _5, _6, _7, _8, _9, _10)) // putting AutoInc last
  }
  /** Table description of table schema_version. Objects of this class serve as prototypes for rows in queries.
   *  NOTE: The following names collided with Scala keywords and were escaped: type */
  class SchemaVersion(_tableTag: Tag) extends profile.api.Table[SchemaVersionRow](_tableTag, Some("maser"), "schema_version") {
    def * = (installedRank, version, description, `type`, script, checksum, installedBy, installedOn, executionTime, success) <> (SchemaVersionRow.tupled, SchemaVersionRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(installedRank), version, Rep.Some(description), Rep.Some(`type`), Rep.Some(script), checksum, Rep.Some(installedBy), Rep.Some(installedOn), Rep.Some(executionTime), Rep.Some(success)).shaped.<>({r=>import r._; _1.map(_=> SchemaVersionRow.tupled((_1.get, _2, _3.get, _4.get, _5.get, _6, _7.get, _8.get, _9.get, _10.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column installed_rank SqlType(INT), PrimaryKey */
    val installedRank: Rep[Int] = column[Int]("installed_rank", O.PrimaryKey)
    /** Database column version SqlType(VARCHAR), Length(50,true), Default(None) */
    val version: Rep[Option[String]] = column[Option[String]]("version", O.Length(50,varying=true), O.Default(None))
    /** Database column description SqlType(VARCHAR), Length(200,true) */
    val description: Rep[String] = column[String]("description", O.Length(200,varying=true))
    /** Database column type SqlType(VARCHAR), Length(20,true)
     *  NOTE: The name was escaped because it collided with a Scala keyword. */
    val `type`: Rep[String] = column[String]("type", O.Length(20,varying=true))
    /** Database column script SqlType(VARCHAR), Length(1000,true) */
    val script: Rep[String] = column[String]("script", O.Length(1000,varying=true))
    /** Database column checksum SqlType(INT), Default(None) */
    val checksum: Rep[Option[Int]] = column[Option[Int]]("checksum", O.Default(None))
    /** Database column installed_by SqlType(VARCHAR), Length(100,true) */
    val installedBy: Rep[String] = column[String]("installed_by", O.Length(100,varying=true))
    /** Database column installed_on SqlType(TIMESTAMP) */
    val installedOn: Rep[java.sql.Timestamp] = column[java.sql.Timestamp]("installed_on")
    /** Database column execution_time SqlType(INT) */
    val executionTime: Rep[Int] = column[Int]("execution_time")
    /** Database column success SqlType(BIT) */
    val success: Rep[Boolean] = column[Boolean]("success")

    /** Index over (success) (database name schema_version_s_idx) */
    val index1 = index("schema_version_s_idx", success)
  }
  /** Collection-like TableQuery object for table SchemaVersion */
  lazy val SchemaVersion = new TableQuery(tag => new SchemaVersion(tag))

  /** Entity class storing rows of table TelevisionSerie
   *  @param name Database column name SqlType(VARCHAR), Length(45,true)
   *  @param year Database column year SqlType(INT)
   *  @param created Database column created SqlType(TIMESTAMP)
   *  @param updated Database column updated SqlType(TIMESTAMP)
   *  @param id Database column id SqlType(BIGINT), AutoInc, PrimaryKey */
  case class TelevisionSerieRow(name: String, year: Int, created: Option[java.sql.Timestamp], updated: Option[java.sql.Timestamp], id: Option[Long] = None)
  /** GetResult implicit for fetching TelevisionSerieRow objects using plain SQL queries */
  implicit def GetResultTelevisionSerieRow(implicit e0: GR[String], e1: GR[Int], e2: GR[Option[java.sql.Timestamp]], e3: GR[Option[Long]]): GR[TelevisionSerieRow] = GR{
    prs => import prs._
    val r = (<<?[Long], <<[String], <<[Int], <<?[java.sql.Timestamp], <<?[java.sql.Timestamp])
    import r._
    TelevisionSerieRow.tupled((_2, _3, _4, _5, _1)) // putting AutoInc last
  }
  /** Table description of table television_serie. Objects of this class serve as prototypes for rows in queries. */
  class TelevisionSerie(_tableTag: Tag) extends profile.api.Table[TelevisionSerieRow](_tableTag, Some("maser"), "television_serie") {
    def * = (name, year, created, updated, Rep.Some(id)) <> (TelevisionSerieRow.tupled, TelevisionSerieRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(name), Rep.Some(year), created, updated, Rep.Some(id)).shaped.<>({r=>import r._; _1.map(_=> TelevisionSerieRow.tupled((_1.get, _2.get, _3, _4, _5)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column name SqlType(VARCHAR), Length(45,true) */
    val name: Rep[String] = column[String]("name", O.Length(45,varying=true))
    /** Database column year SqlType(INT) */
    val year: Rep[Int] = column[Int]("year")
    /** Database column created SqlType(TIMESTAMP) */
    val created: Rep[Option[java.sql.Timestamp]] = column[Option[java.sql.Timestamp]]("created")
    /** Database column updated SqlType(TIMESTAMP) */
    val updated: Rep[Option[java.sql.Timestamp]] = column[Option[java.sql.Timestamp]]("updated")
    /** Database column id SqlType(BIGINT), AutoInc, PrimaryKey */
    val id: Rep[Long] = column[Long]("id", O.AutoInc, O.PrimaryKey)
  }
  /** Collection-like TableQuery object for table TelevisionSerie */
  lazy val TelevisionSerie = new TableQuery(tag => new TelevisionSerie(tag))
}
