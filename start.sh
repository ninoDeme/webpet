#!/usr/bin/env bash

# Define the path to the SQLite executable and the database file
databaseFile="banco.db"

# Define the path to the SQL code file
sqlCodeFile="banco.sql"

# Remove the existing database file if it exists
rm -f "$databaseFile"

# Create a new empty database file
touch "$databaseFile"

# Start the SQLite shell and run the SQL code
sqlite3 "$databaseFile" < "$sqlCodeFile"

if [ ! -e "dados.csv" ]; then
  python3 pushar_dados.py
fi

# Start the Python script
python3 inserir_dados.py

# Start the SQLite shell again
sqlite3 "$databaseFile"
