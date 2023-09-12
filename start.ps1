# Define the path to the SQLite executable and the database file
$databaseFile = "banco.db"

# Define the path to the SQL code file
$sqlCodeFile = "banco.sql"

Remove-Item banco.db
New-Item banco.db

# Start the SQLite shell and run the SQL code
$sqliteProcess = Start-Process sqlite3.exe -ArgumentList $databaseFile, ('".read ' + $sqlCodeFile + '"') -PassThru -NoNewWindow
$sqliteProcess.WaitForExit()
$sqliteProcess = Start-Process python.exe -ArgumentList ".\inserir_dados.py" -PassThru -NoNewWindow
$sqliteProcess.WaitForExit()
$sqliteProcess = Start-Process sqlite3.exe -ArgumentList $databaseFile -PassThru -NoNewWindow
$sqliteProcess.WaitForExit()

