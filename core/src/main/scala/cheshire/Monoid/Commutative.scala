package cheshire

import scala.{Boolean}

/** The `⊗` must be constructed with a pair of `M`. E.g., one may pass `(M, M)`
  * for monoids in *Scal*, or `Compose[M, M, ?]` for monads, but kind
  * polymorphism isn’t yet powerful enough for `Monoid` itself to ensure the
  * constraint that `⊗` is a bifunctor on `M`.
  */
trait CommutativeMonoid [⟶[_, _], I, ⊗, M]
    extends CommutativeSemigroup[⟶, ⊗, M]
    with Monoid[⟶, I, ⊗, M] {
  def le: ⊗ ⟶ Boolean
}
