import unittest
from query_safety import demonstration_database, find_person


class ParameterizedLookupTests(unittest.TestCase):
    def setUp(self):
        self.db = demonstration_database()
        self.addCleanup(self.db.close)

    def test_expected_record_is_returned(self):
        self.assertEqual(find_person(self.db, 'Alex'), [(1, 'Alex')])

    def test_legitimate_apostrophe_is_supported(self):
        self.assertEqual(find_person(self.db, "O'Neil"), [(2, "O'Neil")])

    def test_boolean_expression_cannot_expand_results(self):
        self.assertEqual(find_person(self.db, "' OR 1=1 --"), [])

    def test_statement_like_input_does_not_change_schema_or_data(self):
        before = self.db.execute('SELECT * FROM people ORDER BY id').fetchall()
        self.assertEqual(find_person(self.db, "'; DROP TABLE people; --"), [])
        self.assertEqual(self.db.execute('SELECT * FROM people ORDER BY id').fetchall(), before)

    def test_unknown_empty_and_wildcard_values_are_literal(self):
        for value in ['Missing', '', '%', '_', 'Alex --']:
            with self.subTest(value=value):
                self.assertEqual(find_person(self.db, value), [])

    def test_sql_like_text_can_itself_be_a_stored_value(self):
        value = "' OR 1=1 --"
        self.db.execute('INSERT INTO people (name) VALUES (?)', (value,))
        self.assertEqual(find_person(self.db, value), [(4, value)])


if __name__ == '__main__':
    unittest.main()
