package cheshire

/** A dagger category has no direction in its arrows, therefore if you can go
  * one direction between two objects, you can always do the reverse.
  */
final case class DaggerCategory[C <: TCategory, A, B]
  (arrow: C#Arrow[A, B], opArrow: C#Arrow[B, A])

// object DaggerCategory {
//   implicit def category[C <: TCategory]
//     (implicit original: Category[C#Arrow])
//       : Category[DaggerCategory[C, ?, ?]] =
//     new Category[DaggerCategory[C, ?, ?]] {
//       def identity[A] =
//         DaggerCategory[C, A, A](original.identity, original.identity)
//       def op =
//         new FunctionB
//           [ComposeB[DaggerCategory[C, ?, ?], DaggerCategory[C, ?, ?], ?, ?],
//            DaggerCategory[C, ?, ?]] {
//           def apply[A, B](
//             fab: ComposeB[DaggerCategory[C, ?, ?],
//                           DaggerCategory[C, ?, ?],
//                           A,
//                           B]) =
//             DaggerCategory[C, A, B](
//               original.op(
//                 new ComposeB[C#Arrow, C#Arrow, A, B] {
//                   type Z = fab.Z
//                   def f = fab.f.arrow
//                   def g = fab.g.arrow
//                 }),
//               original.op(
//                 new ComposeB[C#Arrow, C#Arrow, A, B] {
//                   type Z = fab.Z
//                   def f = fab.g.opArrow
//                   def g = fab.f.opArrow
//                 }))
//         }
//     }
// }
