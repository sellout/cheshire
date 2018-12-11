package cheshire

import scala.{AnyKind}

/** The `⊗` must be constructed with a pair of `M`. E.g., one may pass `(M, M)`
  * for monoids in *Scal*, or `Compose[M, M, ?]` for monads, but kind
  * polymorphism isn’t yet powerful enough for `Monoid` itself to ensure the
  * constraint that `⊗` is a bifunctor on `M`.
  */
trait Monoid
  [⟶[_ <: AnyKind, _ <: AnyKind], I <: AnyKind, ⊗ <: AnyKind, M <: AnyKind]
    extends Semigroup[⟶, ⊗, M] {

  def identity: I ⟶ M
}

trait MonoidK
  [⟶[_[_ <: AnyKind], _[_ <: AnyKind]],
   I[_ <: AnyKind],
   ⊗[_ <: AnyKind],
   M[_ <: AnyKind]]
    extends SemigroupK[⟶, ⊗, M] {

  def identity: I ⟶ M
}

trait MonoidB[⟶[_[_, _], _[_, _]], I[_, _], ⊗[_, _], M[_, _]]
    extends Monoid[⟶, I, ⊗, M]
