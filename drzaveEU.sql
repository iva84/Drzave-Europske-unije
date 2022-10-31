--
-- PostgreSQL database dump
--

-- Dumped from database version 13.4
-- Dumped by pg_dump version 13.4

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: drzava; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.drzava (
    "ISOozn" character varying(3) NOT NULL,
    naziv character varying NOT NULL,
    "datumUlaskaEU" timestamp without time zone NOT NULL,
    povrsina double precision,
    "brojStanovnika" integer,
    "nazivHimne" character varying,
    "glavniGrad" character varying,
    "puniNaziv" character varying
);


ALTER TABLE public.drzava OWNER TO postgres;

--
-- Name: drzavaJezik; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public."drzavaJezik" (
    "ISOoznDrz" character varying(3) NOT NULL,
    "ISOoznJez" character varying(2) NOT NULL
);


ALTER TABLE public."drzavaJezik" OWNER TO postgres;

--
-- Name: drzavaValuta; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public."drzavaValuta" (
    "ISOoznDrz" character varying(3) NOT NULL,
    "ISOoznVal" character varying(3) NOT NULL
);


ALTER TABLE public."drzavaValuta" OWNER TO postgres;

--
-- Name: drzavniVrh; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public."drzavniVrh" (
    "punoIme" character varying NOT NULL,
    "ISOoznDrz" character varying(3) NOT NULL,
    uloga character varying
);


ALTER TABLE public."drzavniVrh" OWNER TO postgres;

--
-- Name: jezik; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.jezik (
    "ISOozn" character varying(2) NOT NULL,
    naziv character varying NOT NULL
);


ALTER TABLE public.jezik OWNER TO postgres;

--
-- Name: valuta; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.valuta (
    "ISOozn" character varying(3) NOT NULL,
    naziv character varying NOT NULL
);


ALTER TABLE public.valuta OWNER TO postgres;

--
-- Data for Name: drzava; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.drzava ("ISOozn", naziv, "datumUlaskaEU", povrsina, "brojStanovnika", "nazivHimne", "glavniGrad", "puniNaziv") FROM stdin;
AUT	Austrija	1995-01-01 00:00:00	83879	8978929	Zemljo brda, zemljo na rijekama	Beč	Republika Austrija
BGR	Bugarska	2007-01-01 00:00:00	110370	6838937	Mila domovino	Sofija	Republika Bugarska
BEL	Belgija	1958-01-01 00:00:00	30528	11631136	Pjesma o Brabantu	Bruxelles	Kraljevina Belgija
CZE	Češka	2004-05-01 00:00:00	78868	10516707	Gdje moj je dom	Prag	Češka Republika
CYP	Cipar	2004-05-01 00:00:00	9251	904705	Himna slobodi	Nikozija	Republika Cipar
DNK	Danska	1973-01-01 00:00:00	42924	5873420	Ima jedna divna zemlja	Kopenhagen	Kraljevina Danska
EST	Estonija	2004-05-01 00:00:00	45227	1331796	Moja domovina, moja sreća i radost	Tallinn	Republika Estonija
FIN	Finska	1995-01-01 00:00:00	338440	5548241	Naša zemlja	Helsinki	Republika Finska
FRA	Francuska	1958-01-01 00:00:00	633186	67842582	Marseljeza	Pariz	Francuska Republika
GRC	Grčka	1981-01-01 00:00:00	132049	10603810	Himna slobodi	Atena	Helenska Republika
HRV	Hrvatska	2013-07-01 00:00:00	56594	3879074	Lijepa naša domovino	Zagreb	Republika Hrvatska
IRL	Irska	1973-01-01 00:00:00	69797	5060005	Vojnikova pjesma	Dublin	Irska
ITA	Italija	1958-01-01 00:00:00	302073	58983122	Pjev Talijana	Rim	Talijanska Republika
LVA	Latvija	2004-05-01 00:00:00	64573	1875757	Bože, blagoslovi Latviju!	Riga	Republika Latvija
LTU	Litva	2004-05-01 00:00:00	65286	2805998	Nacionalna himna	Vilnius	Republika Litva
LUX	Luksemburg	1958-01-01 00:00:00	2586	645397	Naša domovina	Luxembourg	Veliko Vojvodstvo Luksemburg
HUN	Mađarska	2004-05-01 00:00:00	93011	9689010	Bože, blagoslovi Mađare	Budimpešta	Mađarska
MLT	Malta	2004-05-01 00:00:00	315.4	520971	Malteška himna	Valletta	Republika Malta
NLD	Nizozemska	1958-01-01 00:00:00	41540	17590672	Wilhelmus	Amsterdam	Nizozemska
DEU	Njemačka	1958-01-01 00:00:00	357376	83237124	Pjesma Nijemaca	Berlin	Savezna Republika Njemačka
POL	Poljska	2004-05-01 00:00:00	312679	37654247	Mazurek Dąbrowskiego	Varšava	Republika Poljska
PRT	Portugal	1986-01-01 00:00:00	92226	10352042	Portugalska	Lisabon	Portugalska Republika
ROU	Rumunjska	2007-01-01 00:00:00	238390	19038098	Probudite se, Rumunji	Bukurešt	Rumunjska
SVK	Slovačka	2004-05-01 00:00:00	49035	5434712	Nad Tatrama sijeva	Bratislava	Slovačka Republika
SVN	Slovenija	2004-05-01 00:00:00	20273	2107180	Zdravljica	Ljubljana	Republika Slovenija
ESP	Španjolska	1986-01-01 00:00:00	505944	47432805	Kraljevski marš	Madrid	Kraljevina Španjolska
SWE	Švedska	1995-01-01 00:00:00	438574	10452326	Ti stari, ti slobodni	Stockholm	Kraljevina Švedska
\.


--
-- Data for Name: drzavaJezik; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public."drzavaJezik" ("ISOoznDrz", "ISOoznJez") FROM stdin;
AUT	de
BEL	nl
BEL	fr
BEL	de
BGR	bg
CZE	cs
CYP	el
DNK	da
EST	et
FIN	fi
FIN	sv
FRA	fr
GRC	el
HRV	hr
IRL	ga
IRL	en
ITA	it
LVA	lv
LTU	lt
LUX	fr
LUX	de
HUN	hu
MLT	mt
MLT	en
NLD	nl
DEU	de
POL	pl
PRT	pt
ROU	ro
SVK	sk
SVN	sl
ESP	es
SWE	sv
\.


--
-- Data for Name: drzavaValuta; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public."drzavaValuta" ("ISOoznDrz", "ISOoznVal") FROM stdin;
AUT	EUR
BEL	EUR
BGR	BGN
CZE	CZK
CYP	EUR
DNK	DKK
EST	EUR
FIN	EUR
FRA	EUR
GRC	EUR
HRV	HRK
IRL	EUR
ITA	EUR
LVA	EUR
LTU	EUR
LUX	EUR
HUN	HUF
MLT	EUR
NLD	EUR
DEU	EUR
POL	PLN
PRT	EUR
ROU	RON
SVK	EUR
SVN	EUR
ESP	EUR
SWE	SEK
\.


--
-- Data for Name: drzavniVrh; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public."drzavniVrh" ("punoIme", "ISOoznDrz", uloga) FROM stdin;
Alexander Van der Bellen	AUT	predsjednik
Filip Leopold Lodewijk Maria	BEL	kralj
Rumen Radev	BGR	predsjednik
Miloš Zeman	CZE	predsjednik
Nikos Anastasiades	CYP	predsjednik
Kersti Kaljulaid	EST	predsjednik
Sauli Niinistö	FIN	predsjednik
Emmanuel Macron	FRA	predsjednik
Margareta II.	DNK	kralj
Katerina Sakellaropoulou	GRC	predsjednik
Zoran Milanović	HRV	predsjednik
Michael Daniel Higgins	IRL	predsjednik
Sergio Mattarella	ITA	predsjednik
Egils Levits	LVA	predsjednik
Gitanas Nausėda	LTU	predsjednik
Henri Luksemburški	LUX	veliki vojvoda
Katalin Novák	HUN	predsjednik
George Vella	MLT	predsjednik
Willem-Alexander	NLD	kralj
Frank-Walter Steinmeier	DEU	predsjednik
Andrzej Duda	POL	predsjednik
Marcelo Rebelo de Sousa	PRT	predsjednik
Klaus Iohannis	ROU	predsjednik
Zuzana Čaputová	SVK	predsjednik
Borut Pahor	SVN	predsjednik
Felipe VI.	ESP	kralj
Carl XVI. Gustaf	SWE	kralj
\.


--
-- Data for Name: jezik; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.jezik ("ISOozn", naziv) FROM stdin;
de	njemački
nl	nizozemski
fr	francuski
bg	bugarski
cs	češki
el	grčki
da	danski
et	estonski
fi	finski
sv	švedski
hr	hrvatski
ga	irski
en	engleski
it	talijanski
lv	latvijski
lt	litavski
hu	mađarski
mt	malteški
pl	poljski
pt	portugalski
ro	rumunjski
sk	slovački
sl	slovenski
es	španjolski
\.


--
-- Data for Name: valuta; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.valuta ("ISOozn", naziv) FROM stdin;
EUR	euro
BGN	bugarski lev
CZK	češka kruna
DKK	danska kruna
HRK	hrvatska kuna
HUF	mađarska forinta
PLN	poljski zloti
RON	rumunjski leu
SEK	švedska kruna
\.


--
-- Name: drzavaJezik drzJezPK; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."drzavaJezik"
    ADD CONSTRAINT "drzJezPK" PRIMARY KEY ("ISOoznDrz", "ISOoznJez");


--
-- Name: drzavaValuta drzValPK; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."drzavaValuta"
    ADD CONSTRAINT "drzValPK" PRIMARY KEY ("ISOoznDrz", "ISOoznVal");


--
-- Name: drzava drzava_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.drzava
    ADD CONSTRAINT drzava_pkey PRIMARY KEY ("ISOozn");


--
-- Name: jezik jezik_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.jezik
    ADD CONSTRAINT jezik_pkey PRIMARY KEY ("ISOozn");


--
-- Name: drzavniVrh predsjednikPK; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."drzavniVrh"
    ADD CONSTRAINT "predsjednikPK" PRIMARY KEY ("ISOoznDrz");


--
-- Name: valuta valuta_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.valuta
    ADD CONSTRAINT valuta_pkey PRIMARY KEY ("ISOozn");


--
-- Name: drzavaValuta drzFK; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."drzavaValuta"
    ADD CONSTRAINT "drzFK" FOREIGN KEY ("ISOoznDrz") REFERENCES public.drzava("ISOozn");


--
-- Name: drzavaJezik drzFK; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."drzavaJezik"
    ADD CONSTRAINT "drzFK" FOREIGN KEY ("ISOoznDrz") REFERENCES public.drzava("ISOozn");


--
-- Name: drzavaJezik jezFK; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."drzavaJezik"
    ADD CONSTRAINT "jezFK" FOREIGN KEY ("ISOoznJez") REFERENCES public.jezik("ISOozn");


--
-- Name: drzavniVrh predsjednikDrzavaFK; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."drzavniVrh"
    ADD CONSTRAINT "predsjednikDrzavaFK" FOREIGN KEY ("ISOoznDrz") REFERENCES public.drzava("ISOozn");


--
-- Name: drzavaValuta valFK; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."drzavaValuta"
    ADD CONSTRAINT "valFK" FOREIGN KEY ("ISOoznVal") REFERENCES public.valuta("ISOozn");


--
-- PostgreSQL database dump complete
--

