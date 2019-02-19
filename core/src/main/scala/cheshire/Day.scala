package cheshire

/**
  * @todo This should be defined as a specialization of `Lan` with better kind-
  *       polymorphism.
  */
abstract class Day[F[_], G[_], C] {
  type A
  type B

  def fa: F[A]
  def gb: G[B]
  def fn: (A, B) => C
}
