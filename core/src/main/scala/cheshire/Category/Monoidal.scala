package cheshire.category

import cats.{Id}
import cats.data.{Nested}

import scala.{Function1, Unit}

import cheshire._

trait TMonoidalCategory extends cheshire.TCategory with cheshire.TMonoid {
  def associate[A, B, C]
      : Arrow[Product[Product[A, B], C], Product[A, Product[B, C]]]

  def leftUnit[A]: Arrow[Product[Identity, A], A]

  def rightUnit[A]: Arrow[Product[A, Identity], A]
}

trait TMonoidalCategoryF extends cheshire.TCategoryF with cheshire.TMonoidF
trait TMonoidalCategoryB extends cheshire.TCategoryB with cheshire.TMonoidB

trait TBraidedMonoidalCategory extends TMonoidalCategory {
  def braid[A, B]: Arrow[Product[A, B], Product[B, A]]
}

trait TSymmetricMonoidalCategory extends TBraidedMonoidalCategory

trait TRigCategory extends cheshire.TCategory with cheshire.TRig { outer =>
  def braid[A, B]: Arrow[Add[A, B], Add[B, A]]

  def leftDistribute[A, B, C]
      : Arrow[Multiply[A, Add[B, C]], Add[Multiply[A, B], Multiply[A, C]]]

  def rightDistribute[A, B, C]
      : Arrow[Multiply[Add[A, B], C], Add[Multiply[A, C], Multiply[B, C]]]

  def leftAnnihilate[A]: Arrow[Multiply[A, Zero], Zero]

  def rightAnnihilate[A]: Arrow[Multiply[Zero, A], Zero]

  abstract class Additive extends TSymmetricMonoidalCategory {
    type Arrow[A, B] = outer.Arrow[A, B]
    type Product[A, B] = Add[A, B]
    type Identity = Zero

    def braid[A,B] = outer.braid[A, B]
  }

  abstract class Multiplicative extends TMonoidalCategory {
    type Arrow[A, B] = outer.Arrow[A, B]
    type Product[A, B] = Multiply[A, B]
    type Identity = One
  }
}

trait TDuoidalCategory extends cheshire.TCategory with cheshire.TRig { outer =>
  abstract class Additive extends TMonoidalCategory {
    type Arrow[A, B] = outer.Arrow[A, B]
    type Product[A, B] = Add[A, B]
    type Identity = Zero
  }

  abstract class Multiplicative extends TMonoidalCategory {
    type Arrow[A, B] = outer.Arrow[A, B]
    type Product[A, B] = Multiply[A, B]
    type Identity = One
  }
}

trait TDuoidalCategoryF extends cheshire.TCategoryF with cheshire.TRigF { outer =>
  def redistribute[A[_], B[_], C[_], D[_]]
      : Arrow[Add[Multiply[A, B, ?], Multiply[C, D, ?], ?],
              Multiply[Add[A, C, ?], Add[B, D, ?], ?]]
  def semisplit: Arrow[Zero, Multiply[Zero, Zero, ?]]
  def semijoin: Arrow[Add[One, One, ?], One]
  /** This one should be able to have a default definition. */
  def reident: Arrow[Zero, One]

  class Additive extends TMonoidalCategoryF {
    type Arrow[A[_], B[_]] = outer.Arrow[A, B]
    type Product[A[_], B[_], I] = Add[A, B, I]
    type Identity[A] = Zero[A]
  }

  class Multiplicative extends TMonoidalCategoryF {
    type Arrow[A[_], B[_]] = outer.Arrow[A, B]
    type Product[A[_], B[_], I] = Multiply[A, B, I]
    type Identity[A] = One[A]
  }
}

final class EndofunctorCategory extends TDuoidalCategoryF {
  type Arrow[A[_], B[_]] = FunctionK[A, B]
  type Add[F[_], G[_], A] = Day[F, G, A]
  type Zero[A] = Function1[Unit, A]
  type Multiply[F[_], G[_], A] = Nested[F, G, A]
  type One[A] = Id[A]

  def redistribute[F[_], G[_], H[_], I[_]] =
    new FunctionK[Day[Nested[F, G, ?], Nested[H, I, ?], ?],
                  Nested[Day[F, H, ?], Day[G, I, ?], ?]] {
      def apply[X](day: Day[Nested[F, G, ?], Nested[H, I, ?], X]) =
        Nested(new Day[F, H, Day[G, I, X]] {
          type A = G[day.A]
          type B = I[day.B]
          def fa = day.fa.value
          def gb = day.gb.value
          def fn = (a, b) => new Day[G, I, X] {
            type A = day.A
            type B = day.B
            def fa = a
            def gb = b
            def fn = day.fn
          }
        })
    }

  def semisplit =
    new FunctionK[Function1[Unit, ?],
                  Nested[Function1[Unit, ?], Function1[Unit, ?], ?]] {
      def apply[A](fa: Function1[Unit, A]) = Nested(_ => fa)
    }

  def semijoin = new FunctionK[Day[Id, Id, ?], Id] {
    def apply[A](fa: Day[Id, Id, A]) = fa.fn(fa.fa, fa.gb)
  }

  def reident = new FunctionK[Function1[Unit, ?], Id] {
    def apply[A](fa: Function1[Unit, A]) = fa(())
  }
}

/** This can’t be poly-kinded, because we can’t apply `⊗` until we know the
  * monoid, so we need one of these for each kind.
  *
  * @todo Generalize with better kind-polymorphism.
  */
trait MonoidalCategory[C <: TMonoidalCategory] {
  type Monoid[M] = cheshire.Monoid[C, M]
  type CommutativeMonoid[M] =
    cheshire.CommutativeMonoid[C, M]

  type Group[G] = cheshire.Group[C, G]
  type CommutativeGroup[G] = cheshire.CommutativeGroup[C, G]

  type Semigroup[G] = cheshire.Semigroup[C, G]
  type CommutativeSemigroup[G] = cheshire.CommutativeSemigroup[C, G]

  type Semiring[G] = cheshire.Semiring[C, G]

  type Rig[G] = cheshire.Rig[C, G]

  type Ring[G] = cheshire.Ring[C, G]
  type CommutativeRing[G] = cheshire.CommutativeRing[C, G]
  type DivisionRing[G] = cheshire.DivisionRing[C, G]

  type Field[G] = cheshire.Field[C, G]
}

// trait MonoidalBicategory[⟶[_, _], I <: AnyKind, ⊗[_, _], ⟹[_[_], _[_]]] {

// }
