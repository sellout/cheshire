package cheshire

import cheshire.category._

trait Lattice[⟶[_, _], M] {
  def cat: TMonoidalCategory[⟶]

  def join: Semilattice[⟶, M]
  def meet: Semilattice[⟶, M]
}

trait BoundedLattice[⟶[_, _], M] extends Lattice[⟶, M] {
  def join: BoundedSemilattice[⟶, M]
  def meet: BoundedSemilattice[⟶, M]

  def joinSemiring: IdempotentSemiring[⟶, M] = new IdempotentSemiring[⟶, M] {
    def additive = join
    def multiplicative = meet
  }

  def meetSemiring: IdempotentSemiring[⟶, M] = new IdempotentSemiring[⟶, M] {
    def additive = meet
    def multiplicative = join
  }
}

trait DistributiveLattice[⟶[_, _], M] extends Lattice[⟶, M]
