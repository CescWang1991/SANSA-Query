package net.sansa_stack.querying.spark.utils
import org.slf4j.LoggerFactory

trait Logging {
  protected val logger = LoggerFactory.getLogger(getClass.getName)
}