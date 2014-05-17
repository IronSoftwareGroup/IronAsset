INSERT INTO lov values( 'ASSET_TYPE1', 'OS', 'Operating System');
INSERT INTO lov values( 'ASSET_TYPE1', 'HW', 'Hardware');
INSERT INTO lov values( 'ASSET_TYPE1', 'SW', 'Software');

INSERT INTO lov values( 'ASSET_TYPE2', 'Monitor', 'PC Monitor');
INSERT INTO lov values( 'ASSET_TYPE2', 'Printer', 'Printer');
INSERT INTO lov values( 'ASSET_TYPE2', 'Router', 'Router');
INSERT INTO lov values( 'ASSET_TYPE2', 'Switch', 'Switch');
INSERT INTO lov values( 'ASSET_TYPE2', 'Cable', 'Cable');
INSERT INTO lov values( 'ASSET_TYPE2', 'Tablet', 'Tablet');
INSERT INTO lov values( 'ASSET_TYPE2', 'Smartphone', 'Smartphone');
INSERT INTO lov values( 'ASSET_TYPE2', 'PC', 'Personal Computer');
INSERT INTO lov values( 'ASSET_TYPE2', 'Workstation', 'Workstation');
INSERT INTO lov values( 'ASSET_TYPE2', 'Server', 'Server');
INSERT INTO lov values( 'ASSET_TYPE2', 'Thin Client', 'Thin Client');
INSERT INTO lov values( 'ASSET_TYPE2', 'PDA', 'PDA');

INSERT INTO lov values( 'ASSET_TYPE2', 'Network', 'Network');
INSERT INTO lov values( 'ASSET_TYPE2', 'Productivity', 'Productivity');
INSERT INTO lov values( 'ASSET_TYPE2', 'Audio', 'Audio');
INSERT INTO lov values( 'ASSET_TYPE2', 'Video', 'Video');
INSERT INTO lov values( 'ASSET_TYPE2', 'Internet', 'Internet');
INSERT INTO lov values( 'ASSET_TYPE2', 'Uitility', 'Utility');
INSERT INTO lov values( 'ASSET_TYPE2', 'Business', 'Business');

INSERT INTO lov values( 'ASSET_TYPE3', 'Inventory', 'Inventory');
INSERT INTO lov values( 'ASSET_TYPE3', 'Sales', 'Sales');
INSERT INTO lov values( 'ASSET_TYPE3', 'Finance', 'Finance');
INSERT INTO lov values( 'ASSET_TYPE3', 'Purchase', 'Purchase');
INSERT INTO lov values( 'ASSET_TYPE3', 'Manufacturing', 'Manufacturing');
INSERT INTO lov values( 'ASSET_TYPE3', 'EDP', 'EDP');


INSERT INTO lov values( 'CURRENCY', 'EUR', 'Euro');
INSERT INTO lov values( 'CURRENCY', 'USD', 'Usd');

INSERT INTO lov values( 'UOM', 'EA', 'Each'
INSERT INTO lov values( 'UOM', 'Per Socket', 'Software is licenzed per single socket');
INSERT INTO lov values( 'UOM', 'Per Core', 'Software is licenzed per single core');
INSERT INTO lov values( 'UOM', 'Per User', 'Software is licensed per single user');

INSERT INTO lov values( 'VENDOR', 'Microsoft', 'Microsoft');
INSERT INTO lov values( 'VENDOR', 'Oracle', 'Oracle');
INSERT INTO lov values( 'VENDOR', 'IBM', 'International Business Machine');
INSERT INTO lov values( 'VENDOR', 'Apple', 'Apple');

INSERT INTO global_properties values( 'EMAIL', 'CC', 'admin@ironsg.com','Copy email address');
INSERT INTO global_properties values( 'EMAIL', 'SENDER', 'admin@ironsg.com','Email sender');
INSERT INTO global_properties values( 'EMAIL', 'RECEIVER', 'admin@ironsg.com','Email receiver');
INSERT INTO global_properties values( 'ASSET_GRID', 'id', 'false','set true to show the column');
INSERT INTO global_properties values( 'ASSET_GRID', 'name', 'true','set true to show the column');
INSERT INTO global_properties values( 'ASSET_GRID', 'description', 'false','set true to show the column');
INSERT INTO global_properties values( 'ASSET_GRID', 'type', 'true','set true to show the column');
INSERT INTO global_properties values( 'ASSET_GRID', 'type2', 'true','set true to show the column');
INSERT INTO global_properties values( 'ASSET_GRID', 'type3', 'false','set true to show the column');
INSERT INTO global_properties values( 'ASSET_GRID', 'serial', 'true','set true to show the column');
INSERT INTO global_properties values( 'ASSET_GRID', 'warranty', 'true','set true to show the column');
INSERT INTO global_properties values( 'ASSET_GRID', 'requestDate', 'false','set true to show the column');
INSERT INTO global_properties values( 'ASSET_GRID', 'startDate', 'false','set true to show the column');
INSERT INTO global_properties values( 'ASSET_GRID', 'endDate', 'true','set true to show the column');
INSERT INTO global_properties values( 'ASSET_GRID', 'unitPrice', 'false','set true to show the column');
INSERT INTO global_properties values( 'ASSET_GRID', 'totalPrice', 'false','set true to show the column');
INSERT INTO global_properties values( 'ASSET_GRID', 'currency', 'false','set true to show the column');
INSERT INTO global_properties values( 'ASSET_GRID', 'units', 'true','set true to show the column');
INSERT INTO global_properties values( 'ASSET_GRID', 'uom', 'false','set true to show the column');
INSERT INTO global_properties values( 'ASSET_GRID', 'vendorCompany', 'true','set true to show the column');
INSERT INTO global_properties values( 'ASSET_GRID', 'vendorName', 'false','set true to show the column');
INSERT INTO global_properties values( 'ASSET_GRID', 'vendorEmail', 'false','set true to show the column');
INSERT INTO global_properties values( 'ASSET_GRID', 'vendorPhone', 'false','set true to show the column');
INSERT INTO global_properties values( 'ASSET_GRID', 'vendorWebsite', 'false','set true to show the column');
INSERT INTO global_properties values( 'ASSET_GRID', 'note', 'false','set true to show the column');
INSERT INTO global_properties values( 'ASSET_GRID', 'totReminder', 'false','set true to show the column');
INSERT INTO global_properties values( 'ASSET_GRID', 'sentReminder', 'true','set true to show the column');
