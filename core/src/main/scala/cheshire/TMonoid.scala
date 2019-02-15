package cheshire

/** Type-level monoid. */
trait TMonoid extends TSemigroup {
  type Identity
}

trait TMonoidF extends TSemigroupF {
  type Identity[_]
}

trait TMonoidB extends TSemigroupB {
  type Identity[_, _]
}

trait TCommutativeMonoid extends TCommutativeSemigroup with TMonoid

trait TCommutativeMonoidF extends TCommutativeSemigroupF with TMonoidF

trait TCommutativeMonoidB extends TCommutativeSemigroupB with TMonoidB

trait TIdempotentMonoid extends TIdempotentSemigroup with TMonoid

trait TIdempotentMonoidF extends TIdempotentSemigroupF with TMonoidF

trait TIdempotentMonoidB extends TIdempotentSemigroupB with TMonoidB

// final class Categorical extends TMonoidB {
//   type Identity[A, B] = cats.evidence.Is[A, B]
//   type Product[F[_, _], G[_, _], A, B] = Profunctory[F, G, A, B]
// }
