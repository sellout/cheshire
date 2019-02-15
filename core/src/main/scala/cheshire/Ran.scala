package cheshire

import scala.{AnyKind}

/**
  * @todo Generalize with better kind-polymorphism.
  */
final abstract class Ran[⟶[_, _], P[_ <: AnyKind], F[_ <: AnyKind], A] {
  def extension[B]: A ⟶ P[B] => F[B]
}
