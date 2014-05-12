
CREATE TABLE global_properties (
                section VARCHAR(10) NOT NULL,
                entry VARCHAR(30) NOT NULL,
                value VARCHAR(256) NOT NULL,
                description VARCHAR(256),
                CONSTRAINT global_properties_pk PRIMARY KEY (section, entry)
);


CREATE TABLE reminder (
                asset_id INTEGER NOT NULL,
                sequence INTEGER NOT NULL,
                days INTEGER NOT NULL,
                sent VARCHAR(3) DEFAULT NO NOT NULL,
                CONSTRAINT rem_pk PRIMARY KEY (asset_id, sequence)
);


CREATE TABLE lov (
                subject VARCHAR(30) NOT NULL,
                entry VARCHAR(30) NOT NULL,
                description VARCHAR(256) NOT NULL,
                CONSTRAINT lov_pk PRIMARY KEY (subject, entry)
);


CREATE TABLE asset (
                id INTEGER NOT NULL,
                name VARCHAR(60),
                description VARCHAR(256),
                type VARCHAR(60),
                type2 VARCHAR(60),
                type3 VARCHAR(60),
                serial VARCHAR(120),
                warranty VARCHAR(60),
                request_date DATE,
                start_date DATE,
                end_date DATE,
                unit_price FLOAT,
                total_price FLOAT,
                price_metric VARCHAR(60),
                currency VARCHAR(3),
                units FLOAT,
                uom VARCHAR(10),
                vendor_company VARCHAR(60),
                vendor_name VARCHAR(60),
                vendor_email VARCHAR(60),
                vendor_phone VARCHAR(60),
                vendor_website VARCHAR(256),
                note VARCHAR(256),
                tot_reminder INTEGER,
                sent_reminder INTEGER,
                CONSTRAINT asset_pk PRIMARY KEY (id)
);
