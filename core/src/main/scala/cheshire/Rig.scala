package cheshire

trait TRig extends TSemiring {
  type Zero = additive.Identity

  def additive: TCommutativeMonoid
}

trait TRigF extends TSemiringF {
  type Zero[_]

  class Additive extends TMonoidF {
    type Product[A[_], B[_], I] = Add[A, B, I]
    type Identity[A] = Zero[A]
  }
}

trait Rig[⟶[_, _], M] extends Semiring[⟶, M] {
  def additive: CommutativeMonoid[⟶, M]

  def zero: cat.Identity ⟶ M = additive.identity
}

trait IdempotentRig[⟶[_, _], M]
    extends Rig[⟶, M] with IdempotentSemiring[⟶, M] {
  def additive: IdempotentCommutativeMonoid[⟶, M]
}
