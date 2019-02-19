package cheshire

import scala.{AnyKind}

trait Product[⟶[_ <: AnyKind, _ <: AnyKind], A <: AnyKind, B <: AnyKind, `A × B` <: AnyKind] {
  def left: `A × B` ⟶ A
  def right: `A × B` ⟶ B

  def fanout[X](l: X ⟶ A, r: X ⟶ B): X ⟶ `A × B`
}

trait CategoryWithProducts[⟶[_ <: AnyKind, _ <: AnyKind], ×[_ <: AnyKind, _ <: AnyKind]] extends Category[⟶] {
  def product[A, B]: Product[⟶, A, B, A × B]
}
