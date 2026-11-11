# Query safety: from recommendation to executable check

A November 2026 **AI-assisted companion exercise**, added after the original virtual-machine coursework. It demonstrates one mitigation discussed in the case study: separating SQL code from input values with parameter binding.

This uses Python's standard library and a temporary in-memory SQLite database containing three fictional names. It makes no network connections, accepts no target URLs and requires no credentials. It does not modify DVWA or claim a retest of the original VM environment.

From this directory, with Python 3.12:

```sh
python3 query_safety.py
python3 -m unittest -v
```

**Verified: six tests passed on 11 November 2026.**

| Check | Expected behaviour |
| --- | --- |
| Known name | Returns only the matching record |
| Legitimate apostrophe | Works without stripping or manually escaping the name |
| Boolean SQL expression | Does not return unrelated records |
| Statement-like input | Does not alter the table or its records |
| Empty, unknown and wildcard text | Remains a literal lookup value |
| SQL-like text deliberately stored as a name | Matches exactly that stored value |

`find_person` uses a fixed SQL statement and a separate parameter tuple. Parameter binding protects this value position; it does not make arbitrary SQL fragments, dynamic column names, authorization or the rest of an application safe. The tests cover this small function, not the original lab's entire security posture.

[Back to the security case study](https://rr11ddzz.github.io/portfolio/projects/security-lab/)
