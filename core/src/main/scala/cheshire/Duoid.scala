package cheshire

import cheshire.category._

trait TDuoid extends TIdempotentSemiring {
  type Zero

  class Additive extends TBoundedSemilattice {
    type Product[A, B] = Add[A, B]
    type Identity = Zero
  }
}

trait TDuoidF extends TIdempotentSemiringF {
  type Zero[_]

  class Additive extends TBoundedSemilatticeF {
    type Product[A[_], B[_], I] = Add[A, B, I]
    type Identity[A] = Zero[A]
  }
}

trait Duoid[C <: TMonoidalCategory, M] extends IdempotentSemiring[C, M] {
  def additive: BoundedSemilattice[C, M]
}
