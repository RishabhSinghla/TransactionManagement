-- src/main/resources/import.sql

-- Inserting sample failure transactions for account number '12233300011001'
INSERT INTO Transaction (account_Number, transaction_Id, status, amount, date)
VALUES
    ('12233300011001', '345577721', 'fail', '500', '30-04-2023'),
    ('12233300011001', '245900023', 'fail', '50', '29-05-2023');
