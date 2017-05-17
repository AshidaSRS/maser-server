package maser

import info.mukel.telegrambot4s.models._

trait UtilsCommand {
  def unTag(tag: String, s: String): String = s.stripPrefix(tag)

  // def unTagGame(tag: String, s: String): CallbackGame

  def getButtonParam(s: String) = {
    val params = s.split("::")
    val res = params.sliding(2).toList
    res.zipWithIndex.filter(_._2 % 2 == 0).map(_._1)
  }
}

object UtilsCommand
