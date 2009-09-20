#!/usr/bin/perl

use strict;
use warnings FATAL => 'all';

my $biggest_palindrome = 0;

for my $m ( 100..999 ) {
    for my $n ( $m..999 ) {
        my $prod = $m * $n;
        if ( is_palindrome( $prod ) and $prod > $biggest_palindrome ) {
            $biggest_palindrome = $prod;
        }
    }
}

print "$biggest_palindrome\n";

sub is_palindrome {
    my $str = shift;
    my $rev = join '', reverse split '', $str;
    $str eq $rev;
}
