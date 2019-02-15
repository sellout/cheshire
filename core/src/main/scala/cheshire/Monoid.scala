package cheshire

import scala.{Boolean}

import cheshire.category._

/** The `⊗` must be constructed with a pair of `M`. E.g., one may pass `(M, M)`
  * for monoids in *Scal*, or `Compose[M, M, ?]` for monads, but kind
  * polymorphism isn’t yet powerful enough for `Monoid` itself to ensure the
  * constraint that `⊗` is a bifunctor on `M`.
  */
trait Monoid [C <: TMonoidalCategory, M] extends Semigroup[C, M] {

  def identity: C#Arrow[C#Identity, M]
}

trait CommutativeMonoid[C <: TMonoidalCategory, M]
    extends CommutativeSemigroup[C, M]
    with Monoid[C, M] {

  /** A commutative monoid induces a preorder. Note that this is weaker than
    * even a partial order – not only may both `x ≤ y` _and_ `y ≤ x` be false,
    * but when both are true, it does _not_ imply that `x == y`.
    */
  def le: C#Arrow[C#Product[M, M], Boolean]
}

trait IdempotentMonoid[C <: TMonoidalCategory, M]
    extends IdempotentSemigroup[C, M]
    with Monoid[C, M] {
}

trait IdempotentCommutativeMonoid[C <: TMonoidalCategory, M]
    extends Semilattice[C, M]
    with IdempotentMonoid[C, M]
    with CommutativeMonoid[C, M]{
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
