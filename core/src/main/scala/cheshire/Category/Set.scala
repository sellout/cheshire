package cheshire.category

import scala.{Either, Function1, Left, Nothing, Right, Unit}

import cheshire.{FunctionB, ProProduct}

package object meh {
  def Set: TRigCategory[Function1] = new TRigCategory[Function1] { outer =>
    def op = new FunctionB[ProProduct[Function1, Function1, ?, ?], Function1] {
      def apply[A, B](fab: ProProduct[Function1, Function1, A, B]) =
        fab.f.compose(fab.g)
    }
    def identity[A] = scala.Predef.identity[A](_)

    def additive = new TSymmetricMonoidalCategory[Function1] {
      type Product[A, B] = Either[A, B]
      type Identity = Nothing

      // TODO: Would be nice to not have to manually map these two.
      def op = outer.op
      def identity[A] = outer.identity[A]

      def associate[A, B, C] = {
        case Left(Left(a)) => Left(a)
        case Left(Right(b)) => Right(Left(b))
        case Right(c) => Right(Right(c))
      }
      def leftUnit[A] = {
        // FIXME: This should be using the cocartesian identity op.
        case Left(_) => scala.Predef.???
        case Right(a) => a
      }
      def rightUnit[A] = {
        case Left(a) => a
        // FIXME: This should be using the cocartesian identity op.
        case Right(_) => scala.Predef.???
      }
      def braid[A, B] = {
        case Left(a) => Right(a)
        case Right(b) => Left(b)
      }
    }

    def multiplicative = new TSymmetricMonoidalCategory[Function1] {
      type Product[A, B] = (A, B)
      type Identity = Unit

      // TODO: Would be nice to not have to manually map these two.
      def op = outer.op
      def identity[A] = outer.identity[A]

      def associate[A, B, C] = x => (x._1._1, (x._1._2, x._2))
      def leftUnit[A] = _._2
      def rightUnit[A] = _._1
      def braid[A, B] = {
        case (a, b) => (b, a)
      }
    }

    def leftDistribute[A, B, C]
        : (A, Either[B, C]) => Either[(A, B), (A, C)] = {
      case (a, Left(b)) => Left((a, b))
      case (a, Right(c)) => Right((a, c))
    }

    def rightDistribute[A, B, C]
        : (Either[A, B], C) => Either[(A, C), (B, C)] = {
      case (Left(a), c) => Left((a, c))
      case (Right(b), c) => Right((b, c))
    }

    def leftAnnihilate[A]: (A, Nothing) => Nothing = (_: A, y: Nothing) => y

    def rightAnnihilate[A]: (Nothing, A) => Nothing = (x: Nothing, _: A) => x
  }
}
