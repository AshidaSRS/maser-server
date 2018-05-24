package com.shin.utils

import wvlet.log.LogFormatter.SourceCodeLogFormatter
import wvlet.log.LogLevel._
import wvlet.log.Logger


trait LoggerIntegration {

  lazy val log: Logger = Logger("LoggerIntegration")
  log.setLogLevel(DEBUG)
  log.setFormatter(SourceCodeLogFormatter)

}
