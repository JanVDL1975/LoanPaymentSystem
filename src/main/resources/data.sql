-- data.sql

-- Insert a sample loan
INSERT INTO loans (loan_id, loan_amount, term, status, remaining_balance)
VALUES
  ('11111111-1111-1111-1111-111111111111', 10000.00, 12, 'ACTIVE', 10000.00);

-- Insert sample payments for the above loan
INSERT INTO payments (payment_id, loan_id, payment_amount, payment_date)
VALUES
  ('22222222-2222-2222-2222-222222222222', '11111111-1111-1111-1111-111111111111', 2000.00, CURRENT_TIMESTAMP),
  ('33333333-3333-3333-3333-333333333333', '11111111-1111-1111-1111-111111111111', 1500.00, CURRENT_TIMESTAMP);
