package cheshire.category

/** Aliases that specialize the type to *Scal* (a.k.a., *Set*).
  */
// TODO: Should extend a RigCategory instead.
package object set extends MonoidalCategory[Set#Multiplicative] {
  type Applicative[F[_]] = cheshire.Applicative[EndofunctorCategory, F]

  type Monad[M[_]] = cheshire.Monad[EndofunctorCategory, M]
}
