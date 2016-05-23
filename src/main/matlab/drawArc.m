%% drawArc: draw an arc
function [counters, heights] = drawArc(counters, heights, i1, i2, ...
	total, color)
	maximum = 0;

	if i1 > i2
		i2 = i2 + total;
	end
	for i = i1 : i2 - 1
		index = mod(i - 1, total) + 1;
		if heights(index) > maximum
			maximum = heights(index);
		end
	end
	maximum = maximum + 1;
	for i = i1 : i2 - 1
		index = mod(i - 1, total) + 1;
		heights(index) = maximum;
		counters(index) = counters(index) + 1;
	end

	radius = 1 + maximum * 0.05;
	delta = pi / 200;
	degree = 2 * pi / total;
	theta = (i1 - 1) * degree + delta : pi / 1000 : (i2 - 1) * degree - delta;
	arcX = radius * sin(theta);
	arcY = radius * cos(theta);
	plot(arcX, arcY, color);
