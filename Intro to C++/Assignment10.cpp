% clc
% clear all

A = dlmread('goldDataA.txt'); %reads the text file into a matrix
N = 10; %number of rows - will need this value as the end point of the for loop
p = pi;

for i = 1:N % for loop iterates from 1 to 10 with an increment of 1
    ids(i) = i; % set the ids value to the current index
    x = A(i,4)*A(i,3)*A(i,3)*A(i,2)*p; 
%     x is the mass constant for each row (density*radius*radius*length*pi)

% the code below multiplies the mass constant by the concentrations
    goldMasses(i) = x*A(i,5);    
    sulfurMasses(i) = x*A(i,6);
    arsenicMasses(i) = x*A(i,7);

    % necessary conditions for cutoff
    if A(i,5) > 1 && A(i,6) < 5 && A(i,7) < 1 
        cutoffConditions(i) = 'Y';
    else 
        cutoffConditions(i) = 'N';
    end
    
end

[sortedGoldMasses, idx_sort] = sort(goldMasses, 'descend');
% sorts the gold massess and their respective indices from highest to
% lowest

disp('ID, Gold Mass [g], Sulfur Mass [Kg], Arsenic Mass [Kg], Cutoff conditions? (Y/N)');
for j = 1:N
    val = idx_sort(j); %index value assigned to the one corresponding to the appropriate gold mass
    fprintf('%d, %0.2f, %0.2f, %0.2f, %c\n', ids(val), sortedGoldMasses(j), sulfurMasses(val), arsenicMasses(val), cutoffConditions(val))
end
fprintf('Max mass: %0.2f g at ID %d\n', sortedGoldMasses(1), idx_sort(1)); %max mass will always be at index 1