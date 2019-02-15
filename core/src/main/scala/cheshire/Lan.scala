package cheshire

import scala.{AnyKind}

/**
  * @todo Generalize with better kind-polymorphism.
  */
final abstract class Lan[⟶[_, _], P[_ <: AnyKind], F[_ <: AnyKind], A] {
  type B <: AnyKind

  def extension: P[B] ⟶ A => F[B]
}
