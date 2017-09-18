#!/bin/bash -e
die() {
  echo "$*"
  exit 1
}
[ -z "$1" ] && die "Usage: $0 task-name"
tname="$1"
bdir="tasks/$tname"
[ -e "$bdir" ] && die "Dir $bdir already exists"
jdir="$bdir/java"
rdir="$bdir/rust"
mkdir -p "$jdir" "$rdir"
cargo new "$rdir/$tname" --bin
mvn -B archetype:generate \
    -DarchetypeGroupId=org.apache.maven.archetypes \
    -DgroupId=se.cygni.rust \
    -DartifactId=$tname
    -Dbasedir="$jdir/npe"

