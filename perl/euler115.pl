#!/usr/bin/env perl

use strict;
use warnings FATAL => 'all';

use List::Util 'sum';
use Readonly;

Readonly my $MIN_BLOCK_SIZE => 50;

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

my $n = 0;

while ( A($n) < 1000000 ) {
    $n++;
}

print "$n\n";





# sub A {
#     my ($n, $MIN_BLOCK_SIZE) = @_;

#     if ( $n < $MIN_BLOCK_SIZE ) {
#         return 1;
#     }

#     if ( $n == $MIN_BLOCK_SIZE ) {
#         return 2;
#     }

#     unless ( defined $A[$n][$MIN_BLOCK_SIZE] ) {
#         $A[$n][$MIN_BLOCK_SIZE] = sum A($n - 1, $MIN_BLOCK_SIZE), map {
#             A($n - $_ - 1, $MIN_BLOCK_SIZE)
#         } $MIN_BLOCK_SIZE .. $n;
#     }

#     return $A[$n][$MIN_BLOCK_SIZE];
# }

# print A(@ARGV) . "\n";


