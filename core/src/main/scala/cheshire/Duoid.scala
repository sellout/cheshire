package cheshire

trait TDuoid {
  type Add[A, B] = additive.Product[A, B]
  type Zero = additive.Identity
  type Multiply[A, B] = multiplicative.Product[A, B]
  type One = multiplicative.Identity

  def additive: TMonoid

  def multiplicative: TMonoid
}

trait TDuoidF extends TIdempotentSemiringF {
  type Zero[_]

  class Additive extends TBoundedSemilatticeF {
    type Product[A[_], B[_], I] = Add[A, B, I]
    type Identity[A] = Zero[A]
  }
}

trait Duoid[⟶[_, _], M] extends IdempotentSemiring[⟶, M] {
  def additive: BoundedSemilattice[⟶, M]
}
