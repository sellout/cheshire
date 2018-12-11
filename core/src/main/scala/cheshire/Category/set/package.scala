package cheshire.category

import scala.{Either, Function1, Nothing, Unit}

/** Aliases that specialize the type to *Scal* (a.k.a., *Set*).
  */
package object set extends MonoidalCategory[Function1, Unit, (?, ?)] {
  type Applicative[F[_]] = cheshire.Applicative[cheshire.FunctionK, F]

  type Monad[M[_]] = cheshire.Monad[cheshire.FunctionK, M]
}

package object cocartesianSet
    extends MonoidalCategory[Function1, Nothing, Either] {
  type Applicative[F[_]] = cheshire.Applicative[cheshire.FunctionK, F]

  type Monad[M[_]] = cheshire.Monad[cheshire.FunctionK, M]
}
