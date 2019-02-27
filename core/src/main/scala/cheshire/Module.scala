package cheshire

trait LeftModule[⟶[_, _], R, M]
    extends Ring[⟶, R] with CommutativeGroup[⟶, M] {
  def leftMultiply: cat.Product[R, M] ⟶ M
}

trait RightModule[⟶[_, _], R, M]
    extends Ring[⟶, R] with CommutativeGroup[⟶, M] {
  def rightMultiply: cat.Product[M, R] ⟶ M
}

trait Module[⟶[_, _], R, M]
    extends LeftModule[⟶, R, M]
    with RightModule[⟶, R, M]
    with CommutativeRing[⟶, R] {
}

trait VectorSpace[⟶[_, _], R, M]
    extends Module[⟶, R, M] with Field[⟶, R] {
}
