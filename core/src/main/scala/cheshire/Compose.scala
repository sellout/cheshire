package cheshire

import scala.{AnyKind}

/** A composition of functors. Unfortunately, the limitations of kind-
  * polymorphism mean that `F` must be an endofunctor in *Skal*, while `G` can
  * be a functor (to *Skal*) from another category.
  */
final case class Compose[F[_], G[_ <: AnyKind], A <: AnyKind]
  (getCompose: F[G[A]])

/** Composition of bifunctors.
  */
abstract class ComposeB
  [F[_ <: AnyKind, _ <: AnyKind],
   G[_ <: AnyKind, _ <: AnyKind],
   A <: AnyKind,
   B <: AnyKind]
  {
    type Z <: AnyKind

    def f: F[Z, B]
    def g: G[A, Z]
  }
