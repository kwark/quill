package io.getquill.context.cassandra

import io.getquill.Spec
import io.getquill.context.mirror.Row
import io.getquill.context.cassandra.mirrorContext._

class BindVariablesSpec extends Spec {

  "binds lifted values" in {
    def q(i: Int) =
      quote {
        query[TestEntity].filter(e => e.i == lift(i))
      }
    val mirror = mirrorContext.run(q(2))
    mirror.cql mustEqual "SELECT s, i, l, o FROM TestEntity WHERE i = ?"
    mirror.binds mustEqual Row(2)
  }
}
