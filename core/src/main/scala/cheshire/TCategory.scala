package cheshire

import scala.{AnyKind}

/** A *Set*-category (i.e., a category where the Hom-object (i.e., the target
  * category of `Arrow[A,B]`) is a Set (i.e., a type)) at the type level.
  */
trait TCategory {
  type Arrow[_ <: AnyKind, _ <: AnyKind]
}

trait TBicategory extends TCategory {
  type Arrow2[_[_ <: AnyKind, _ <: AnyKind], _[_ <: AnyKind, _ <: AnyKind]]

  class Upper extends TCategoryB {
    type Arrow[A[_, _], B[_, _]] = Arrow2[A, B]
  }
}

trait TCategoryF {
  type Arrow[_[_ <: AnyKind], _[_ <: AnyKind]]
}

trait TCategoryB {
  type Arrow[_[_ <: AnyKind, _ <: AnyKind], _[_ <: AnyKind, _ <: AnyKind]]
}

