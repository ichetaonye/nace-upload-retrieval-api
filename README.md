Given source account exists
and target account exists
and source account has a balance greater or equal to the transaction amount When a transaction request is received
Then the balance of source account should be debited
and the balance of target account should be credited