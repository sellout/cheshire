package cheshire

import scala.{AnyKind}

trait Leibniz[⟶[_, _], A <: AnyKind, B <: AnyKind] {
  def subst[F[_ <: AnyKind]]: F[A] ⟶ F[B]
}
