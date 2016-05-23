%% hasEdge: determine if two vertices have an edge between them
function [v] = hasEdge(a, b)
	xorSum = bitxor(a, b);
	v = xorSum ~= 0 & bitand(xorSum, xorSum - 1) == 0;
	return;
