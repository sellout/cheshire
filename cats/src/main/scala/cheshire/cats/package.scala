package cheshire

import scala.{Function1}

import _root_.cats.{
  Applicative => CApplicative,
  Functor => CFunctor,
  Traverse => CTraverse }

/** This package tries to make it easy to use existing Cats data types and type
  * classes with Cheshire.
  */
package object cats {
  implicit def endofunctor[F[_]](implicit F: CFunctor[F])
      : Endofunctor[Function1, F] =
    new Endofunctor[Function1, F] {
      def map[A, B](fn: A => B) = F.map(_)(fn)
    }

  implicit def traverse[M[_], F[_]]
    (implicit M: CApplicative[M], F: CTraverse[F])
      : Traverse[Function1, M, F] =
    new Traverse[Function1, M, F] {
      def map[A, B](fn: Kleisli[Function1, M, A, B]) =
        Kleisli(F.traverse(_)(fn.runKleisli))
    }
}
