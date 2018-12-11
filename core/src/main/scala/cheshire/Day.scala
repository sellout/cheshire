package cheshire

final abstract class Day[F[_], G[_], C] {
  type A
  type B

  def fa: F[A]
  def gb: G[B]
  def fn: (A, B) => C
}
