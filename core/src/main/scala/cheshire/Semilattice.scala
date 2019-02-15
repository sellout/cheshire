package cheshire

import scala.{Boolean}

import cheshire.category._

trait TSemilattice extends TCommutativeSemigroup with TIdempotentSemigroup

trait TSemilatticeF extends TCommutativeSemigroupF with TIdempotentSemigroupF

trait TBoundedSemilattice
    extends TSemilattice with TCommutativeMonoid with TIdempotentMonoid

trait TBoundedSemilatticeF
    extends TSemilatticeF with TCommutativeMonoidF with TIdempotentMonoidF

trait Semilattice[C <: TMonoidalCategory, M]
    extends CommutativeSemigroup[C, M] with IdempotentSemigroup[C, M] {

  /** A semilattice induces a partial order.
    */
  def le: C#Arrow[C#Product[M, M], Boolean]
}

trait BoundedSemilattice[C <: TMonoidalCategory, M]
    extends Semilattice[C, M]
    with CommutativeMonoid[C, M]
    with IdempotentMonoid[C, M]
