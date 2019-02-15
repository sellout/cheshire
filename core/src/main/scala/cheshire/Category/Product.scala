package cheshire

import scala.{AnyKind}

final class ProductObject {
  type First <: AnyKind
  type Second <: AnyKind
}

final case class ProductCategory
  [⟶[_ <: AnyKind, _ <: AnyKind],
   ⟹[_ <: AnyKind, _ <: AnyKind],
   A <: ProductObject,
   B <: ProductObject]
  (firstArrow: A#First ⟶ B#First, secondArrow: A#Second ⟹ B#Second)
