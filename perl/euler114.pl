#!/usr/bin/env perl

use strict;
use warnings FATAL => 'all';

use List::Util 'sum';
use Readonly;

Readonly my $MIN_BLOCK_SIZE => 3;

my @A;

sub A {
    my $n = shift;

    if ( $n < $MIN_BLOCK_SIZE ) {
        return 1;
    }

    if ( $n == $MIN_BLOCK_SIZE ) {
        return 2;
    }

    unless ( defined $A[$n] ) {
        $A[$n] = sum A($n - 1), map {
            A($n - $_ - 1 )
        } $MIN_BLOCK_SIZE .. $n;
    }

    return $A[$n];
}

print A(50) . "\n";
