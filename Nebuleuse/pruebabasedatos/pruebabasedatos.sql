--
-- PostgreSQL database dump
--

-- Dumped from database version 9.5.4
-- Dumped by pg_dump version 9.5.4

-- Started on 2016-09-22 12:38:19 PYT

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 1 (class 3079 OID 12435)
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- TOC entry 2186 (class 0 OID 0)
-- Dependencies: 1
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 182 (class 1259 OID 16720)
-- Name: tabla01; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE tabla01 (
    id integer NOT NULL,
    numerico integer,
    caracter character varying
);


ALTER TABLE tabla01 OWNER TO postgres;

--
-- TOC entry 181 (class 1259 OID 16718)
-- Name: tabla01_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE tabla01_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE tabla01_id_seq OWNER TO postgres;

--
-- TOC entry 2187 (class 0 OID 0)
-- Dependencies: 181
-- Name: tabla01_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE tabla01_id_seq OWNED BY tabla01.id;


--
-- TOC entry 2060 (class 2604 OID 16723)
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY tabla01 ALTER COLUMN id SET DEFAULT nextval('tabla01_id_seq'::regclass);


--
-- TOC entry 2178 (class 0 OID 16720)
-- Dependencies: 182
-- Data for Name: tabla01; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY tabla01 (id, numerico, caracter) FROM stdin;
\.


--
-- TOC entry 2188 (class 0 OID 0)
-- Dependencies: 181
-- Name: tabla01_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('tabla01_id_seq', 1, false);


--
-- TOC entry 2062 (class 2606 OID 16728)
-- Name: tabla01_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY tabla01
    ADD CONSTRAINT tabla01_pkey PRIMARY KEY (id);


--
-- TOC entry 2185 (class 0 OID 0)
-- Dependencies: 6
-- Name: public; Type: ACL; Schema: -; Owner: postgres
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM postgres;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO PUBLIC;


-- Completed on 2016-09-22 12:38:21 PYT

--
-- PostgreSQL database dump complete
--

