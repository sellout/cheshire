package cheshire

import cheshire.category._

trait TSemiring {
  type Add[A, B] = additive.Product[A, B]
  type Multiply[A, B] = multiplicative.Product[A, B]
  type One = multiplicative.Identity

  def additive: TCommutativeSemigroup

  def multiplicative: TMonoid
}

trait TSemiringF {
  type Add[_[_], _[_], _]
  type Multiply[_[_], _[_], _]
  type One[_]

  class Additive extends TCommutativeSemigroupF {
    type Product[A[_], B[_], I] = Add[A, B, I]
  }

  class Multiplicative extends TMonoidF {
    type Product[A[_], B[_], I] = Multiply[A, B, I]
    type Identity[A] = One[A]
  }
}

trait TIdempotentSemiring extends TSemiring {
  class Additive extends TSemilattice {
    type Product[A, B] = Multiply[A, B]
  }
}

trait TIdempotentSemiringF extends TSemiringF {
  class Additive extends TSemilatticeF {
    type Product[A[_], B[_], I] = Multiply[A, B, I]
  }
}

trait Semiring[⟶[_, _], M] {
  def cat: TMonoidalCategory[⟶]

  def additive: CommutativeSemigroup[⟶, M]
  def multiplicative: Monoid[⟶, M]

  def add: cat.Product[M, M] ⟶ M = additive.op
  def multiply: cat.Product[M, M] ⟶ M = multiplicative.op
  def one: cat.Identity ⟶ M = multiplicative.identity
}

trait IdempotentSemiring[⟶[_, _], M] extends Semiring[⟶, M] {
  def additive: Semilattice[⟶, M]
}
