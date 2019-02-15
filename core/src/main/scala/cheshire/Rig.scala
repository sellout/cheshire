package cheshire

import cheshire.category._

trait TRig extends TSemiring {
  type Zero

  class Additive extends TMonoid {
    type Product[A, B] = Add[A, B]
    type Identity = Zero
  }
}

trait TRigF extends TSemiringF {
  type Zero[_]

  class Additive extends TMonoidF {
    type Product[A[_], B[_], I] = Add[A, B, I]
    type Identity[A] = Zero[A]
  }
}

trait Rig[C <: TMonoidalCategory, M] extends Semiring[C, M] {
  def additive: CommutativeMonoid[C, M]

  def zero: C#Arrow[C#Identity, M] = additive.identity
}

trait IdempotentRig[C <: TMonoidalCategory, M]
    extends Rig[C, M] with IdempotentSemiring[C, M] {
  def additive: IdempotentCommutativeMonoid[C, M]
}
