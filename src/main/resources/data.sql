-- Insert a sample loan with ID = 1
INSERT INTO loan (loan_id, loan_amount, term, status, remaining_balance)
VALUES
  (1, 10000.00, 12, 'ACTIVE', 10000.00);

-- Insert sample payments for loan ID = 1
INSERT INTO payment (payment_id, loan_id, payment_amount, payment_date)
VALUES
  (1, 1, 2000.00, CURRENT_TIMESTAMP),
  (2, 1, 1500.00, CURRENT_TIMESTAMP);