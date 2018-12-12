package cheshire

import scala.{AnyKind}

trait Ring
  [⟶[_ <: AnyKind, _ <: AnyKind], I <: AnyKind, ⊗ <: AnyKind, M <: AnyKind]
    extends Rig[⟶, I, ⊗, M] {
  def additive: CommutativeGroup[⟶, I, ⊗, M]
  def multiplicative: Monoid[⟶, I, ⊗, M]

  def negate: M ⟶ M = additive.inverse
  def subtract: ⊗ ⟶ M = additive.quotient
}

trait CommutativeRing
  [⟶[_ <: AnyKind, _ <: AnyKind], I <: AnyKind, ⊗ <: AnyKind, M <: AnyKind]
    extends Ring[⟶, I, ⊗, M] {
  def multiplicative: CommutativeMonoid[⟶, I, ⊗, M]
}

trait DivisionRing
  [⟶[_ <: AnyKind, _ <: AnyKind], I <: AnyKind, ⊗ <: AnyKind, M <: AnyKind]
    extends Ring[⟶, I, ⊗, M] {
  def multiplicative: Group[⟶, I, ⊗, M]

  def reciprocal: M ⟶ M = multiplicative.inverse
}
