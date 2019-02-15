import scala.{AnyKind, Option}

import cheshire.category._

package object cheshire {
  type Applicative[C <: TDuoidalCategoryF, F[_]] = MonoidF[C#Additive, F]

  type Endofunctor[⟶[_, _], F[_]] = Functor[⟶, ⟶, F]

  type Monad[C <: TDuoidalCategoryF, M[_]] = MonoidF[C#Multiplicative, M]

  type Traverse[⟶[_, _], M[_], F[_]] = Endofunctor[Kleisli[⟶, M, ?, ?], F]

  // type Bifunctor[⟶[_ <: AnyKind, _ <: AnyKind], ⟹[_ <: AnyKind, _ <: AnyKind], ⟼[_, _], F[_]] =
  //   Functor[ProductCategory[⟶, ⟹, ?, ?], ⟼, F]

  type Compactable[⟶[_, _], F[_]] = Functor[Kleisli[⟶, Option, ?, ?], ⟶, F]

  type Contravariant[⟶[_, _], F[_]] = Functor[Op[⟶, ?, ?], ⟶, F]

  type Density[⟶[_, _], F[_ <: AnyKind], A] = Lan[⟶, F, F, A]
  type Codensity[⟶[_, _], F[_ <: AnyKind], A] = Ran[⟶, F, F, A]

  type Yoneda[⟶[_, _], F[_], A] = Ran[⟶, Identity, F, A]
  type Coyoneda[⟶[_, _], F[_], A] = Lan[⟶, Identity, F, A]

  /** *NB*: This being an alias means that what is traditionally called `ana` is
    *       instead called `cata`.
    */
  type Corecursive[⟶[_, _], T, F[_]] = Recursive[Op[⟶, ?, ?], T, F]
}
