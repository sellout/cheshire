package cheshire

import scala.{Function1, Tuple2}

trait Arrow[⟶[_, _]] extends Functor[Function1, ⟶, λ[`X` => X]] with CategoryWithProducts[⟶, Tuple2] {
  def merge[A, B, X](l: X ⟶ A, r: X ⟶ B): X ⟶ (A, B)

  override def identity[A]: A ⟶ A = map(x => x)

  override def product[A, B]: Product[⟶, A, B, (A, B)] = new Product[⟶, A, B, (A, B)] {
    override def left: (A, B) ⟶ A = map(_._1)
    override def right: (A, B) ⟶ B = map(_._2)
    override def fanout[X](l: X ⟶ A, r: X ⟶ B): X ⟶ (A, B) = merge(l, r)
  }
}
