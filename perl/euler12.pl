#!/usr/bin/perl -l

use strict;
use warnings FATAL => 'all';

use List::Util 'reduce';

my $n = 1;
my $triangle = $n;

while( num_divisors( $triangle ) < 500 ) {
    $n++;
    $triangle += $n;
}

print $triangle;


sub num_divisors {
    my $n = shift;
    product( map $_+1, values %{ factorize( $n ) } );
}

sub product {
    return 1 unless @_;
    local( $a, $b );
    reduce { $a * $b } @_;
}

sub factorize {
    my $n = shift;
    
    my $pg = PrimeGenerator->new;

    my %factors;
    while ( $n > 1 ) { 
        my $p = $pg->next;
        while ( $n % $p == 0 ) {
            $factors{ $p }++;
            $n = $n / $p;
        }
    }

    return \%factors;
}

package PrimeGenerator;

my @primes;
BEGIN {
    @primes = ( 2, 3 );
}

sub new {
    my $class = shift;
    my $ix = 0;
    bless \$ix, $class;
}

sub reset {
    my $self = shift;
    $$self = 0;
    return $self;
}

sub next {
    my $self = shift;
    if ( $$self >= @primes ) {
        $self->grow_primes;
    }
    $primes[$$self++];
}

sub grow_primes {
    my $class = shift;
    my $first_candidate = $primes[-1] + 1;
    my $last_candidate = $primes[-1] * $primes[-1];
    my @candidates = ( $first_candidate .. $last_candidate );
    foreach my $p ( @primes ) {
        for ( my $ix = 0; $ix < @candidates ; $ix += $p ) {
            undef $candidates[$ix - $first_candidate];
        }
    }
    push @primes, grep defined, @candidates;
}