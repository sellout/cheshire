package cheshire

import scala.{Function1}

import _root_.cats.{
  Applicative => CApplicative,
  Functor => CFunctor,
  Traverse => CTraverse }

import _root_.cats.arrow.{Arrow => CArrow}
import _root_.cats.syntax.compose._

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

  implicit def arrow[==>[_, _]](implicit A: CArrow[==>]): Arrow[==>] = new Arrow[==>] {
    override def merge[A, B, X](l: X ==> A, r: X ==> B): X ==> (A, B) = A.merge(l, r)

    override def op = new FunctionB[ComposeB[==>, ==>, ?, ?], ==>] {
      override def apply[A, B](fab: ComposeB[==>, ==>, A, B]): A ==> B =
        fab.f <<< fab.g
    }

    override def map[A, B](fn: A => B): A ==> B = CArrow[==>].lift(fn)
  }
}
