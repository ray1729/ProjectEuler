#!/usr/bin/perl

use strict;
use warnings FATAL => 'all';

my %word_for = (  1 => 'one',
		  2 => 'two',
		  3 => 'three',
		  4 => 'four',
		  5 => 'five',
		  6 => 'six',
		  7 => 'seven',
		  8 => 'eight',
		  9 => 'nine',
		 10 => 'ten',
		 11 => 'eleven',
		 12 => 'twelve',
		 13 => 'thirteen',
		 14 => 'fourteen',
		 15 => 'fifteen',
		 16 => 'sixteen',
		 17 => 'seventeen',
		 18 => 'eighteen',
		 19 => 'nineteen',
		 20 => 'twenty',
		 30 => 'thirty',
		 40 => 'forty',
		 50 => 'fifty',
		 60 => 'sixty',
		 70 => 'seventy',
		 80 => 'eighty',
		 90 => 'ninety',
    );

sub to_word {
    my $n = $_[0];
    if ( $n < 100 ) {
	if ( $word_for{$n} ) {
	    return $word_for{$n};
	}
	return join " ", $word_for{10 * int($n/10)}, $word_for{ $n % 10 };
    }
    elsif ( $n < 1000 ) {
	my $hundreds = to_word( int($n/100) );
	my $remainder = $n % 100;
	if ( $remainder == 0 ) {
	    return "$hundreds hundred";
	}
	else {
	    return "$hundreds hundred and " . to_word( $remainder );
	}
    }
    elsif ( $n < 1000000 ) {
	my $thousands = to_word( int($n/1000) );
	my $remainder = $n % 1000;
	if ( $remainder == 0 ) {
	    return "$thousands thousand";
	}
	elsif ( $remainder < 100 ) {
	    return "$thousands thousand and " . to_word( $remainder );
	}
	else {
	    return "$thousands thousand " . to_word( $remainder );
	}
    }
    die "Number out of range: $n\n";
}

my $sum = 0;
for ( 1..1000 ) {
    my $str = to_word( $_ );
    $str =~ s/\W//g;
    $sum += length $str;
}

print "$sum\n";
