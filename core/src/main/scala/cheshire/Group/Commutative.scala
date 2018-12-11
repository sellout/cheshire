package cheshire

import scala.{AnyKind}

trait CommutativeGroup
  [⟶[_ <: AnyKind, _ <: AnyKind], I <: AnyKind, ⊗ <: AnyKind, M <: AnyKind]
    extends CommutativeMonoid[⟶, I, ⊗, M]
    with Group[⟶, I, ⊗, M] {
  def inverse: M ⟶ M
}
