package cheshire

import scala.{AnyKind}

/** A dagger category has no direction in its arrows, therefore if you can go
  * one direction between two objects, you can always do the reverse.
  */
final case class DaggerCategory
  [⟶[_ <: AnyKind, _ <: AnyKind], A <: AnyKind, B <: AnyKind]
  (arrow: A ⟶ B, opArrow: A ⟶ B)

object DaggerCategory {
  implicit def category[⟶[_ <: AnyKind, _ <: AnyKind]]
    (implicit original: Category[⟶])
      : Category[DaggerCategory[⟶, ?, ?]] =
    new Category[DaggerCategory[⟶, ?, ?]] {
      def identity[A] =
        DaggerCategory[⟶, A, A](original.identity, original.identity)
      def op =
        new FunctionB
          [ComposeB[DaggerCategory[⟶, ?, ?], DaggerCategory[⟶, ?, ?], ?, ?],
           DaggerCategory[⟶, ?, ?]] {
          def apply[A <: AnyKind, B <: AnyKind](
            fab: ComposeB[DaggerCategory[⟶, ?, ?],
                          DaggerCategory[⟶, ?, ?],
                          A,
                          B]) =
            DaggerCategory[⟶, A, B](
              original.op(
                new ComposeB[⟶, ⟶, A, B] {
                  type Z = fab.Z
                  def f = fab.f.arrow
                  def g = fab.g.arrow
                }),
              original.op(
                new ComposeB[⟶, ⟶, A, B] {
                  type Z = fab.Z
                  def f = fab.g.opArrow
                  def g = fab.f.opArrow
                }))
        }
    }
}
