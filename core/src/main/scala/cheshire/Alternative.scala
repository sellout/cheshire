package cheshire

import scala.{Unit}

//   upside: there‘s no alternative operations to use outside of the monoid
// downside: • weird nested implementation
//           • have to project the monoid
trait Alternative[⟶[_, _], F[_]] {
  def monoid[A]: Monoid[⟶, Unit, (F[A], F[A]), F[A]]
}


// //   upside: clear implementation
// // downside: can be used independently of its monoid
// trait Alternative[⟶[_, _], F[_]] {
//   def empty[A]: Unit => F[A]
//   def or[A]: (F[A], F[A]) => F[A]
// }

// implicit def alternativeMonoid[⟶[_, _], F[_], A](implicit F: Alternative[⟶, F]) =
//   new Monoid[⟶, Unit, (F[A], F[A]), F[A]] {
//     def identity = F.empty[A]
//     def op = F.or
//   }

// trait Alternativeʹ[⟶[_, _], F[_]] extends λ[A => Monoid[⟶, Unit, (F[A], F[A]), F[A]]]
