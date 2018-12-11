package cheshire

import scala.{AnyKind}

trait Group
  [⟶[_ <: AnyKind, _ <: AnyKind], I <: AnyKind, ⊗ <: AnyKind, M <: AnyKind]
    extends Monoid[⟶, I, ⊗, M] {
  def inverse: M ⟶ M
}
