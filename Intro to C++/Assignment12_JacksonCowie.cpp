clc
clear all

%starter code
height = input('Enter controlled-descent height (m):');
fprintf('\n');
rho = input('Enter fluid density (kg/m^3):');
fprintf('\n');

% defining constants (values don't change in the loops)
p = pi;
m = 15600;
stageForce = 174850;
tMax = 60;
dt = [1.00 0.10 0.01];

for i = 1:3 % for loop runs for three iterations (three dts)
    
    % variables below reset for each dt value as they are changed in the
    % while loop
    position = 10000;
    velocity = -1500/3.6; % rocket moving downwards, so velocity is negative
    t = 0;
    timeCount = 0;

    while position > 0 % while loop runs until the rocket hits the ground

        if position <= height && timeCount <= tMax % once the controlled-descent height is reached and 
            % while the time counter is less than or equal to 60 
            % seconds, the stage force is applied
            F = stageForce;
            timeCount = timeCount + t; % time counter to ensure the stage force is only applied for 60 seconds
        else
            F = 0;
        end

        fDrag = -0.5*1.0*rho*p*1.85*1.85*velocity*velocity; % drag equation

        if velocity > 0 
            fDrag = -fDrag;  % velocity always opposite to drag force 
            % (error handling for end of descent if velocity becomes positive)
        end

        acceleration = (F + fDrag - 9.8)/m; % stage force and fDrag would 
        % both act upwards, and gravity would act downards

        position = position + velocity*dt(i); % update position

        velocity = velocity + acceleration*dt(i); % update velocity

        t = t + dt(i); % update time
        
    end
    
    fprintf("dt = %0.2f s, Total time = %.0f s, Position = %.0f m\n", dt(i), t, position); %print the values once the while
    % loop is done

end 

fprintf("landing speed: %.0f km/h\n", velocity*3.6); % print the velocity of the 0.01 dt value in km/h

% conditional statement for a successful/non-successful landing
if velocity <= 10
    disp("Successful landing");
else
    disp("Non-successful landing");
end


%I understand that my code does not output the correct values, however, I
%think my logic is mostly correct.

%I interpreted the question as the rocket starting at a height of 10,000m
%and the having the force applied at the controlled-descent height for a
%total of 60s.

%acceleration, position, velocity, and time are updated for each iteration
%of the while loop