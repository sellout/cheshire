package cheshire.category

import scala.{Either, Function1, Left, Nothing, Right, Unit}

final class Set extends TRigCategory {
  type Arrow[A, B] = Function1[A, B]
  type Add[A, B] = Either[A, B]
  type Zero = Nothing
  type Multiply[A, B] = (A, B)
  type One = Unit

  def braid[A, B] = {
    case Left(a) => Right(a)
    case Right(b) => Left(b)
  }

  def leftDistribute[A, B, C] = {
    case (a, Left(b)) => Left((a, b))
    case (a, Right(c)) => Right((a, c))
  }

  def rightDistribute[A, B, C] = {
    case (Left(a), c) => Left((a, c))
    case (Right(b), c) => Right((b, c))
  }

  def leftAnnihilate[A] = _._2

  def rightAnnihilate[A] = _._1
}
