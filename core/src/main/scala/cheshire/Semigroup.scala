package cheshire

import scala.{AnyKind}

/** The `⊗` must be constructed with a pair of `M`. E.g., one may pass `(M, M)`
  * for semigroups in *Scal*, or `Compose[M, M, ?]` for monads, but kind
  * polymorphism isn’t yet powerful enough for `Monoid` itself to ensure the
  * constraint that `⊗` is a bifunctor on `M`.
  */
trait Semigroup[⟶[_ <: AnyKind, _ <: AnyKind], ⊗ <: AnyKind, M <: AnyKind] {
  def op: ⊗ ⟶ M
}

/** A semigroup in the category of endofunctors in *Skal*.
  *
  * *NB*: This is different from [[cats.SemigroupK]]. That is simply a
  *      [[Semigroupʹ]] over `F[A]` that is parametric over `A`.
  */
trait SemigroupK
  [⟶[_[_ <: AnyKind], _[_ <: AnyKind]], ⊗[_ <: AnyKind], M[_ <: AnyKind]] {
  def op: ⊗ ⟶ M
}


/** A semigroup in the category of internal Hom functors in *Skal*.
  */
trait SemigroupB
  [⟶[_[_ <: AnyKind, _ <: AnyKind], _[_ <: AnyKind, _ <: AnyKind]],
   ⊗[_ <: AnyKind, _ <: AnyKind],
   M[_ <: AnyKind, _ <: AnyKind]] {
  def op: ⊗ ⟶ M
}
