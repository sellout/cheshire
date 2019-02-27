package cheshire

abstract class Iso[⟶[_, _], A, B] {
  def apply: A ⟶ B
  def unapply: B ⟶ A
}
