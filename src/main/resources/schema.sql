-- schema.sql

CREATE TABLE loans (
    loan_id BIGINT PRIMARY KEY AUTO_INCREMENT,
    loan_amount DECIMAL(15, 2) NOT NULL,
    term INT NOT NULL,
    status VARCHAR(20) NOT NULL,
    remaining_balance DECIMAL(15, 2) NOT NULL
);

CREATE TABLE payments (
    payment_id BIGINT PRIMARY KEY AUTO_INCREMENT,
    loan_id BIGINT NOT NULL,
    payment_amount DECIMAL(15, 2) NOT NULL,
    payment_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (loan_id) REFERENCES loans(loan_id)
);
