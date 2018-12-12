package cheshire

import scala.{AnyKind}

trait Semiring
  [⟶[_ <: AnyKind, _ <: AnyKind], I <: AnyKind, ⊗ <: AnyKind, M <: AnyKind] {
  def additive: CommutativeSemigroup[⟶, ⊗, M]
  def multiplicative: Monoid[⟶, I, ⊗, M]

  def add: ⊗ ⟶ M = additive.op
  def multiply: ⊗ ⟶ M = multiplicative.op
  def one: I ⟶ M = multiplicative.identity
}
