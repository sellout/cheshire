package cheshire

import scala.{AnyKind}

import cheshire.category._

trait FunctionK[F[_ <: AnyKind], G[_ <: AnyKind]] {
  def apply[A <: AnyKind](fa: F[A]): G[A]
}

trait FunctionB[F[_ <: AnyKind, _ <: AnyKind], G[_ <: AnyKind, _ <: AnyKind]] {
  def apply[A <: AnyKind, B <: AnyKind](fab: F[A, B]): G[A, B]
}

final class ProfunctorCategory extends TMonoidalCategoryB {
  type Arrow[F[_, _], G[_, _]] = FunctionB[F, G]
  type Product[F[_, _], G[_, _], A, B] = {
    type Z

    def f: F[Z, B]
    def g: G[A, Z]
  }
  type Unit[A, B] = scala.Function1[A, B]
}

trait Semigroupoid[⟶[_ <: AnyKind, _ <: AnyKind]]
    extends SemigroupB[ProfunctorCategory, ⟶]

// FIXME: This should be a type alias of `Monoid`, but I don’t know what the
//        correct identity object is.
trait Category[⟶[_ <: AnyKind, _ <: AnyKind]] extends Semigroupoid[⟶] {
  def identity[A]: A ⟶ A
}
