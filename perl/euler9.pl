#!/usr/bin/perl

use strict;
use warnings FATAL => 'all';

for my $a ( 1..997 ) {
    my $b = (1000000 - 2000*$a)/( 2000 - 2*$a);
    if ( $b == int( $b ) and $b >= $a ) {
        my $c = 1000 - $a - $b;
        print join(q{,}, $a, $b, $c, $a*$a + $b*$b, $c*$c, $a*$b*$c) . "\n";
    }
}
