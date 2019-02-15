package cheshire.category

import cats.{Id}

import scala.{Unit}

import cheshire._

trait TMonoidalCategory extends cheshire.TCategory with cheshire.TMonoid
trait TMonoidalCategoryF extends cheshire.TCategoryF with cheshire.TMonoidF
trait TMonoidalCategoryB extends cheshire.TCategoryB with cheshire.TMonoidB

trait TRigCategory extends cheshire.TCategory with cheshire.TRig { outer =>
  class Additive extends TMonoidalCategory {
    type Arrow[A, B] = outer.Arrow[A, B]
    type Product[A, B] = Add[A, B]
    type Identity = Zero
  }

  class Multiplicative extends TMonoidalCategory {
    type Arrow[A, B] = outer.Arrow[A, B]
    type Product[A, B] = Multiply[A, B]
    type Identity = One
  }
}

trait TDuoidalCategory extends cheshire.TCategory with cheshire.TRig { outer =>
  class Additive extends TMonoidalCategory {
    type Arrow[A, B] = outer.Arrow[A, B]
    type Product[A, B] = Add[A, B]
    type Identity = Zero
  }

  class Multiplicative extends TMonoidalCategory {
    type Arrow[A, B] = outer.Arrow[A, B]
    type Product[A, B] = Multiply[A, B]
    type Identity = One
  }
}

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
}

final class EndofunctorCategory extends TDuoidalCategoryF {
  type Arrow[A[_], B[_]] = FunctionK[A, B]
  type Add[F[_], G[_], A] = Day[F, G, A]
  type Zero[A] = scala.Function1[Unit, A]
  type Multiply[F[_], G[_], A] = cats.data.Nested[F, G, A]
  type One[A] = Id[A]
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
