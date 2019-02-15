package cheshire.category

import scala.{Either, Function1, Nothing, Unit}

final class Set extends TRigCategory {
  type Arrow[A, B] = Function1[A, B]
  type Add[A, B] = Either[A, B]
  type Zero = Nothing
  type Multiply[A, B] = (A, B)
  type One = Unit
}
