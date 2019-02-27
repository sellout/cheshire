package cheshire

import scala.{Boolean}

trait TSemilattice extends TCommutativeSemigroup with TIdempotentSemigroup

trait TSemilatticeF extends TCommutativeSemigroupF with TIdempotentSemigroupF

trait TBoundedSemilattice
    extends TSemilattice with TCommutativeMonoid with TIdempotentMonoid

trait TBoundedSemilatticeF
    extends TSemilatticeF with TCommutativeMonoidF with TIdempotentMonoidF

trait Semilattice[⟶[_, _], M]
    extends CommutativeSemigroup[⟶, M] with IdempotentSemigroup[⟶, M] {

  /** A semilattice induces a partial order.
    */
  def le: cat.Product[M, M] ⟶ Boolean
}

trait BoundedSemilattice[⟶[_, _], M]
    extends Semilattice[⟶, M]
    with CommutativeMonoid[⟶, M]
    with IdempotentMonoid[⟶, M]
