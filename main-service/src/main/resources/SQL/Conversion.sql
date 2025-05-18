Create table conversions(
id SERIAL PRIMARY KEY,
from_currency VARCHAR(255) NOT NULL,
 to_currency VARCHAR(255) NOT NULL,
 amount NUMERIC(38, 2) NOT NULL,
 converted_amount NUMERIC(38, 2) NOT NULL,
  exchange_rate NUMERIC(38, 2) NOT NULL,
 timestamp TIMESTAMP NOT NULL,
    rate NUMERIC (38, 2) NOT NULL
)

