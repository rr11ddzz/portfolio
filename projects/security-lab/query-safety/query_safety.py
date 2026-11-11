"""A later portfolio exercise using fictional, in-memory records only.

Python standard library; no network, credentials or persistent database.
"""
import sqlite3


def demonstration_database():
    connection = sqlite3.connect(':memory:')
    connection.execute('CREATE TABLE people (id INTEGER PRIMARY KEY, name TEXT NOT NULL UNIQUE)')
    connection.executemany('INSERT INTO people (name) VALUES (?)', [('Alex',), ("O'Neil",), ('Sam',)])
    return connection


def find_person(connection, name):
    """Bind a value separately from the fixed SQL statement."""
    return connection.execute('SELECT id, name FROM people WHERE name = ?', (name,)).fetchall()


if __name__ == '__main__':
    db = demonstration_database()
    try:
        for value in ['Alex', "O'Neil", "' OR 1=1 --", "'; DROP TABLE people; --"]:
            print(f'{value!r} -> {find_person(db, value)}')
    finally:
        db.close()
