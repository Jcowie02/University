clc

vals = [10 100 1000 10000 100000]; % array of N values

% starter code - takes the user input for the integration limits and 
% defines the function handle
a = input('Enter a:');
fprintf('\n');
b = input('Enter b:');
fprintf('\n');
f = @(x) 1.8.^(0.045.*x)-1;

q = quadgk(f, a, b); % evaluating the exact value of the integral
fprintf("True value: %.0f N\n", q);

for i = 1:5 % for loop runs 5 times (the number of N values)
    
    N = vals(i); % take the given N value
    xSet = linspace(a,b,N); % creates an array of 'N' values between limits a and b
    fSet = 1.8.^(0.045.*xSet)-1; % evaluate the function at each point in the xSet
    dx = (b - a)/N; % width of the rectangular elements
    y = sum(fSet*dx); % add all of the rectangle elements
    error = ((y - q)/q) * 100; % error formula given in assignment
    
    fprintf("N = %d, F = %.0f N, Error %% = %0.2f\n", vals(i), y, error);
end 

% the code below performs the same operations as above, only for integration
% limits of 450 and 500 and an N value of 10000
xSet2 = linspace(450,500,vals(4));
fSet2 = 1.8.^(0.045.*xSet2)-1;
dx2 = (500 - 450)/vals(4);
y2 = sum(fSet2*dx2);

P = y2/(50*50); % the calculated force is then divided by the area (units of N/m^2)

fprintf("Average pressure between 450 and 500 m: %0.1f kPa\n", P/1000); % output the final
% value in kPa