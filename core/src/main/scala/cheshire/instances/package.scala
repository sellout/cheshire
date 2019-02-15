package cheshire

import cats.{Id}

import scala.{Boolean, Int, Long, None, Option, Some, Unit}

package object instances {
  // implicit val function1Category: Category[Function1] =
  //   new Category[Function1] {
  //     def identity[A] = Predef.identity[A](_)
  //     def op =
  //       new FunctionB[ComposeB[Function1, Function1, ?, ?], Function1] {
  //         def apply[A, B](fab: ComposeB[Function1, Function1, A, B]) =
  //           fab.f.compose(fab.g)
  //       }
  //   }

  implicit val booleanRig
      : category.set.Rig[Boolean] =
    new category.set.Rig[Boolean] {
      def additive = new category.set.CommutativeMonoid[Boolean] {
        def identity = (_: Unit) => false
        def op = (p: (Boolean, Boolean)) => p._1 || p._2
        def le = (p: (Boolean, Boolean)) => if (p._1) p._2 else true
      }
      def multiplicative = new category.set.Monoid[Boolean] {
        def identity = (_: Unit) => true
        def op = (p: (Boolean, Boolean)) => p._1 && p._2
      }
    }

  implicit val intCommutativeRing
      : category.set.CommutativeRing[Int] =
    new category.set.CommutativeRing[Int] {
      def additive = new category.set.CommutativeGroup[Int] {
        def identity = (_: Unit) => 0
        def op = (p: (Int, Int)) => p._1 + p._2
        def le = (p: (Int, Int)) => p._1 <= p._2
        def inverse = (- _)
        def leftQuotient = (p: (Int, Int)) => op((inverse(p._1), p._2))
        def rightQuotient = (p: (Int, Int)) => op((p._1, inverse(p._2)))
      }
      def multiplicative = new category.set.CommutativeMonoid[Int] {
        def identity = (_: Unit) => 1
        def op = (p: (Int, Int)) => p._1 * p._2
        def le = (p: (Int, Int)) => p._1 <= p._2
      }
    }

  implicit val longCommutativeRing
      : category.set.CommutativeRing[Long] =
    new category.set.CommutativeRing[Long] {
      def additive = new category.set.CommutativeGroup[Long] {
        def identity = (_: Unit) => 0
        def op = (p: (Long, Long)) => p._1 + p._2
        def le = (p: (Long, Long)) => p._1 <= p._2
        def inverse = (- _)
        def leftQuotient = (p: (Long, Long)) => op((inverse(p._1), p._2))
        def rightQuotient = (p: (Long, Long)) => op((p._1, inverse(p._2)))
      }
      def multiplicative = new category.set.CommutativeMonoid[Long] {
        def identity = (_: Unit) => 1
        def op = (p: (Long, Long)) => p._1 * p._2
        def le = (p: (Long, Long)) => p._1 <= p._2
      }
    }

  implicit val optionMonad
      : category.set.Monad[Option] =
    new category.set.Monad[Option] {
      def identity = new FunctionK[Id, Option] {
        def apply[A](fa: Id[A]) = Some(fa)
      }
      def op = new FunctionK[cats.data.Nested[Option, Option, ?], Option] {
        def apply[A](fa: cats.data.Nested[Option, Option, A]) =
          fa.value.fold[Option[A]](None)(_.fold[Option[A]](None)(Some(_)))
      }
    }
}
