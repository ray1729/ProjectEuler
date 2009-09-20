#!/usr/bin/perl -l

use strict;
use warnings FATAL => 'all';

#use Smart::Comments;

my %seqlen = ( 1 => 1 );

for ( 1..999999 ) {
    next if $seqlen{ $_ };
    my @seq = ($_);
    while ( not $seqlen{ $seq[-1] } ) {
        push @seq, nextval( $seq[-1] );
    }
    for ( my $i = 0; $i < @seq; $i++ ) {
        $seqlen{ $seq[$i] } = @seq + $seqlen{ $seq[-1] } - $i - 1;
    }
}

my ( $val, $max_len ) = ( 0, 0 );

while ( my ( $v, $l ) = each %seqlen ) {
    if ( $l > $max_len ) {
        $max_len = $l;
        $val = $v;
    }
}

print "$val => $max_len";

sub nextval {
    my $n = shift;
    if ( $n == 1 ) {
        return;
    }
    elsif ( $n % 2 ) {
        return 3 * $n + 1; 
    }
    else {
        return $n / 2;
    }
}


#sub seqlen {
#    my $n = shift;
#    if ( $seqlen{ $n } ) {
#        return $seqlen{ $n };
#    }
#    if ( my $nv = nextval( $n ) ) {
#        return $seqlen{ $n } = 1 + seqlen( $nv );
#    }
#    return 1;
#}
