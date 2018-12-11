package cheshire

import scala.{AnyKind}

trait Functor[⟶[_ <: AnyKind, _ <: AnyKind], ⟹[_, _], F[_ <: AnyKind]] {
  def map[A <: AnyKind, B <: AnyKind](fn: A ⟶ B): F[A] ⟹ F[B]
}
