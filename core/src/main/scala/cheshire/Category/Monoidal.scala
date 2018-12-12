package cheshire.category

import scala.{AnyKind}

/** This can’t be poly-kinded, because we can’t apply `⊗` until we know the
  * monoid, so we need one of these for each kind.
  *
  * @todo Generalize with better kind-polymorphism.
  */
trait MonoidalCategory[⟶[_, _], I <: AnyKind, ⊗[_, _]] {
  type Monoid[M] = cheshire.Monoid[⟶, I, M ⊗ M, M]
  type CommutativeMonoid[M] =
    cheshire.CommutativeMonoid[⟶, I, M ⊗ M, M]

  type Group[G] = cheshire.Group[⟶, I, G ⊗ G, G]
  type CommutativeGroup[G] =
    cheshire.CommutativeGroup[⟶, I, G ⊗ G, G]

  type Semigroup[G] = cheshire.Semigroup[⟶, G ⊗ G, G]
  type CommutativeSemigroup[G] =
    cheshire.CommutativeSemigroup[⟶, G ⊗ G, G]

  type Semiring[G] = cheshire.Semiring[⟶, I, G ⊗ G, G]

  type Rig[G] = cheshire.Rig[⟶, I, G ⊗ G, G]

  type Ring[G] = cheshire.Ring[⟶, I, G ⊗ G, G]
  type CommutativeRing[G] = cheshire.CommutativeRing[⟶, I, G ⊗ G, G]
  type DivisionRing[G] = cheshire.DivisionRing[⟶, I, G ⊗ G, G]

  type Field[G] = cheshire.Field[⟶, I, G ⊗ G, G]
}

trait MonoidalBicategory[⟶[_, _], I <: AnyKind, ⊗[_, _], ⟹[_[_], _[_]]] {

}
