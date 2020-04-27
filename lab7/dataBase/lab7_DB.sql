CREATE TABLE "Client" ( "id_client" INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE, "first_name_client" TEXT NOT NULL, "last_name_client" TEXT NOT NULL );
CREATE TABLE "Installation" ( "id_router" INTEGER NOT NULL UNIQUE, "address" TEXT NOT NULL, "id_price" INTEGER NOT NULL, "id_client" INTEGER NOT NULL, "id_installation" INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE, FOREIGN KEY("id_client") REFERENCES "Client"("id_client"), FOREIGN KEY("id_price") REFERENCES "Price"("id_price") );
CREATE TABLE "Payment" ( "payment_date" TEXT NOT NULL, "amount" INTEGER NOT NULL, "id_client" INTEGER NOT NULL, "id_payment" INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE, FOREIGN KEY("id_client") REFERENCES "Client"("id_client") );
CREATE TABLE "Price" ( "type" TEXT NOT NULL, "price" INTEGER NOT NULL, "id_price" INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE );
INSERT INTO "main"."Price" VALUES('new','15','1');
INSERT INTO "main"."Price" VALUES('active','20','2');
INSERT INTO "main"."Price" VALUES('expired','0','3');
CREATE TABLE "Receivables" ( "pay_to" TEXT NOT NULL, "amount_to_pay" INTEGER NOT NULL, "id_client" INTEGER NOT NULL, "id_recivables" INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE, FOREIGN KEY("id_client") REFERENCES "Client"("id_client") );
