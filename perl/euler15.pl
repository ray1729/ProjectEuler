#!/usr/bin/perl -l

use strict;
use warnings FATAL => 'all';

print factorial(40)/(factorial(20)**2);

sub factorial {
    my $n = shift;
    my $factorial = 1;
    while ( $n > 1 ) {
        $factorial = $factorial * $n--;
    }
    return $factorial;
}