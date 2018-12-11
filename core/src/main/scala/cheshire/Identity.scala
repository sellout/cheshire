package cheshire

import scala.{AnyKind}

// FIXME: This seems like a false positive.
@java.lang.SuppressWarnings(scala.Array("org.wartremover.warts.ArrayEquals"))
final case class Identity[A <: AnyKind](getIdentity: A)
