CREATE TABLE "waybills" (
	"org_send_id" bigint NOT NULL,
	"number" bigint NOT NULL,
	"date" DATE NOT NULL,
	CONSTRAINT "waybills_pk" PRIMARY KEY ("org_send_id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "organizations" (
	"id" bigint NOT NULL,
	"name" varchar(255) NOT NULL,
	"inn" bigint NOT NULL,
	"settlement_acc" bigint NOT NULL,
	CONSTRAINT "organizations_pk" PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "positions" (
	"nomenclature_id" bigint NOT NULL,
	"waybill_id" bigint NOT NULL,
	"price" bigint NOT NULL,
	"amount" bigint NOT NULL,
	CONSTRAINT "positions_pk" PRIMARY KEY ("nomenclature_id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "nomenclature" (
	"id" bigint NOT NULL,
	"name" varchar(255) NOT NULL,
	"code" bigint NOT NULL,
	CONSTRAINT "nomenclature_pk" PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



ALTER TABLE "waybills" ADD CONSTRAINT "waybills_fk0" FOREIGN KEY ("org_send_id") REFERENCES "organizations"("id");


ALTER TABLE "positions" ADD CONSTRAINT "positions_fk0" FOREIGN KEY ("nomenclature_id") REFERENCES "nomenclature"("id");
ALTER TABLE "positions" ADD CONSTRAINT "positions_fk1" FOREIGN KEY ("waybill_id") REFERENCES "waybills"("org_send_id");

