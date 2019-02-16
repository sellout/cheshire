package cheshire

import cheshire.category._

trait Lattice[C <: TMonoidalCategory, M] {
  def join: Semilattice[C, M]
  def meet: Semilattice[C, M]
}

trait BoundedLattice[C <: TMonoidalCategory, M] extends Lattice[C, M] {
  def join: BoundedSemilattice[C, M]
  def meet: BoundedSemilattice[C, M]

  def joinSemiring: IdempotentSemiring[C, M] = new IdempotentSemiring[C, M] {
    def additive = join
    def multiplicative = meet
  }

  def meetSemiring: IdempotentSemiring[C, M] = new IdempotentSemiring[C, M] {
    def additive = meet
    def multiplicative = join
  }
}

trait DistributiveLattice[C <: TMonoidalCategory, M] extends Lattice[C, M]
