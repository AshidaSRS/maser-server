// Generated by <a href="http://scalaxb.org/">scalaxb</a>.
package models


case class Entry(id: Int,
  title: String,
  english: String,
  synonyms: String,
  episodes: Int,
  typeValue: String,
  status: String,
  start_date: javax.xml.datatype.XMLGregorianCalendar,
  end_date: javax.xml.datatype.XMLGregorianCalendar,
  synopsis: String,
  image: String)
      


case class Anime(entry: models.Entry)
      
