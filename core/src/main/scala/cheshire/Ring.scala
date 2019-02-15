package cheshire

import cheshire.category._

trait Ring[C <: TMonoidalCategory, M] extends Rig[C, M] {
  def additive: CommutativeGroup[C, M]
  def multiplicative: Monoid[C, M]

  def negate: C#Arrow[M, M] = additive.inverse
  def subtract: C#Arrow[C#Product[M, M], M] = additive.quotient
}

trait CommutativeRing[C <: TMonoidalCategory, M] extends Ring[C, M] {
  def multiplicative: CommutativeMonoid[C, M]
}

trait DivisionRing[C <: TMonoidalCategory, M] extends Ring[C, M] {
  def multiplicative: Group[C, M]

  def reciprocal: C#Arrow[M, M] = multiplicative.inverse
}
