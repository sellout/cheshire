package cheshire

import cheshire.category._

trait TSemiring {
  type Add[_, _]
  type Multiply[_, _]
  type One

  class Additive extends TCommutativeSemigroup {
    type Product[A, B] = Add[A, B]
  }

  class Multiplicative extends TMonoid {
    type Product[A, B] = Multiply[A, B]
    type Identity = One
  }
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

trait Semiring[C <: TMonoidalCategory, M] {
  def additive: CommutativeSemigroup[C, M]
  def multiplicative: Monoid[C, M]

  def add: C#Arrow[C#Product[M, M], M] = additive.op
  def multiply: C#Arrow[C#Product[M, M], M] = multiplicative.op
  def one: C#Arrow[C#Identity, M] = multiplicative.identity
}

trait IdempotentSemiring[C <: TMonoidalCategory, M] extends Semiring[C, M] {
  def additive: Semilattice[C, M]
}
