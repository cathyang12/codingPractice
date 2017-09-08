get i th bit: (x & (1<<c)) != 0
set i th bit to 1:  x | (1<<c)
cleat ith bit to 0: mask = ~(1<<i)
	 		        res = mask & x
to clear the bit with the most significant bit through i
: mask (1 << i) -1;   00001111
  num & mask

to clear all bits from in rhough 0 (inclusive)
: mask = ~( -1 >>> (31-i))   111111 => 00011 => 11100
  num & mask
