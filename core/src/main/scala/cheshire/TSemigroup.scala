package cheshire

trait TSemigroup {
  type Product[_, _]
}

trait TSemigroupF {
  type Product[_[_], _[_], _]
}

trait TSemigroupB {
  type Product[_[_, _], _[_, _], _, _]
}

trait TCommutativeSemigroup extends TSemigroup

trait TCommutativeSemigroupF extends TSemigroupF

trait TCommutativeSemigroupB extends TSemigroupB

trait TIdempotentSemigroup extends TSemigroup

trait TIdempotentSemigroupF extends TSemigroupF

trait TIdempotentSemigroupB extends TSemigroupB
