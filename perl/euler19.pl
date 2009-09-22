#!/usr/bin/perl

use strict;
use warnings FATAL => 'all';

sub is_leap {
    my $year = shift;
    if ( $year % 4 == 0 and (  $year % 100 != 0 or $year % 400 == 0 ) ) {
	return 1;
    }
    return 0;
}

{

    my @days_in_month = ( 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 );

    sub days_in_month {
	my ( $month, $year ) = @_;
	if ( $month != 2 ) {
	    return $days_in_month[ $month - 1 ];
	}
	return $days_in_month[ $month - 1 ] + is_leap( $year );
    }
}

my $count = 0;
my $year = 1900;
my $month = 1;
my $day = 1;
my $dow = 1;

while ( $year < 2000 or $month < 12 or $day < 31 ) {
    $day += 1;
    if ( $day > days_in_month( $month, $year ) ) {
	$day = 1;
	$month += 1;
	if ( $month > 12 ) {
	    $month = 1;
	    $year += 1;
	}
    }
    $dow = ( $dow + 1 ) % 7;
    $count++ if $year > 1900 and $day == 1 and $dow == 0;
}

print "$count\n";
