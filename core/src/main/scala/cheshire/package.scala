import scala.{Option}

package object cheshire {
  type Applicative[⟶[_[_], _[_]], F[_]] = MonoidK[⟶, Identity, Day[F, F, ?], F]

  type Endofunctor[⟶[_, _], F[_]] = Functor[⟶, ⟶, F]

  type Monad[⟶[_[_], _[_]], M[_]] = MonoidK[⟶, Identity, Compose[M, M, ?], M]

  type Traverse[⟶[_, _], M[_], F[_]] = Endofunctor[Kleisli[⟶, M, ?, ?], F]

  type Compactable[⟶[_, _], F[_]] = Functor[Kleisli[⟶, Option, ?, ?], ⟶, F]

  type Contravariant[⟶[_, _], F[_]] = Functor[Op[⟶, ?, ?], ⟶, F]
}
