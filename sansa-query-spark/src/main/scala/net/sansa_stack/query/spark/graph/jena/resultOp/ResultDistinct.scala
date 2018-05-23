package net.sansa_stack.query.spark.graph.jena.resultOp

import net.sansa_stack.query.spark.graph.jena.model.{IntermediateResult, SparkExecutionModel}
import net.sansa_stack.query.spark.graph.jena.util.Result
import org.apache.jena.graph.Node
import org.apache.jena.sparql.algebra.Op
import org.apache.jena.sparql.algebra.op.OpDistinct
import org.apache.spark.rdd.RDD
import org.apache.spark.sql.SparkSession

class ResultDistinct(op: OpDistinct) extends ResultOp {

  private val tag = "DISTINCT"

  override def execute(input: Array[Map[Node, Node]]): Array[Map[Node, Node]] = {
    input.distinct
  }

  override def execute(): Unit = {
    val oldResult = IntermediateResult.getResult(op.getSubOp.hashCode())
    val newResult = SparkExecutionModel.distinct(oldResult)
    IntermediateResult.putResult(op.hashCode(), newResult)
    IntermediateResult.removeResult(op.getSubOp.hashCode())
  }

  override def getTag: String = { tag }

  def getOp: Op = { op }
}
