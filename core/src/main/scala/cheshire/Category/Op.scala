package cheshire

import scala.{AnyKind}

final case class Op
  [⟶[_ <: AnyKind, _ <: AnyKind], A <: AnyKind, B <: AnyKind]
  (getOp: B ⟶ A)
