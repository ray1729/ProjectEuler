#!/usr/bin/perl -l

use strict;
use warnings FATAL => 'all';

sub value_of {
  my $name = shift;
  my $value = 0;
  foreach my $c ( split '', $name ) {
    $value += ord( $c ) - ord( 'A' ) + 1;
  }
  return $value;
}

my @names = sort <STDIN> =~ /(\w+)/g;

my $sum = 0;
my $pos = 1;

foreach ( @names ) {
  $sum += value_of($_) * $pos++;
}

print $sum;
