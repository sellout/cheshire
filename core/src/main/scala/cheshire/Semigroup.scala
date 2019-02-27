package cheshire

import cheshire.category._

/** The `⊗` must be constructed with a pair of `M`. E.g., one may pass `(M, M)`
  * for semigroups in *Scal*, or `Compose[M, M, ?]` for monads, but kind
  * polymorphism isn’t yet powerful enough for `Monoid` itself to ensure the
  * constraint that `⊗` is a bifunctor on `M`.
  */
trait Semigroup[⟶[_, _], M] {
  def cat: TMonoidalCategory[⟶]

  def op: cat.Product[M, M] ⟶ M
}

trait CommutativeSemigroup[⟶[_, _], M] extends Semigroup[⟶, M]

/** Also known as a “band”.
  */
trait IdempotentSemigroup[⟶[_, _], M] extends Semigroup[⟶, M]

/** A semigroup in the category of endofunctors in *Skal*.
  *
  * *NB*: This is different from [[cats.SemigroupK]]. That is simply a
  *      [[Semigroupʹ]] over `F[A]` that is parametric over `A`.
  *
  * @todo Remove and use `Semigroup` directly.
  */
trait SemigroupF[C <: TMonoidalCategoryF, M[_]] {
  def op: C#Arrow[C#Product[M, M, ?], M]
}

/** A semigroup in the category of bifunctors.
  *
  * @todo Remove and use `Semigroup` directly.
  */
trait SemigroupB[C <: TMonoidalCategoryB, M[_, _]] {
  def op: C#Arrow[C#Product[M, M, ?, ?], M]
}
