-- src/main/resources/import.sql

-- Inserting sample pending transactions for account number '12233300011001'
INSERT INTO Transaction (account_Number, transaction_Id, status, amount, date)
VALUES
    ('12233300011001', '88797721', 'pending', '500', '30-04-2023'),
    ('12233300011001', '88797722', 'pending', '800', '30-04-2023');
