@echo off
start /min /wait gmast_analysis.exe
IF ERRORLEVEL 1 goto was_cancelled
rem start /min /wait %comspec% /c mast_command
@type mast_command > mast_command.bat
@call .\mast_command

IF ERRORLEVEL 1 goto dont_view_results

@type mast_results_command > mast_results_command.bat
@call .\mast_results_command
@del mast_command.bat
@del mast_results_command.bat
echo Mast analysis completed
goto the_end

:dont_view_results
@del mast_command.bat
goto the_end

:was_cancelled
rem echo cancelled

:the_end
pause
rem exit