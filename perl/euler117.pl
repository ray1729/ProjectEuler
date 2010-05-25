#!/usr/bin/env perl

use strict;
use warnings FATAL => 'all';

use List::Util qw(sum min);
use Readonly;

Readonly my @BLOCK_SIZES => (2,3,4);

my @A;

sub A {
    my $len = shift;

    if ( $len < min( @BLOCK_SIZES ) ) {
        return 1;
    }

    if ( $len == min( @BLOCK_SIZES ) ) {
        return 2;
    }

    unless ( defined $A[$len] ) {
        $A[$len] = sum A( $len - 1 ), map {
            A( $len - $_ )
        } grep $_ <= $len, @BLOCK_SIZES;
    }

    return $A[$len];
}

print A(50) . "\n";
