--
-- PostgreSQL database dump
--

-- Dumped from database version 9.6.8
-- Dumped by pg_dump version 9.6.8

-- Started on 2018-04-05 00:19:47

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 1 (class 3079 OID 12387)
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- TOC entry 3214 (class 0 OID 0)
-- Dependencies: 1
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


--
-- TOC entry 22 (class 3079 OID 22060)
-- Name: btree_gin; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS btree_gin WITH SCHEMA public;


--
-- TOC entry 3215 (class 0 OID 0)
-- Dependencies: 22
-- Name: EXTENSION btree_gin; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION btree_gin IS 'support for indexing common datatypes in GIN';


--
-- TOC entry 21 (class 3079 OID 22405)
-- Name: btree_gist; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS btree_gist WITH SCHEMA public;


--
-- TOC entry 3216 (class 0 OID 0)
-- Dependencies: 21
-- Name: EXTENSION btree_gist; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION btree_gist IS 'support for indexing common datatypes in GiST';


--
-- TOC entry 20 (class 3079 OID 22959)
-- Name: citext; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS citext WITH SCHEMA public;


--
-- TOC entry 3217 (class 0 OID 0)
-- Dependencies: 20
-- Name: EXTENSION citext; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION citext IS 'data type for case-insensitive character strings';


--
-- TOC entry 19 (class 3079 OID 23043)
-- Name: cube; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS cube WITH SCHEMA public;


--
-- TOC entry 3218 (class 0 OID 0)
-- Dependencies: 19
-- Name: EXTENSION cube; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION cube IS 'data type for multidimensional cubes';


--
-- TOC entry 18 (class 3079 OID 23130)
-- Name: dblink; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS dblink WITH SCHEMA public;


--
-- TOC entry 3219 (class 0 OID 0)
-- Dependencies: 18
-- Name: EXTENSION dblink; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION dblink IS 'connect to other PostgreSQL databases from within a database';


--
-- TOC entry 17 (class 3079 OID 23176)
-- Name: dict_int; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS dict_int WITH SCHEMA public;


--
-- TOC entry 3220 (class 0 OID 0)
-- Dependencies: 17
-- Name: EXTENSION dict_int; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION dict_int IS 'text search dictionary template for integers';


--
-- TOC entry 16 (class 3079 OID 23181)
-- Name: dict_xsyn; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS dict_xsyn WITH SCHEMA public;


--
-- TOC entry 3221 (class 0 OID 0)
-- Dependencies: 16
-- Name: EXTENSION dict_xsyn; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION dict_xsyn IS 'text search dictionary template for extended synonym processing';


--
-- TOC entry 15 (class 3079 OID 23186)
-- Name: earthdistance; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS earthdistance WITH SCHEMA public;


--
-- TOC entry 3222 (class 0 OID 0)
-- Dependencies: 15
-- Name: EXTENSION earthdistance; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION earthdistance IS 'calculate great-circle distances on the surface of the Earth';


--
-- TOC entry 14 (class 3079 OID 23201)
-- Name: fuzzystrmatch; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS fuzzystrmatch WITH SCHEMA public;


--
-- TOC entry 3223 (class 0 OID 0)
-- Dependencies: 14
-- Name: EXTENSION fuzzystrmatch; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION fuzzystrmatch IS 'determine similarities and distance between strings';


--
-- TOC entry 13 (class 3079 OID 23212)
-- Name: hstore; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS hstore WITH SCHEMA public;


--
-- TOC entry 3224 (class 0 OID 0)
-- Dependencies: 13
-- Name: EXTENSION hstore; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION hstore IS 'data type for storing sets of (key, value) pairs';


--
-- TOC entry 12 (class 3079 OID 23335)
-- Name: intarray; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS intarray WITH SCHEMA public;


--
-- TOC entry 3225 (class 0 OID 0)
-- Dependencies: 12
-- Name: EXTENSION intarray; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION intarray IS 'functions, operators, and index support for 1-D arrays of integers';


--
-- TOC entry 11 (class 3079 OID 23453)
-- Name: ltree; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS ltree WITH SCHEMA public;


--
-- TOC entry 3226 (class 0 OID 0)
-- Dependencies: 11
-- Name: EXTENSION ltree; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION ltree IS 'data type for hierarchical tree-like structures';


--
-- TOC entry 10 (class 3079 OID 23628)
-- Name: pg_stat_statements; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS pg_stat_statements WITH SCHEMA public;


--
-- TOC entry 3227 (class 0 OID 0)
-- Dependencies: 10
-- Name: EXTENSION pg_stat_statements; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION pg_stat_statements IS 'track execution statistics of all SQLQueries statements executed';


--
-- TOC entry 9 (class 3079 OID 23635)
-- Name: pg_trgm; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS pg_trgm WITH SCHEMA public;


--
-- TOC entry 3228 (class 0 OID 0)
-- Dependencies: 9
-- Name: EXTENSION pg_trgm; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION pg_trgm IS 'text similarity measurement and index searching based on trigrams';


--
-- TOC entry 8 (class 3079 OID 23700)
-- Name: pgcrypto; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS pgcrypto WITH SCHEMA public;


--
-- TOC entry 3229 (class 0 OID 0)
-- Dependencies: 8
-- Name: EXTENSION pgcrypto; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION pgcrypto IS 'cryptographic functions';


--
-- TOC entry 7 (class 3079 OID 23737)
-- Name: pgrowlocks; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS pgrowlocks WITH SCHEMA public;


--
-- TOC entry 3230 (class 0 OID 0)
-- Dependencies: 7
-- Name: EXTENSION pgrowlocks; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION pgrowlocks IS 'show row-level locking information';


--
-- TOC entry 6 (class 3079 OID 23739)
-- Name: pgstattuple; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS pgstattuple WITH SCHEMA public;


--
-- TOC entry 3231 (class 0 OID 0)
-- Dependencies: 6
-- Name: EXTENSION pgstattuple; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION pgstattuple IS 'show tuple-level statistics';


--
-- TOC entry 5 (class 3079 OID 23748)
-- Name: tablefunc; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS tablefunc WITH SCHEMA public;


--
-- TOC entry 3232 (class 0 OID 0)
-- Dependencies: 5
-- Name: EXTENSION tablefunc; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION tablefunc IS 'functions that manipulate whole tables, including crosstab';


--
-- TOC entry 4 (class 3079 OID 23769)
-- Name: unaccent; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS unaccent WITH SCHEMA public;


--
-- TOC entry 3233 (class 0 OID 0)
-- Dependencies: 4
-- Name: EXTENSION unaccent; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION unaccent IS 'text search dictionary that removes accents';


--
-- TOC entry 3 (class 3079 OID 23776)
-- Name: uuid-ossp; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS "uuid-ossp" WITH SCHEMA public;


--
-- TOC entry 3234 (class 0 OID 0)
-- Dependencies: 3
-- Name: EXTENSION "uuid-ossp"; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION "uuid-ossp" IS 'generate universally unique identifiers (UUIDs)';


--
-- TOC entry 2 (class 3079 OID 23787)
-- Name: xml2; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS xml2 WITH SCHEMA public;


--
-- TOC entry 3235 (class 0 OID 0)
-- Dependencies: 2
-- Name: EXTENSION xml2; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION xml2 IS 'XPath querying and XSLT';


SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 211 (class 1259 OID 23801)
-- Name: attr_value; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.attr_value (
    id uuid DEFAULT public.uuid_generate_v4() NOT NULL,
    obj_id uuid,
    attr_id uuid,
    value character varying(255)
);


ALTER TABLE public.attr_value OWNER TO postgres;

--
-- TOC entry 212 (class 1259 OID 23805)
-- Name: attributes; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.attributes (
    id uuid DEFAULT public.uuid_generate_v4() NOT NULL,
    obj_type_id uuid,
    attr_name character varying(32)
);


ALTER TABLE public.attributes OWNER TO postgres;

--
-- TOC entry 213 (class 1259 OID 23809)
-- Name: obj_type; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.obj_type (
    id uuid DEFAULT public.uuid_generate_v4() NOT NULL,
    type_name character varying(32)
);


ALTER TABLE public.obj_type OWNER TO postgres;

--
-- TOC entry 214 (class 1259 OID 23813)
-- Name: objects; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.objects (
    id uuid DEFAULT public.uuid_generate_v4() NOT NULL,
    type_id uuid
);


ALTER TABLE public.objects OWNER TO postgres;

--
-- TOC entry 215 (class 1259 OID 23817)
-- Name: user_roles; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.user_roles (
    role_id integer NOT NULL,
    role_name character varying(32)
);


ALTER TABLE public.user_roles OWNER TO postgres;

--
-- TOC entry 216 (class 1259 OID 23820)
-- Name: user_roles_role_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.user_roles_role_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.user_roles_role_id_seq OWNER TO postgres;

--
-- TOC entry 3236 (class 0 OID 0)
-- Dependencies: 216
-- Name: user_roles_role_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.user_roles_role_id_seq OWNED BY public.user_roles.role_id;


--
-- TOC entry 217 (class 1259 OID 23822)
-- Name: users; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.users (
    username character varying(32) NOT NULL,
    role_id integer,
    user_id uuid,
    password character varying(128)
);


ALTER TABLE public.users OWNER TO postgres;

--
-- TOC entry 3057 (class 2604 OID 23825)
-- Name: user_roles role_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_roles ALTER COLUMN role_id SET DEFAULT nextval('public.user_roles_role_id_seq'::regclass);


--
-- TOC entry 3200 (class 0 OID 23801)
-- Dependencies: 211
-- Data for Name: attr_value; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.attr_value (id, obj_id, attr_id, value) FROM stdin;
cc618799-51d6-4062-a2cc-6313168cebd5	023f8169-df80-4043-99d1-a4bea31a6827	01c0d554-9acd-453c-8ef7-9c68bbb44dbe	Marty McFly
a1076c0f-b9a0-4392-a4ee-039d0562239a	023f8169-df80-4043-99d1-a4bea31a6827	c2b9a488-357a-4a76-adf2-f3ddd576aba0	marty@gmail.com
ccf4780d-0597-47dd-af3e-d71ff1e60bcc	f77d8d83-e378-47d1-a654-17fd5092c9b6	01c0d554-9acd-453c-8ef7-9c68bbb44dbe	Emmett Brown
95de40dc-42ae-4a95-8b7e-0957acb2f8ab	f77d8d83-e378-47d1-a654-17fd5092c9b6	c2b9a488-357a-4a76-adf2-f3ddd576aba0	emmett@gmail.com
a6ce0969-b517-4ce0-a430-8635d28ac11e	6c94efe3-626c-4da5-ba89-1580c8437f65	f55e93f6-cde8-4bd3-a78f-20d139a0838e	OPENED
1ffd4a12-b7c4-49da-8097-0134dbb8e82d	6c94efe3-626c-4da5-ba89-1580c8437f65	250dffc2-8b90-4a8a-8a40-8a686b2a917b	2.06.2018
8ee38f83-1801-4ac5-88d6-d4a2d3f110c3	9a68ad15-7295-45f1-91dd-6b0c3fd36d21	f23b33c0-0416-4e62-a843-73754797fed4	6c94efe3-626c-4da5-ba89-1580c8437f65
0250a3c6-60ea-47ec-bc46-5490818e6c45	9a68ad15-7295-45f1-91dd-6b0c3fd36d21	7f1bb07c-fe73-4bac-938d-e5c242257e9d	023f8169-df80-4043-99d1-a4bea31a6827
d89453cc-7c71-4772-988b-ad850c645905	9a68ad15-7295-45f1-91dd-6b0c3fd36d21	adfb83e0-478f-45e1-8d88-f38e5068b6b7	cool 
5e5239ed-9371-4a7e-a06b-64b6a8213a8d	e409b8a7-338f-42b8-82ad-fbfcf2d404db	f23b33c0-0416-4e62-a843-73754797fed4	6c94efe3-626c-4da5-ba89-1580c8437f65
dcbcf69e-d867-4185-8be4-5955dd8223a8	e409b8a7-338f-42b8-82ad-fbfcf2d404db	7f1bb07c-fe73-4bac-938d-e5c242257e9d	f77d8d83-e378-47d1-a654-17fd5092c9b6
4eb41b8b-1afa-4091-8816-1bdb0b08c21f	e409b8a7-338f-42b8-82ad-fbfcf2d404db	adfb83e0-478f-45e1-8d88-f38e5068b6b7	it 
\.


--
-- TOC entry 3201 (class 0 OID 23805)
-- Dependencies: 212
-- Data for Name: attributes; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.attributes (id, obj_type_id, attr_name) FROM stdin;
01c0d554-9acd-453c-8ef7-9c68bbb44dbe	4f589c55-b50d-4846-bbf0-736c42c46f6e	fullname
c2b9a488-357a-4a76-adf2-f3ddd576aba0	4f589c55-b50d-4846-bbf0-736c42c46f6e	email
f23b33c0-0416-4e62-a843-73754797fed4	79d53305-a108-41f5-8ce3-8e259bb6bba8	match_id
7f1bb07c-fe73-4bac-938d-e5c242257e9d	79d53305-a108-41f5-8ce3-8e259bb6bba8	user_id
adfb83e0-478f-45e1-8d88-f38e5068b6b7	79d53305-a108-41f5-8ce3-8e259bb6bba8	message
250dffc2-8b90-4a8a-8a40-8a686b2a917b	253c101a-69df-43d1-acda-152834064c75	match_date
f55e93f6-cde8-4bd3-a78f-20d139a0838e	253c101a-69df-43d1-acda-152834064c75	match_state
\.


--
-- TOC entry 3202 (class 0 OID 23809)
-- Dependencies: 213
-- Data for Name: obj_type; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.obj_type (id, type_name) FROM stdin;
4f589c55-b50d-4846-bbf0-736c42c46f6e	user
79d53305-a108-41f5-8ce3-8e259bb6bba8	comment
253c101a-69df-43d1-acda-152834064c75	match
\.


--
-- TOC entry 3203 (class 0 OID 23813)
-- Dependencies: 214
-- Data for Name: objects; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.objects (id, type_id) FROM stdin;
023f8169-df80-4043-99d1-a4bea31a6827	4f589c55-b50d-4846-bbf0-736c42c46f6e
f77d8d83-e378-47d1-a654-17fd5092c9b6	4f589c55-b50d-4846-bbf0-736c42c46f6e
6c94efe3-626c-4da5-ba89-1580c8437f65	253c101a-69df-43d1-acda-152834064c75
9a68ad15-7295-45f1-91dd-6b0c3fd36d21	79d53305-a108-41f5-8ce3-8e259bb6bba8
e409b8a7-338f-42b8-82ad-fbfcf2d404db	79d53305-a108-41f5-8ce3-8e259bb6bba8
\.


--
-- TOC entry 3204 (class 0 OID 23817)
-- Dependencies: 215
-- Data for Name: user_roles; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.user_roles (role_id, role_name) FROM stdin;
1	ROLE_ADMIN
2	ROLE_USER
\.


--
-- TOC entry 3237 (class 0 OID 0)
-- Dependencies: 216
-- Name: user_roles_role_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.user_roles_role_id_seq', 3, true);


--
-- TOC entry 3206 (class 0 OID 23822)
-- Dependencies: 217
-- Data for Name: users; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.users (username, role_id, user_id, password) FROM stdin;
marty68	1	023f8169-df80-4043-99d1-a4bea31a6827	$2a$10$Ka6TSQZ3VzTuN0afojEuNuQ2/LDeEwxzUo2c86Coh54iFwXtzCyUu
emmett20	2	f77d8d83-e378-47d1-a654-17fd5092c9b6	$2a$10$Vqv3paA25uD1geXX7e6XnuNhXo2qvDxs/EI.Oavj4jdYC3CW9SB2W
\.


--
-- TOC entry 3059 (class 2606 OID 23827)
-- Name: attr_value attr_value_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.attr_value
    ADD CONSTRAINT attr_value_pkey PRIMARY KEY (id);


--
-- TOC entry 3063 (class 2606 OID 23829)
-- Name: attributes attributes_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.attributes
    ADD CONSTRAINT attributes_pkey PRIMARY KEY (id);


--
-- TOC entry 3066 (class 2606 OID 23831)
-- Name: obj_type obj_type_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.obj_type
    ADD CONSTRAINT obj_type_pkey PRIMARY KEY (id);


--
-- TOC entry 3069 (class 2606 OID 23833)
-- Name: objects objects_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.objects
    ADD CONSTRAINT objects_pkey PRIMARY KEY (id);


--
-- TOC entry 3071 (class 2606 OID 23835)
-- Name: user_roles user_roles_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_roles
    ADD CONSTRAINT user_roles_pkey PRIMARY KEY (role_id);


--
-- TOC entry 3075 (class 2606 OID 23837)
-- Name: users users_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_pkey PRIMARY KEY (username);


--
-- TOC entry 3060 (class 1259 OID 23838)
-- Name: fki_attr_value_attribute_id_fkey; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX fki_attr_value_attribute_id_fkey ON public.attr_value USING btree (attr_id);


--
-- TOC entry 3061 (class 1259 OID 23839)
-- Name: fki_attr_value_obj_id_fkey; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX fki_attr_value_obj_id_fkey ON public.attr_value USING btree (obj_id);


--
-- TOC entry 3064 (class 1259 OID 23840)
-- Name: fki_attributes_obj_type_id_fkey; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX fki_attributes_obj_type_id_fkey ON public.attributes USING btree (obj_type_id);


--
-- TOC entry 3067 (class 1259 OID 23841)
-- Name: fki_type_id_fkey; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX fki_type_id_fkey ON public.objects USING btree (type_id);


--
-- TOC entry 3072 (class 1259 OID 23842)
-- Name: fki_users_role_id_fkey; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX fki_users_role_id_fkey ON public.users USING btree (role_id);


--
-- TOC entry 3073 (class 1259 OID 23843)
-- Name: fki_users_user_id_fkey; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX fki_users_user_id_fkey ON public.users USING btree (user_id);


--
-- TOC entry 3076 (class 2606 OID 23844)
-- Name: attr_value attr_value_attribute_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.attr_value
    ADD CONSTRAINT attr_value_attribute_id_fkey FOREIGN KEY (attr_id) REFERENCES public.attributes(id) ON DELETE CASCADE;


--
-- TOC entry 3077 (class 2606 OID 23849)
-- Name: attr_value attr_value_obj_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.attr_value
    ADD CONSTRAINT attr_value_obj_id_fkey FOREIGN KEY (obj_id) REFERENCES public.objects(id) ON DELETE CASCADE;


--
-- TOC entry 3078 (class 2606 OID 23854)
-- Name: attributes attributes_obj_type_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.attributes
    ADD CONSTRAINT attributes_obj_type_id_fkey FOREIGN KEY (obj_type_id) REFERENCES public.obj_type(id) ON DELETE CASCADE;


--
-- TOC entry 3079 (class 2606 OID 23859)
-- Name: objects type_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.objects
    ADD CONSTRAINT type_id_fkey FOREIGN KEY (type_id) REFERENCES public.obj_type(id) ON DELETE CASCADE;


--
-- TOC entry 3080 (class 2606 OID 23864)
-- Name: users users_role_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_role_id_fkey FOREIGN KEY (role_id) REFERENCES public.user_roles(role_id) ON DELETE CASCADE;


--
-- TOC entry 3081 (class 2606 OID 23869)
-- Name: users users_user_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_user_id_fkey FOREIGN KEY (user_id) REFERENCES public.objects(id) ON DELETE CASCADE;


-- Completed on 2018-04-05 00:19:51

--
-- PostgreSQL database dump complete
--

