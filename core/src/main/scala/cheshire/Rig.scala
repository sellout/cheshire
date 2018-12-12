package cheshire

import scala.{AnyKind}

trait Rig
  [⟶[_ <: AnyKind, _ <: AnyKind], I <: AnyKind, ⊗ <: AnyKind, M <: AnyKind]
    extends Semiring[⟶, I, ⊗, M] {
  def additive: CommutativeMonoid[⟶, I, ⊗, M]

  def zero: I ⟶ M = additive.identity
}
