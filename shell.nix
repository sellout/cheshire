{ pkgs ? import <nixpkgs> {} }:

with pkgs;

stdenv.mkDerivation {
  buildInputs = [sbt];

  name = "cheshire";
}
