package cheshire

import scala.{AnyKind}

trait Ring
  [⟶[_ <: AnyKind, _ <: AnyKind], I <: AnyKind, ⊗ <: AnyKind, M <: AnyKind]
    extends Rig[⟶, I, ⊗, M] {
  def additive: CommutativeGroup[⟶, I, ⊗, M]
  def multiplicative: Monoid[⟶, I, ⊗, M]
}
