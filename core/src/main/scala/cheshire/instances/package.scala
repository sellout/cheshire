package cheshire

import scala.{Boolean, Function1, Int, None, Option, Predef, Some, Unit}
import scala.Predef.{booleanWrapper}

package object instances {
  implicit val function1Category: Category[Function1] =
    new Category[Function1] {
      def identity[A] = Predef.identity[A](_)
      def op =
        new FunctionB[ComposeB[Function1, Function1, ?, ?], Function1] {
          def apply[A, B](fab: ComposeB[Function1, Function1, A, B]) =
            fab.f.compose(fab.g)
        }
    }

  implicit val booleanRing
      : category.set.Ring[Boolean] =
    new category.set.Ring[Boolean] {
      def additive = new category.set.CommutativeGroup[Boolean] {
        def identity = (_: Unit) => false
        def op = (p: (Boolean, Boolean)) => (p._1 && !p._2) || (!p._1 && p._2)
        def le = (p: (Boolean, Boolean)) => p._1 <= p._2
        def inverse = (! _)
      }
      def multiplicative = new category.set.Monoid[Boolean] {
        def identity = (_: Unit) => true
        def op = (p: (Boolean, Boolean)) => p._1 && p._2
      }
    }

  implicit val intRing
      : category.set.Ring[Int] =
    new category.set.Ring[Int] {
      def additive = new category.set.CommutativeGroup[Int] {
        def identity = (_: Unit) => 0
        def op = (p: (Int, Int)) => p._1 + p._2
        def le = (p: (Int, Int)) => p._1 <= p._2
        def inverse = (- _)
      }
      def multiplicative = new category.set.Monoid[Int] {
        def identity = (_: Unit) => 1
        def op = (p: (Int, Int)) => p._1 * p._2
      }
    }

  implicit val optionMonad
      : category.set.Monad[Option] =
    new category.set.Monad[Option] {
      def identity = new FunctionK[Identity, Option] {
        def apply[A](fa: Identity[A]) = Some(fa.getIdentity)
      }
      def op = new FunctionK[Compose[Option, Option, ?], Option] {
        def apply[A](fa: Compose[Option, Option, A]) =
          fa.getCompose.fold[Option[A]](None)(_.fold[Option[A]](None)(Some(_)))
      }
    }
}
