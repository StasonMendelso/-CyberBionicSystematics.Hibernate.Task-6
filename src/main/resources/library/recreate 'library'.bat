set USERNAME=root
set PASSWORD=root

mysql -u%USERNAME% -p%PASSWORD% < "%~dp0\library_db.sql"

pause