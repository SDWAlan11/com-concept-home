ALTER TABLE payments 
ADD COLUMN VERSION INTEGER NOT NULL;

UPDATE payments
SET VERSION = 0;

-- ALTER TABLE payments ALTER COLUMN VERSION SET NOT NULL;

ALTER TABLE payments 
ADD COLUMN status CHAR(3);

UPDATE payments
SET status = 'OPN';

-- ALTER TABLE payments ALTER COLUMN status SET NOT NULL;

ALTER TABLE payments 
ADD COLUMN historic mediumblob;

UPDATE orders SET status = 'SHP' where status = 'Shipped';
UPDATE orders SET status = 'RSV' where status = 'Resolved';
UPDATE orders SET status = 'OHD' where status = 'On Hold';
UPDATE orders SET status = 'DSP' where status = 'Disputed';
UPDATE orders SET status = 'INP' where status = 'In Process';
UPDATE orders SET status = 'CNL' where status = 'Cancelled';

ALTER TABLE orders modify COLUMN status CHAR(3) NOT NULL;


