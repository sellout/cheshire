package cheshire.category

import cats.{Id}
import cats.data.{Nested}

import scala.{Function1, Unit}

import cheshire._

trait TMonoidalCategory[⟶[_, _]]
    extends cheshire.Category[⟶] with cheshire.TMonoid {
  def associate[A, B, C]
      : Iso[⟶, Product[Product[A, B], C], Product[A, Product[B, C]]]

  def leftUnit[A]: Iso[⟶, Product[Identity, A], A]

  def rightUnit[A]: Iso[⟶, Product[A, Identity], A]
}

trait TMonoidalCategoryF extends cheshire.TCategoryF with cheshire.TMonoidF
trait TMonoidalCategoryB extends cheshire.TCategoryB with cheshire.TMonoidB

trait TBraidedMonoidalCategory[⟶[_, _]]
    extends TMonoidalCategory[⟶] with cheshire.TCommutativeMonoid {
  def braid[A, B]: Product[A, B] ⟶ Product[B, A]
}

trait TSymmetricMonoidalCategory[⟶[_, _]] extends TBraidedMonoidalCategory[⟶]

trait TRigCategory[⟶[_, _]] extends cheshire.Category[⟶] with cheshire.TRig {
  def additive: TSymmetricMonoidalCategory[⟶]

  def multiplicative: TMonoidalCategory[⟶]

  def leftDistribute[A, B, C]
      : Iso[⟶, Multiply[A, Add[B, C]], Add[Multiply[A, B], Multiply[A, C]]]

  def rightDistribute[A, B, C]
      : Iso[⟶, Multiply[Add[A, B], C], Add[Multiply[A, C], Multiply[B, C]]]

  def leftAnnihilate[A]: Iso[⟶, Multiply[A, Zero], Zero]

  def rightAnnihilate[A]: Iso[⟶, Multiply[Zero, A], Zero]
}

trait TDuoidalCategory[⟶[_, _]] extends cheshire.Category[⟶] with cheshire.TDuoid {
  def additive: TMonoidalCategory[⟶]
  def multiplicative: TMonoidalCategory[⟶]

  def redistribute[A, B, C, D]
      : Add[Multiply[A, B], Multiply[C, D]] ⟶ Multiply[Add[A, C], Add[B, D]]
  def semisplit: Zero ⟶ Multiply[Zero, Zero]
  def semijoin: Add[One, One] ⟶ One
  /** TODO: This one should be able to have a default definition. */
  def reident: Zero ⟶ One
}

/** TODO: The laws for duoids have terrible names here. Hopefully someone has
          given them better names somewhere?
  */
trait TDuoidalCategoryF extends cheshire.TCategoryF with cheshire.TRigF { outer =>
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

  def redistribute[A[_], B[_], C[_], D[_]]
      : Arrow[Add[Multiply[A, B, ?], Multiply[C, D, ?], ?],
              Multiply[Add[A, C, ?], Add[B, D, ?], ?]]
  def semisplit: Arrow[Zero, Multiply[Zero, Zero, ?]]
  def semijoin: Arrow[Add[One, One, ?], One]
  /** TODO: This one should be able to have a default definition. */
  def reident: Arrow[Zero, One]
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
trait MonoidalCategory[⟶[_, _]] { outer =>
  def cat: TMonoidalCategory[⟶]

  trait ≅[A, B] extends cheshire.Iso[⟶, A, B]
  trait ≣[A, B] extends cheshire.Leibniz[⟶, A, B]

  trait Monoid[M] extends cheshire.Monoid[⟶, M] {
    def cat = outer.cat
  }
  trait CommutativeMonoid[M] extends cheshire.CommutativeMonoid[⟶, M] {
    def cat = outer.cat
  }

  trait Group[G] extends cheshire.Group[⟶, G] {
    def cat = outer.cat
  }
  trait CommutativeGroup[G] extends cheshire.CommutativeGroup[⟶, G] {
    def cat = outer.cat
  }

  trait Semigroup[G] extends cheshire.Semigroup[⟶, G] {
    def cat = outer.cat
  }
  trait CommutativeSemigroup[G] extends cheshire.CommutativeSemigroup[⟶, G] {
    def cat = outer.cat
  }

  trait Semiring[G] extends cheshire.Semiring[⟶, G] {
    def cat = outer.cat
  }

  trait Rig[G] extends cheshire.Rig[⟶, G] {
    def cat = outer.cat
  }

  trait Ring[G] extends cheshire.Ring[⟶, G] {
    def cat = outer.cat
  }
  trait CommutativeRing[G] extends cheshire.CommutativeRing[⟶, G] {
    def cat = outer.cat
  }
  trait DivisionRing[G] extends cheshire.DivisionRing[⟶, G] {
    def cat = outer.cat
  }

  trait Field[G] extends cheshire.Field[⟶, G] {
    def cat = outer.cat
  }
}

// trait MonoidalBicategory[⟶[_, _], I <: AnyKind, ⊗[_, _], ⟹[_[_], _[_]]] {

// }
