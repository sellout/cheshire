package cheshire

import scala.{AnyKind}

trait Group
  [⟶[_ <: AnyKind, _ <: AnyKind], I <: AnyKind, ⊗ <: AnyKind, M <: AnyKind]
    extends Monoid[⟶, I, ⊗, M] {
  def inverse: M ⟶ M

  /**
    * @todo Make this a derived method once we have a Bifunctor.
    */
  def leftQuotient: ⊗ ⟶ M

  /**
    * @todo Make this a derived method once we have a Bifunctor.
    */
  def rightQuotient: ⊗ ⟶ M
}

trait CommutativeGroup
  [⟶[_ <: AnyKind, _ <: AnyKind], I <: AnyKind, ⊗ <: AnyKind, M <: AnyKind]
    extends CommutativeMonoid[⟶, I, ⊗, M]
    with Group[⟶, I, ⊗, M] {

  def quotient: ⊗ ⟶ M = rightQuotient
}
