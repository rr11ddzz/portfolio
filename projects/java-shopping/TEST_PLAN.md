# Coursework test plan and source review

The original submitted test plan contains the following ten scenarios. These are planned checks, not a record of successful execution.

1. Add an electronics product.
2. Add a clothing product.
3. Attempt to add beyond maximum capacity.
4. Delete an existing product.
5. Delete a non-existing product.
6. Print an empty product list.
7. Print a non-empty product list.
8. Save an empty product list.
9. Save and reload a mixture of product types.
10. Enter an invalid price.

The original capacity test's expected-output wording is ambiguous. The implementation rejects additions once there are 15 products; that boundary should be asserted explicitly.

## Source-review findings

- The writer omits a product-type marker that the reader expects. Mixed-type persistence needs a corrected format and round-trip test.
- The Swing low-stock display changes numeric table cells into HTML strings, while addToCart casts them to numbers. Use a renderer without changing the underlying values.
- The details area and button panel both use BorderLayout.SOUTH.
- The discount code counts distinct categories instead of the number of products in a category.

No new Java execution was performed during this portfolio review. Original source files are unchanged.
