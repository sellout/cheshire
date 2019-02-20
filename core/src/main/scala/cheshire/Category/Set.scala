package cheshire.category

import scala.{Either, Function1, Left, Nothing, Right, Unit}

import cheshire.{FunctionB, Iso, ProProduct}

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

      def associate[A, B, C] =
        new Iso[Function1, Either[Either[A, B], C], Either[A, Either[B, C]]] {
        def apply = {
          case Left(Left(a)) => Left(a)
          case Left(Right(b)) => Right(Left(b))
          case Right(c) => Right(Right(c))
        }
        def unapply = {
          case Left(a) => Left(Left(a))
          case Right(Left(b)) => Left(Right(b))
          case Right(Right(c)) => Right(c)
        }
      }
      def leftUnit[A] = new Iso[Function1, Either[Nothing, A], A] {
        def apply = {
          // FIXME: This should be using the cocartesian identity op.
          case Left(_) => scala.Predef.???
          case Right(a) => a
        }
        def unapply = Right(_)
      }
      def rightUnit[A] = new Iso[Function1, Either[A, Nothing], A] {
        def apply = {
          case Left(a) => a
          // FIXME: This should be using the cocartesian identity op.
          case Right(_) => scala.Predef.???
        }
        def unapply = Left(_)
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

      def associate[A, B, C] = new Iso[Function1, ((A, B), C), (A, (B, C))] {
        def apply = x => (x._1._1, (x._1._2, x._2))
        def unapply = x => ((x._1, x._2._1), x._2._2)
      }
      def leftUnit[A] = new Iso[Function1, (Unit, A), A] {
        def apply = _._2
        def unapply = ((), _)
      }
      def rightUnit[A] = new Iso[Function1, (A, Unit), A] {
        def apply = _._1
        def unapply = (_, ())
      }
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
