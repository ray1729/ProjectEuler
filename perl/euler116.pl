#!/usr/bin/env perl

use strict;
use warnings FATAL => 'all';

my @A;

sub A {
    my ( $m, $n ) = @_;

    if ( $n > $m ) {
        return 0;
    }

    if ( $n == $m ) {
        return 1;
    }

    unless ( defined $A[$m][$n] ) {
        $A[$m][$n] = 1 + A( $m - 1, $n ) + A( $m - $n, $n );
    }

    return $A[$m][$n];
}

my $total = 0;

for ( [50,2], [50,3], [50,4] ) {
    my ( $m, $n ) = @$_;
    my $A = A( $m, $n );
    print "A($m, $n) = $A\n";
    $total += $A;
}

print "Total = $total\n";

