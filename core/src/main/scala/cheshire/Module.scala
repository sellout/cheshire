package cheshire

import cheshire.category._

trait LeftModule[C <: TMonoidalCategory, R, M]
    extends Ring[C, R] with CommutativeGroup[C, M] {
  def leftMultiply: C#Arrow[C#Product[R, M], M]
}

trait RightModule[C <: TMonoidalCategory, R, M]
    extends Ring[C, R] with CommutativeGroup[C, M] {
  def rightMultiply: C#Arrow[C#Product[M, R], M]
}

trait Module[C <: TMonoidalCategory, R, M]
    extends LeftModule[C, R, M]
    with RightModule[C, R, M]
    with CommutativeRing[C, R] {
}

trait VectorSpace[C <: TMonoidalCategory, R, M]
    extends Module[C, R, M] with Field[C, R] {
}
