#!/usr/bin/perl -l

use strict;
use warnings FATAL => 'all';

my @primes;

sub next_prime {
    if ( @primes == 0 ) {
        push @primes, 2;
    }
    elsif ( @primes == 1 ) {
        push @primes, 3;
    }
    else {
        my $next = $primes[-1] + 2;
    TRY:
        while (1) {
            foreach my $p (@primes) {
                if ( $next % $p == 0 ) {
                    $next += 2;
                    next TRY;
                }
            }
            push @primes, $next;
            last;
        }
    }
    return $primes[-1];
}

my %is_prime;
while ( ( my $p = next_prime() ) < 1000000 ) {
    $is_prime{$p} = 1;
}

print for @primes;