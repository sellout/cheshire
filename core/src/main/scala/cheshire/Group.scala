package cheshire

trait Group[⟶[_, _], M] extends Monoid[⟶, M] {
  def inverse: M ⟶ M

  /**
    * @todo Make this a derived method once we have a Bifunctor.
    */
  def leftQuotient: cat.Product[M, M] ⟶ M

  /**
    * @todo Make this a derived method once we have a Bifunctor.
    */
  def rightQuotient: cat.Product[M, M] ⟶ M
}

trait CommutativeGroup[⟶[_, _], M]
    extends CommutativeMonoid[⟶, M] with Group[⟶, M] {

  def quotient: cat.Product[M, M] ⟶ M = rightQuotient
}
