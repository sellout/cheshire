package cheshire

trait Ring[⟶[_, _], M] extends Rig[⟶, M] {
  def additive: CommutativeGroup[⟶, M]
  def multiplicative: Monoid[⟶, M]

  def negate: M ⟶ M = additive.inverse
  def subtract: cat.Product[M, M] ⟶ M = additive.quotient
}

trait CommutativeRing[⟶[_, _], M] extends Ring[⟶, M] {
  def multiplicative: CommutativeMonoid[⟶, M]
}

trait DivisionRing[⟶[_, _], M] extends Ring[⟶, M] {
  def multiplicative: Group[⟶, M]

  def reciprocal: M ⟶ M = multiplicative.inverse
}
