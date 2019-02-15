package cheshire

import cheshire.category._

trait Field[C <: TMonoidalCategory, M]
    extends CommutativeRing[C, M] with DivisionRing[C, M] {
  def multiplicative: CommutativeGroup[C, M]

  def divide: C#Arrow[C#Product[M, M], M] = multiplicative.quotient
}
