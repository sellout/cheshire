package cheshire

import scala.{AnyKind}

final case class Kleisli[⟶[_, _], M[_ <: AnyKind], A, B <: AnyKind]
  (runKleisli: A ⟶ M[B])
