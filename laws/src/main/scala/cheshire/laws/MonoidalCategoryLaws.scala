package cheshire.laws

import cheshire._

import java.lang.String
import scala.{AnyKind, Function1, List, Nil, Unit}

import org.scalacheck._
import org.scalacheck.Prop.{forAll}
import org.typelevel.discipline._

final class CartesianSetLaws[A: Arbitrary]
    extends MonoidalCategoryLaws[Function1, Unit, (A, A), A] {
  def semigroup(implicit A: cheshire.category.set.Semigroup[A]) =
    semigroupSkeleton(List(
      ("associative", forAll { (a: A, b: A, c: A) =>
        A.op((A.op((a, b)), c)) == A.op((a, A.op((b, c))))
      })))
  def commutativeSemigroup
    (implicit A: cheshire.category.set.CommutativeSemigroup[A]) =
    commutativeSemigroupSkeleton(List(
      ("commutative", forAll { (a: A, b: A) =>
        A.op((a, b)) == A.op((b, a))
      })))
  def monoid(implicit A: cheshire.category.set.Monoid[A]) =
    monoidSkeleton(List(
      ("left identity", forAll { (a: A) =>
        A.op((a, A.identity(()))) == a
      }),
      ("right identity", forAll { (a: A) =>
        A.op((A.identity(()), a)) == a
      })))
  def group(implicit A: cheshire.category.set.Group[A]) =
    groupSkeleton(List(
      ("invertible", forAll { (a: A) =>
        val inv = A.inverse(a)
        A.op((A.op((a, inv)), a)) == a && A.op((A.op((inv, a)), inv)) == inv
      })))
  def semiring(implicit A: cheshire.category.set.Semiring[A]) =
    semiringSkeleton(List(
      ("distributive", forAll { (x: A, y: A, z: A) =>
        (A.multiplicative.op((x, A.additive.op((y, z))))
         == A.additive.op((A.multiplicative.op((x, y)),
                           A.multiplicative.op((x, z)))))
        // && (A.multiplicative.op((A.additive.op((y, z)), x))
        //     == A.additive.op((A.multiplicative.op((y, x)),
        //                       A.multiplicative.op((z, x)))))
      })))
  def rig(implicit A: cheshire.category.set.Rig[A]) =
    rigSkeleton(List(
      ("absorbtion", forAll { (x: A) =>
        (A.multiplicative.op((A.additive.identity(()), x))
         == A.additive.identity(()))
        // && (A.multiplicative.op((x, A.additive.identity(())))
        //     == A.additive.identity(()))
      })))
  def ring(implicit A: cheshire.category.set.Ring[A]) =
    ringSkeleton(List())
  def commutativeRing(implicit A: cheshire.category.set.CommutativeRing[A]) =
    commutativeRingSkeleton(List())
  def divisionRing(implicit A: cheshire.category.set.DivisionRing[A]) =
    divisionRingSkeleton(List())
}


trait MonoidalCategoryLaws
  [⟶[_ <: AnyKind, _ <: AnyKind], I <: AnyKind, ⊗ <: AnyKind,  A <: AnyKind] extends Laws {

  def semigroupSkeleton(properties: List[(String, Prop)]) = new RuleSet {
    def name = "semigroup"
    def parents = Nil
    def bases = Nil
    def props = properties
  }
  def semigroup(implicit A: Semigroup[⟶, ⊗, A]): RuleSet

  def commutativeSemigroupSkeleton
    (properties: List[(String, Prop)])
    (implicit A: CommutativeSemigroup[⟶, ⊗, A]) =
    new RuleSet {
      def name = "commutative semigroup"
      def parents = List(semigroup)
      def bases = Nil
      def props = properties
    }
  def commutativeSemigroup(implicit A: CommutativeSemigroup[⟶, ⊗, A]): RuleSet

  def monoidSkeleton
    (properties: List[(String, Prop)])
    (implicit A: Monoid[⟶, I, ⊗, A]) =
    new RuleSet {
      def name = "monoid"
      def parents = List(semigroup)
      def bases = Nil
      def props = properties
    }
  def monoid(implicit A: Monoid[⟶, I, ⊗, A]): RuleSet

  def commutativeMonoid(implicit A: CommutativeMonoid[⟶, I, ⊗, A]) =
    new RuleSet {
      def name = "commutative monoid"
      def parents = List(commutativeSemigroup, monoid)
      def bases = Nil
      def props = Nil
    }

  def groupSkeleton
    (properties: List[(String, Prop)])
    (implicit A: Group[⟶, I, ⊗, A]) =
    new RuleSet {
      def name = "group"
      def parents = List(monoid)
      def bases = Nil
      def props = properties
    }
  def group(implicit A: Group[⟶, I, ⊗, A]): RuleSet

  def commutativeGroup(implicit A: CommutativeGroup[⟶, I, ⊗, A]) =
    new RuleSet {
      def name = "group"
      def parents = List(commutativeMonoid, group)
      def bases = Nil
      def props = Nil
    }

  def semiringSkeleton
    (properties: List[(String, Prop)])
    (implicit A: Semiring[⟶, I, ⊗, A]) =
    new RuleSet {
      def name = "semiring"
      def parents = Nil
      def bases = List(
        ("additive",       commutativeSemigroup(A.additive)),
        ("multiplicative", monoid(A.multiplicative)))
      def props = properties
    }
  def semiring(implicit A: Semiring[⟶, I, ⊗, A]): RuleSet

  def rigSkeleton
    (properties: List[(String, Prop)])
    (implicit A: Rig[⟶, I, ⊗, A]) =
    new RuleSet {
      def name = "rig"
      def parents = List(semiring)
      def bases = List(
        ("additive",       commutativeMonoid(A.additive)),
        ("multiplicative", monoid(A.multiplicative)))
      def props = properties
    }
  def rig(implicit A: Rig[⟶, I, ⊗, A]): RuleSet

  def ringSkeleton
    (properties: List[(String, Prop)])
    (implicit A: Ring[⟶, I, ⊗, A]) =
    new RuleSet {
      def name = "ring"
      def parents = List(rig)
      def bases = List(
        ("additive",       commutativeGroup(A.additive)),
        ("multiplicative", monoid(A.multiplicative)))
      def props = properties
    }
  def ring(implicit A: Ring[⟶, I, ⊗, A]): RuleSet

  def commutativeRingSkeleton
    (properties: List[(String, Prop)])
    (implicit A: CommutativeRing[⟶, I, ⊗, A]) =
    new RuleSet {
      def name = "commutativeRing"
      def parents = List(ring)
      def bases = List(
        ("additive",       commutativeGroup(A.additive)),
        ("multiplicative", commutativeMonoid(A.multiplicative)))
      def props = properties
    }
  def commutativeRing(implicit A: CommutativeRing[⟶, I, ⊗, A]): RuleSet

  def divisionRingSkeleton
    (properties: List[(String, Prop)])
    (implicit A: DivisionRing[⟶, I, ⊗, A]) =
    new RuleSet {
      def name = "divisionRing"
      def parents = List(ring)
      def bases = List(
        ("additive",       commutativeGroup(A.additive)),
        ("multiplicative", group(A.multiplicative)))
      def props = properties
    }
  def divisionRing(implicit A: DivisionRing[⟶, I, ⊗, A]): RuleSet
}
