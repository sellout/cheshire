package cheshire

import scala.{AnyKind}

/** The `⊗` must be constructed with a pair of `M`. E.g., one may pass `(M, M)`
  * for monoids in *Scal*, or `Compose[M, M, ?]` for monads, but kind
  * polymorphism isn’t yet powerful enough for `Monoid` itself to ensure the
  * constraint that `⊗` is a bifunctor on `M`.
  */
trait CommutativeSemigroup
  [⟶[_ <: AnyKind, _ <: AnyKind], ⊗ <: AnyKind, M <: AnyKind]
    extends Semigroup[⟶, ⊗, M]
