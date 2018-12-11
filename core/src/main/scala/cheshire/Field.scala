package cheshire

import scala.{AnyKind}

trait Field
  [⟶[_ <: AnyKind, _ <: AnyKind], I <: AnyKind, ⊗ <: AnyKind, M <: AnyKind]
    extends Ring[⟶, I, ⊗, M] {
  def additive: CommutativeGroup[⟶, I, ⊗, M]
  def multiplicative: Group[⟶, I, ⊗, M]
}
