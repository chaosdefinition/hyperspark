axis equal;
axis off;
hold on;

% open data file
[file, path] = uigetfile('*');
if file == 0
	quit;
end
fileID = fopen(strcat(path, file));

while 1
	% read a line, each line representing a result
	line = fgetl(fileID);
	if line == -1
		break;
	end
	data = sscanf(line, '%*c%d')';
	total = size(data, 2);

	% start drawing the figure, first clearing previous figure
	cla;

	% draw a basic circle
	innerTheta = 0 : pi / 10000 : 2 * pi;
	innerX = 1 * sin(innerTheta);
	innerY = 1 * cos(innerTheta);
	plot(innerX, innerY, 'k');

	% draw the marks
	markTheta = 0 : 2 * pi / total : 2 * pi;
	markX = [];
	markY = [];
	for j2 = 1 : total
		markX = [1, 0.9, 0.8] * sin(markTheta(j2));
		markY = [1, 0.9, 0.8] * cos(markTheta(j2));
		plot(markX([1, 2]), markY([1, 2]), 'k');
		text(markX(3), markY(3), num2str(data(j2)), 'HorizontalAlignment', 'center');
	end

	% draw the outside arcs
	counters = zeros(1, total);
	heights = zeros(1, total);
	colors = ['r', 'g', 'b', 'm', 'y', 'c'];
	for i = 0 : log2(total) - 1
		for j = 1 : total / 2
			for k = 1 : total
				if j == 8 && k > 7
					continue;
				end
				j2 = mod(k + j - 1, total) + 1;
				if hasEdge(data(k), data(j2)) && ...
					bitxor(data(k), data(j2)) == 2^i
					[counters, heights] = drawArc(counters, heights, ...
						k, j2, total, ...
						colors(i + 1));
				end
			end
		end
	end

	% apply figure style
	sdf;

	% listen to keyboard to choose the next action
	while 1
		try
			if waitforbuttonpress
				action = get(gcf, 'CurrentCharacter');
			end
		catch
			action = 'q';
		end
		switch action
		% draw the next figure
		case 'n'
			break;
		% quit
		case 'q'
			fclose(fileID);
			quit;
		end
	end
end

fclose(fileID);
quit;
