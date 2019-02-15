package cheshire

import scala.{AnyKind}

/** A monomorphic functor. We need this in order to be able to define something
  * functor-like for monoidal products, since they are already applied.
  *
  * @todo Remove with richer kind-polymorphism.
  */
trait Monofunctor
  [⟶[_ <: AnyKind, _ <: AnyKind],
   ⟹[_ <: AnyKind, _ <: AnyKind],
   M <: AnyKind,
   A <: AnyKind] {
  def monomap(fn: A ⟶ A): M ⟹ M
}

/** A functor from an arbitrary category to a category where the objects are
  * Types.
  *
  * @todo Generalize with richer kind-polymorphism.
  */
trait Functor[⟶[_ <: AnyKind, _ <: AnyKind], ⟹[_, _], F[_ <: AnyKind]] {
  def map[A <: AnyKind, B <: AnyKind](fn: A ⟶ B): F[A] ⟹ F[B]

  /** Every functor implies a `Monofunctor`. */
  def monofunctor[A <: AnyKind]: Monofunctor[⟶, ⟹, F[A], A] =
    new Monofunctor[⟶, ⟹, F[A], A] {
      def monomap(fn: A ⟶ A) = map(fn)
    }
}
