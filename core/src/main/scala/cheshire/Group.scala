package cheshire

import cheshire.category._

trait Group[C <: TMonoidalCategory, M] extends Monoid[C, M] {
  def inverse: C#Arrow[M, M]

  /**
    * @todo Make this a derived method once we have a Bifunctor.
    */
  def leftQuotient: C#Arrow[C#Product[M, M], M]

  /**
    * @todo Make this a derived method once we have a Bifunctor.
    */
  def rightQuotient: C#Arrow[C#Product[M, M], M]
}

trait CommutativeGroup[C <: TMonoidalCategory, M] extends CommutativeMonoid[C, M]
    with Group[C, M] {

  def quotient: C#Arrow[C#Product[M, M], M] = rightQuotient
}
