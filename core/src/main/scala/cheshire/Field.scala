package cheshire

trait Field[⟶[_, _], M]
    extends CommutativeRing[⟶, M] with DivisionRing[⟶, M] {
  def multiplicative: CommutativeGroup[⟶, M]

  def divide: cat.Product[M, M] ⟶ M = multiplicative.quotient
}
