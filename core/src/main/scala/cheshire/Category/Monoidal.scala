package cheshire

import scala.{AnyKind}

/**
  */
final class MonoidalCategory {
  /** The morphisms of this category. */
  type ⟶[_ <: AnyKind, _ <: AnyKind]

  /** The identity object in this category. */
  type Id <: AnyKind

  /** This field needs to be defined with two instances of the object for this
    * category.
    */
  type ⊗ <: AnyKind
}
