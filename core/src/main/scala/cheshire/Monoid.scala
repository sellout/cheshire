package cheshire

import scala.{Boolean}

import cheshire.category._

/** The `⊗` must be constructed with a pair of `M`. E.g., one may pass `(M, M)`
  * for monoids in *Scal*, or `Compose[M, M, ?]` for monads, but kind
  * polymorphism isn’t yet powerful enough for `Monoid` itself to ensure the
  * constraint that `⊗` is a bifunctor on `M`.
  */
trait Monoid [⟶[_, _], M] extends Semigroup[⟶, M] {
  def identity: cat.Identity ⟶ M
}

trait CommutativeMonoid[⟶[_, _], M]
    extends CommutativeSemigroup[⟶, M]
    with Monoid[⟶, M] {

  /** A commutative monoid induces a preorder. Note that this is weaker than
    * even a partial order – not only may both `x ≤ y` _and_ `y ≤ x` be false,
    * but when both are true, it does _not_ imply that `x == y`.
    */
  def le: cat.Product[M, M] ⟶ Boolean
}

trait IdempotentMonoid[⟶[_, _], M]
    extends IdempotentSemigroup[⟶, M]
    with Monoid[⟶, M] {
}

trait IdempotentCommutativeMonoid[⟶[_, _], M]
    extends Semilattice[⟶, M]
    with IdempotentMonoid[⟶, M]
    with CommutativeMonoid[⟶, M]{
}

/**
  * @todo Remove and use `Monoid` directly.
  */
trait MonoidF[C <: TMonoidalCategoryF, M[_]] extends SemigroupF[C, M] {

  def identity: C#Arrow[C#Identity, M]
}

/**
  * @todo Remove and use `Monoid` directly.
  */
trait MonoidB[C <: TMonoidalCategoryB, M[_, _]] extends SemigroupB[C, M] {

  def identity: C#Arrow[C#Identity, M]
}
