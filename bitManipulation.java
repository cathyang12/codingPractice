Get i th bit: (x & (1<<c)) != 0
Set i th bit to 1:  x | (1<<c)
Cleat ith bit to 0: mask = ~(1<<i)
	 		        res = mask & x
Clear the bit with the most significant bit through i
  	mask (1 << i) -1;   00001111
  	num & mask

Clear all bits from in rhough 0 (inclusive)
 	mask = ~( -1 >>> (31-i))   111111 => 00011 => 11100
  	num & mask

Update bits:
	value = bitIs1 ? 1: 0;
	mask = ~(1 << i);
	(num & mask) | (value << i);
