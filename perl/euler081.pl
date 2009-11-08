#!/usr/bin/perl

use strict;
use warnings FATAL => 'all';

use List::Util 'min';

my @matrix;

while ( <> ) {
  chomp;
  push @matrix, [split q{,}, $_];
}

sub minimize {
  my ( $row, $col ) = @_;
  my $val   = $matrix[$row][$col];
  my $right = $matrix[$row][$col+1];
  my $down  = $matrix[$row+1][$col];
  my $min_down_right = min( grep defined, $down, $right ) || 0;
  $matrix[$row][$col] = $val + $min_down_right;
}

my $max_row_col = $#matrix;
for ( my $row = $max_row_col; $row >= 0; $row-- ) {
  for ( my $col = $max_row_col; $col >= 0; $col-- ) {
    minimize( $row, $col );
  }
}

print $matrix[0][0] . "\n";
