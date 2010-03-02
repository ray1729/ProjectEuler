#!/usr/bin/perl

use strict;
use warnings FATAL => 'all';

use List::Util qw( min sum );

my @matrix;

while( <> ) {
    chomp;
    my @cols = split ',';
    push @matrix, \@cols;
}

my $result = solve( \@matrix );

print "$result\n";

sub solve {
    my $matrix = shift;

    print_matrix( $matrix );

    my $ncols = @{ $matrix->[0] };
    if ( $ncols == 1 ) {
	return min( map $_->[0], @{ $matrix } );
    }

    my $nrows = @{ $matrix };

    my @new_matrix;

    for ( @{ $matrix } ) {
	my $truncate = @{ $matrix->[0] } - 3;
	push @new_matrix, [ @{$_}[0..$truncate] ];
    }

    for( my $i = 0; $i < $nrows; $i++ ) {
	my @path_sums;

	for( my $j = 0; $j < $nrows; $j++ ) {
	    my $this_path_sum = 0;
	    if ( $j < $i ) {
		for( my $k = $i; $k >= $j; $k-- ) {
		    $this_path_sum += $matrix->[ $k ][ $ncols - 2 ];
		}
	    }
	    else {
		for( my $k = $i; $k <= $j; $k++ ) {
		    $this_path_sum += $matrix->[ $k ][ $ncols - 2 ]; 
		}
	    }
	    $this_path_sum += $matrix->[ $j ][ $ncols - 1 ];
	    push @path_sums, $this_path_sum;
	}
	push @{ $new_matrix[$i] }, min( @path_sums );	
    }

    solve( \@new_matrix );
}


sub print_matrix {
    my $matrix = shift;

    for my $row ( @{ $matrix } ) {
	print join( " ", map sprintf( '% 4d', $_ ), @{ $row } ) . "\n";
    }
    print "\n";
}

#131	673	234	103	18
#201	96	342	965	150
#630	803	746	422	111
#537	699	497	121	956
#805	732	524	37	331


#  0  121 | 103 +  18 

#  1 1218 | 103 + 965 + 150

#  2 1601 | 103 + 965 + 422 + 111

#  3 2567 | 103 + 965 + 422 + 121 + 956

#  4 1979 | 103 + 965 + 422 + 121 +  37 + 331

# --

# -1 1086 | 965 + 103 + 18

#  0 1115 | 965 + 150

#  1 1498 | 965 + 422 + 111

#  2 2464 | 965 + 422 + 121 + 956

#  3 1876 | 965 + 422 + 121 +  37 + 331

# --

# -2 | 422 + 965 + 103 + 18

# -1 | 422 + 965 + 150

#  0 | 422 + 111

#  1 | 422 + 121 + 956

#  2 | 422 + 121 + 37 + 331

# --

# -3 | 121 + 422 + 965 + 103 + 18

# -2 | 121 + 422 + 965 + 150

# -1 | 121 + 422 + 111

#  0 | 122 + 956

#  1 | 121 +  37 + 331

