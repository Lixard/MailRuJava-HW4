CREATE TABLE "Накладные" (
	"id" serial NOT NULL,
	"Номер" bigint  NOT NULL,
	"Дата" DATE NOT NULL,
	"Организация отправитель" varchar(255) NOT NULL,
	CONSTRAINT "Накладные_pk" PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "Организации" (
	"id" bigint  NOT NULL,
	"Название" varchar(255) NOT NULL,
	"ИНН" bigint  NOT NULL,
	"Расчетный счет" bigint  NOT NULL
) WITH (
  OIDS=FALSE
);



CREATE TABLE "Позиции" (
	"id" serial NOT NULL,
	"Цена" bigint  NOT NULL,
	"Номенклатура" varchar NOT NULL,
	"Количество" bigint  NOT NULL,
	"waybill_id" bigint  NOT NULL,
	CONSTRAINT "Позиции_pk" PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "Номенклатура" (
	"id" bigint  NOT NULL,
	"Наименование" varchar(255) NOT NULL,
	"Внутренний код" bigint  NOT NULL
) WITH (
  OIDS=FALSE
);




ALTER TABLE "Организации" ADD CONSTRAINT "Организации_fk0" FOREIGN KEY ("id") REFERENCES "Накладные"("id");

ALTER TABLE "Позиции" ADD CONSTRAINT "Позиции_fk0" FOREIGN KEY ("waybill_id") REFERENCES "Накладные"("id");

ALTER TABLE "Номенклатура" ADD CONSTRAINT "Номенклатура_fk0" FOREIGN KEY ("id") REFERENCES "Позиции"("id");
