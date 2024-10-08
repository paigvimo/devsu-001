--
-- PostgreSQL database dump
--

-- Dumped from database version 16.4 (Debian 16.4-1.pgdg120+1)
-- Dumped by pg_dump version 16.4 (Debian 16.4-1.pgdg120+1)

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
-- Name: tcustomer; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.tcustomer (
    gender character(1) NOT NULL,
    status character varying(3) NOT NULL,
    birthdate timestamp(6) without time zone NOT NULL,
    person_id bigint NOT NULL,
    identification_number character varying(20) NOT NULL,
    telephone character varying(20) NOT NULL,
    address character varying(250) NOT NULL,
    name character varying(300),
    password character varying(3000) NOT NULL,
    CONSTRAINT tcustomer_gender_check CHECK ((gender = ANY (ARRAY['M'::bpchar, 'F'::bpchar]))),
    CONSTRAINT tcustomer_status_check CHECK (((status)::text = ANY ((ARRAY['ACT'::character varying, 'INA'::character varying, 'BLK'::character varying, 'DEL'::character varying])::text[])))
);


ALTER TABLE public.tcustomer OWNER TO postgres;

--
-- Name: tcustomer_person_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.tcustomer ALTER COLUMN person_id ADD GENERATED BY DEFAULT AS IDENTITY (
    SEQUENCE NAME public.tcustomer_person_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- Data for Name: tcustomer; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.tcustomer (gender, status, birthdate, person_id, identification_number, telephone, address, name, password) FROM stdin;
F	ACT	1988-12-05 00:00:00	2	2	555-2222	AVENIDA CENTRAL 100	ANA GARCÍA	aPass.5678
M	ACT	1975-03-22 00:00:00	3	3	555-3333	CALLE 3 12-45	JORGE MARTÍNEZ	bPass.1234
M	DEL	1993-07-18 00:00:00	1	1	555-1111	CALLE 1 5-00 Y CALLE 2	PAUL VIDAL	aPass.1234
\.


--
-- Name: tcustomer_person_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.tcustomer_person_id_seq', 3, true);


--
-- Name: tcustomer tcustomer_identification_number_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tcustomer
    ADD CONSTRAINT tcustomer_identification_number_key UNIQUE (identification_number);


--
-- Name: tcustomer tcustomer_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tcustomer
    ADD CONSTRAINT tcustomer_pkey PRIMARY KEY (person_id);


--
-- PostgreSQL database dump complete
--

