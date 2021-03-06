PGDMP                 
        z            scuola    14.1    14.1                0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false                       0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false                       0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false                       1262    17622    scuola    DATABASE     b   CREATE DATABASE scuola WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE = 'Italian_Italy.1252';
    DROP DATABASE scuola;
                postgres    false            ?            1259    17797    corso    TABLE     X   CREATE TABLE public.corso (
    id integer NOT NULL,
    nome character varying(255)
);
    DROP TABLE public.corso;
       public         heap    postgres    false            ?            1259    17796    corso_id_seq    SEQUENCE     ?   CREATE SEQUENCE public.corso_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 #   DROP SEQUENCE public.corso_id_seq;
       public          postgres    false    211            	           0    0    corso_id_seq    SEQUENCE OWNED BY     =   ALTER SEQUENCE public.corso_id_seq OWNED BY public.corso.id;
          public          postgres    false    210            ?            1259    17803 	   frequenta    TABLE     j   CREATE TABLE public.frequenta (
    matricola_studente integer NOT NULL,
    id_corso integer NOT NULL
);
    DROP TABLE public.frequenta;
       public         heap    postgres    false            ?            1259    17818    sequence    TABLE     k   CREATE TABLE public.sequence (
    seq_name character varying(50) NOT NULL,
    seq_count numeric(38,0)
);
    DROP TABLE public.sequence;
       public         heap    postgres    false            ?            1259    17789    studente    TABLE     ?   CREATE TABLE public.studente (
    matricola integer NOT NULL,
    cognome character varying(255),
    nome character varying(255)
);
    DROP TABLE public.studente;
       public         heap    postgres    false            h           2604    17800    corso id    DEFAULT     d   ALTER TABLE ONLY public.corso ALTER COLUMN id SET DEFAULT nextval('public.corso_id_seq'::regclass);
 7   ALTER TABLE public.corso ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    210    211    211                       0    17797    corso 
   TABLE DATA           )   COPY public.corso (id, nome) FROM stdin;
    public          postgres    false    211   ?                 0    17803 	   frequenta 
   TABLE DATA           A   COPY public.frequenta (matricola_studente, id_corso) FROM stdin;
    public          postgres    false    212   ?                 0    17818    sequence 
   TABLE DATA           7   COPY public.sequence (seq_name, seq_count) FROM stdin;
    public          postgres    false    213   ?       ?          0    17789    studente 
   TABLE DATA           <   COPY public.studente (matricola, cognome, nome) FROM stdin;
    public          postgres    false    209          
           0    0    corso_id_seq    SEQUENCE SET     :   SELECT pg_catalog.setval('public.corso_id_seq', 8, true);
          public          postgres    false    210            l           2606    17802    corso corso_pkey 
   CONSTRAINT     N   ALTER TABLE ONLY public.corso
    ADD CONSTRAINT corso_pkey PRIMARY KEY (id);
 :   ALTER TABLE ONLY public.corso DROP CONSTRAINT corso_pkey;
       public            postgres    false    211            n           2606    17807    frequenta frequenta_pkey 
   CONSTRAINT     p   ALTER TABLE ONLY public.frequenta
    ADD CONSTRAINT frequenta_pkey PRIMARY KEY (matricola_studente, id_corso);
 B   ALTER TABLE ONLY public.frequenta DROP CONSTRAINT frequenta_pkey;
       public            postgres    false    212    212            p           2606    17822    sequence sequence_pkey 
   CONSTRAINT     Z   ALTER TABLE ONLY public.sequence
    ADD CONSTRAINT sequence_pkey PRIMARY KEY (seq_name);
 @   ALTER TABLE ONLY public.sequence DROP CONSTRAINT sequence_pkey;
       public            postgres    false    213            j           2606    17795    studente studente_pkey 
   CONSTRAINT     [   ALTER TABLE ONLY public.studente
    ADD CONSTRAINT studente_pkey PRIMARY KEY (matricola);
 @   ALTER TABLE ONLY public.studente DROP CONSTRAINT studente_pkey;
       public            postgres    false    209            r           2606    17813    frequenta fk_frequenta_id_corso    FK CONSTRAINT        ALTER TABLE ONLY public.frequenta
    ADD CONSTRAINT fk_frequenta_id_corso FOREIGN KEY (id_corso) REFERENCES public.corso(id);
 I   ALTER TABLE ONLY public.frequenta DROP CONSTRAINT fk_frequenta_id_corso;
       public          postgres    false    3180    212    211            q           2606    17808 )   frequenta fk_frequenta_matricola_studente    FK CONSTRAINT     ?   ALTER TABLE ONLY public.frequenta
    ADD CONSTRAINT fk_frequenta_matricola_studente FOREIGN KEY (matricola_studente) REFERENCES public.studente(matricola);
 S   ALTER TABLE ONLY public.frequenta DROP CONSTRAINT fk_frequenta_matricola_studente;
       public          postgres    false    3178    212    209                ,   x?3???K?/?M,?LN?2?L.M?,??2?,?O????qqq ?            x?345?4?245???&`Ҍ+F??? B*            x?v?ww??425?????? &u      ?   C   x??K
?0?u?a?z7???M??v=?U?iL?????"/?`6???|??:??íɘ?? ?`?     