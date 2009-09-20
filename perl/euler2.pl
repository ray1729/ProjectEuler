#!/usr/bin/perl -l

use strict;
use warnings FATAL => 'all';

{
    my ( $x, $y );

    sub next_fib {
        unless ( defined $x ) {
            return $x = 0;
        }
        unless ( defined $y ) {
            return $y = 1;
        }
        my $fib = $x + $y;
        $x = $y;
        $y = $fib;
    }

    sub next_fib3 {
        my $fib = next_fib;
        next_fib for 1..2;
        return $fib;
    }
    
    sub reset_fib {
        undef $x; undef $y;
    }   
}

my $FIB_MAX = 4000000;

my $sum = 0;
while ( ( my $fib3 = next_fib3() ) < $FIB_MAX ) {
    print "+ $fib3";
    $sum += $fib3;
}

print $sum;

