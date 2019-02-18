package cheshire

import scala.{Function1, Tuple2}
import _root_.cats.syntax.compose._

import _root_.cats.{
  Applicative => CApplicative,
  Functor => CFunctor,
  Traverse => CTraverse
}

import _root_.cats.arrow.{Arrow => CArrow}
import _root_.cats.Id

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

  implicit def arrowFunctor[==>[_, _]: CArrow]: Functor[Function1, ==>, Id] = new Functor[Function1, ==>, Id] {
    override def map[A, B](fn: A => B): A ==> B =
      CArrow[==>].lift(fn)
  }

  implicit def arrowCategory[==>[_, _]](implicit A: CArrow[==>]): CategoryWithProducts[==>, Tuple2] =
    new CategoryWithProducts[==>, Tuple2] {
      override def product[A, B]: Product[==>, A, B, (A, B)] = new Product[==>, A, B, (A, B)] {
        override def left: (A, B) ==> A = A.lift(_._1)
        override def right: (A, B) ==> B = A.lift(_._2)
        override def merge[X](l: X ==> A, r: X ==> B): X ==> (A, B) = A.merge(l, r)
      }

      override def identity[A]: A ==> A = A.id[A]

      override def op = new FunctionB[ComposeB[==>, ==>, ?, ?], ==>] {
        override def apply[A, B](fab: ComposeB[==>, ==>, A, B]): A ==> B =
          fab.f <<< fab.g
      }
    }
}
