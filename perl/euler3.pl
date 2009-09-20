#!/usr/bin/perl -l

use strict;
use warnings FATAL => 'all';

{
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
                foreach my $p ( @primes ) {
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
}

my $to_factor = shift @ARGV;
my @factors;
my $trial_divisor = next_prime();
while ( $to_factor > 1 ) {
    if ( $to_factor % $trial_divisor == 0 ) {
        push @factors, $trial_divisor;
        $to_factor = $to_factor / $trial_divisor;
    }
    else {
        $trial_divisor = next_prime();
    }
}

print join(' x ', @factors);


