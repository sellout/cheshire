package cheshire

import scala.{AnyKind}

trait Field
  [⟶[_ <: AnyKind, _ <: AnyKind], I <: AnyKind, ⊗ <: AnyKind, M <: AnyKind]
    extends CommutativeRing[⟶, I, ⊗, M] with DivisionRing[⟶, I, ⊗, M] {
  def multiplicative: CommutativeGroup[⟶, I, ⊗, M]

  def divide: ⊗ ⟶ M = multiplicative.quotient
}
