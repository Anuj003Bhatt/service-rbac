--
-- PostgreSQL database dump
--

-- Dumped from database version 16.0 (Debian 16.0-1.pgdg120+1)
-- Dumped by pg_dump version 16.0

-- Started on 2023-11-06 08:42:11 UTC

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

--
-- TOC entry 3415 (class 0 OID 16388)
-- Dependencies: 215
-- Data for Name: permissions; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.permissions VALUES ('9cee0dd0-a439-4217-aaa4-2b84cb2b6f27', 'C', 'Rule a kingdom', 'Rule');
INSERT INTO public.permissions VALUES ('5c8e77d7-46d1-4369-bffe-5e069e3af8f7', 'C', 'Declare a war', 'War');
INSERT INTO public.permissions VALUES ('8ea4ebd5-df7f-40b1-b1f7-b9741300f006', 'R', 'Fight a war', 'Fight in war');
INSERT INTO public.permissions VALUES ('f9b5026e-3b3f-40c6-9667-54082670bcca', 'C', 'Advise king or queen', 'Advise');


--
-- TOC entry 3416 (class 0 OID 16395)
-- Dependencies: 216
-- Data for Name: platform_users; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.platform_users VALUES ('4d516d92-6f8f-451c-bb9d-35946b6c44e9', 'bqiWpuUlT0ZpPzuue2JqppNty0WynHB4P42Jskb+7P0=', 'Jon Snow', '{"salt": "java.util.stream.SliceOps$1@3a373456", "value": "aVtUPBDnWrWphUJ4JN76CkAMhcYiDYcqxyxx6F2/2HE="}', '0/mbBxfKAa4yyomWMEzGCA==', true, 'jon.snow');
INSERT INTO public.platform_users VALUES ('6f8c3b59-7e5d-4900-b25b-fb186d210ffc', 'GyJ9gmMsrAiZiW8dHhw9dzUfr2heZZB1HxEEl88QQ/E=', 'Sansa Stark', '{"salt": "java.util.stream.SliceOps$1@480e48d5", "value": "oDv9TS4OsEQ0K4YX7SDZU4hwX4cZj0adXrkcYTzv5EI="}', 'dNKhe96/cZASQ5pnDPDNUw==', true, 'sansa.stark');
INSERT INTO public.platform_users VALUES ('b7129331-be6b-4a74-b6c8-e2acef9ba721', 'SAd3bFSVQd8LKzbd0hFV9odLVOyQjHzSrSb0WkHKpus=', 'Arya Stark', '{"salt": "java.util.stream.SliceOps$1@2bd9829c", "value": "s5pex7JeIhoLll5uKqEaZ+jwVlYU4TZFMVv5ZvzDWSo="}', 'acs6ic83Jq0Wxl5hy79npg==', true, 'arya.stark');
INSERT INTO public.platform_users VALUES ('aadce704-9fee-4353-aadf-1548032fa746', 'Q+EN7sqGq90P5OdOn2xA3KVJ36QBR63WEvgUYkjyu0E=', 'Daenerys Targaryen', '{"salt": "java.util.stream.SliceOps$1@24e4e22b", "value": "qchFNchrveCsJgGG2jB6gRtAR+Z0uCbJV6AUAY6C0S0="}', '4opjTcUetQhZVFLS7U/wMQ==', true, 'daenerys.targaryen');
INSERT INTO public.platform_users VALUES ('268046a8-fed3-496f-adf8-cde57677de04', 'lsXyT7E4L2P89mhJqLT5DfYbsPP6IPu4C5bkrR+nGAU=', 'Cersei Lannister', '{"salt": "java.util.stream.SliceOps$1@4b587386", "value": "Qe9U7FiZh1P6XWgZ5/xF3V2xtLoJH0Sd9kN3+Zu7f3c="}', 'Y/7v5RuRvrUx1PVuYYPsMQ==', true, 'cersei.lannister');
INSERT INTO public.platform_users VALUES ('63d0ee98-5a5d-486f-8cf4-9cee13d67cb9', 'ErIK8EwgSeWgikahNWyBsPYbsPP6IPu4C5bkrR+nGAU=', 'Tyrion Lannister', '{"salt": "java.util.stream.SliceOps$1@1fe09194", "value": "QnUXd8fuYS2fxACG2d3BxjTS6UvJ5uDhHhmaMz2nAYU="}', 'E66tqUy5qDwye1/P0lZ6xQ==', true, 'tyrion.lannister');
INSERT INTO public.platform_users VALUES ('a983c029-b991-4415-bf46-50410f610849', 'UHjNOjo6+frJjMdwTgzijh7Dutkuc2Avayge+HBd+gY=', 'Eddard Stark', '{"salt": "java.util.stream.SliceOps$1@1d2a95ad", "value": "0UVyS4LbIuO0ULTXG1HJRYczgooCLBAi1BWFdQmBK90="}', 'CT4icihJ8B/TjK4qIvAfkQ==', true, 'eddard.stark');
INSERT INTO public.platform_users VALUES ('90b2a88a-c7a6-4e25-bee3-f1e832c4f3d0', 'TL/DDRZmxOTRQl0WJ2Yxlh7Dutkuc2Avayge+HBd+gY=', 'Lyanna Stark', '{"salt": "java.util.stream.SliceOps$1@313b4359", "value": "GkZXUn3nv2MN5d4UIl57cZkQqWlRcvSM6WWfq05Cbz8="}', 'v0ODFbaCoAwgdZA1JlPCcA==', true, 'lyanna.stark');


--
-- TOC entry 3418 (class 0 OID 16405)
-- Dependencies: 218
-- Data for Name: role_groups; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3420 (class 0 OID 16415)
-- Dependencies: 220
-- Data for Name: roles; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.roles VALUES ('cb49f08d-04c7-4f01-88c7-3efaec9519f2', 'King of Winterfell', 'King in the North');
INSERT INTO public.roles VALUES ('89039cd4-644b-46f3-93c7-8b6d5371f25a', 'Queen at King''s Landing', 'Queen of Seven Kingdoms');
INSERT INTO public.roles VALUES ('6129ae17-1d93-4e76-ab57-91304437ab55', 'Hand of the Queen', 'Hand of the Queen');
INSERT INTO public.roles VALUES ('320cb516-ef4c-484c-a808-e3a79ef9078f', 'Hand of the King', 'Hand of the King');
INSERT INTO public.roles VALUES ('821167f2-4794-437e-9c35-5155fe2f331a', 'Lady of Winterfell', 'Lady of Winterfell');
INSERT INTO public.roles VALUES ('e7645a4b-87a6-4cca-bdb0-e14390845069', 'Lord commander of the night''s watch', 'Lord Commander');


--
-- TOC entry 3417 (class 0 OID 16402)
-- Dependencies: 217
-- Data for Name: role_group_roles; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3419 (class 0 OID 16412)
-- Dependencies: 219
-- Data for Name: role_permission_associations; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3423 (class 0 OID 16428)
-- Dependencies: 223
-- Data for Name: user_groups; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.user_groups VALUES ('f931bfbd-0d30-42e5-b327-839d68cb1a2a', 'Starks of Winterfell', 'Starks');
INSERT INTO public.user_groups VALUES ('f4d24577-83d7-4ff1-8dad-56c3d0808697', 'Lannisters of Casterly Rock', 'Lannisters');
INSERT INTO public.user_groups VALUES ('23020673-3025-4f7c-a965-d3cfa219d0a1', 'Targaryens of king''s landing', 'Targaryens');
INSERT INTO public.user_groups VALUES ('622fb658-7f76-4353-bfbb-22e1134825ff', 'Tyrell', 'Tyrell');
INSERT INTO public.user_groups VALUES ('0eaf7a9f-67ff-42d9-b941-e80e46f88fd2', 'Faceless Man at the House of Black and White', 'Faceless Man');


--
-- TOC entry 3421 (class 0 OID 16422)
-- Dependencies: 221
-- Data for Name: user_group_role_associations; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3422 (class 0 OID 16425)
-- Dependencies: 222
-- Data for Name: user_group_users; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.user_group_users VALUES ('f931bfbd-0d30-42e5-b327-839d68cb1a2a', '4d516d92-6f8f-451c-bb9d-35946b6c44e9');
INSERT INTO public.user_group_users VALUES ('f4d24577-83d7-4ff1-8dad-56c3d0808697', '268046a8-fed3-496f-adf8-cde57677de04');
INSERT INTO public.user_group_users VALUES ('f4d24577-83d7-4ff1-8dad-56c3d0808697', '63d0ee98-5a5d-486f-8cf4-9cee13d67cb9');
INSERT INTO public.user_group_users VALUES ('23020673-3025-4f7c-a965-d3cfa219d0a1', '4d516d92-6f8f-451c-bb9d-35946b6c44e9');


--
-- TOC entry 3424 (class 0 OID 16435)
-- Dependencies: 224
-- Data for Name: user_role_group_associations; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3425 (class 0 OID 16438)
-- Dependencies: 225
-- Data for Name: user_roles; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.user_roles VALUES ('4d516d92-6f8f-451c-bb9d-35946b6c44e9', 'cb49f08d-04c7-4f01-88c7-3efaec9519f2');
INSERT INTO public.user_roles VALUES ('63d0ee98-5a5d-486f-8cf4-9cee13d67cb9', '6129ae17-1d93-4e76-ab57-91304437ab55');


-- Completed on 2023-11-06 08:42:12 UTC

--
-- PostgreSQL database dump complete
--

