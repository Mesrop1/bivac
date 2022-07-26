PGDMP     5    3                z            training    13.1    13.1 "    �           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false            �           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false            �           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false            �           1262    426056    training    DATABASE     e   CREATE DATABASE training WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE = 'Russian_Russia.1251';
    DROP DATABASE training;
                postgres    false            �            1259    435244    groups    TABLE     p   CREATE TABLE public.groups (
    id bigint NOT NULL,
    capacity integer NOT NULL,
    instructor_id bigint
);
    DROP TABLE public.groups;
       public         heap    postgres    false            �            1259    435242    groups_id_seq    SEQUENCE     v   CREATE SEQUENCE public.groups_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 $   DROP SEQUENCE public.groups_id_seq;
       public          postgres    false    201            �           0    0    groups_id_seq    SEQUENCE OWNED BY     ?   ALTER SEQUENCE public.groups_id_seq OWNED BY public.groups.id;
          public          postgres    false    200            �            1259    435252 
   instructor    TABLE     �   CREATE TABLE public.instructor (
    id bigint NOT NULL,
    email character varying(255),
    instructor_name character varying(255)
);
    DROP TABLE public.instructor;
       public         heap    postgres    false            �            1259    435250    instructor_id_seq    SEQUENCE     z   CREATE SEQUENCE public.instructor_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 (   DROP SEQUENCE public.instructor_id_seq;
       public          postgres    false    203            �           0    0    instructor_id_seq    SEQUENCE OWNED BY     G   ALTER SEQUENCE public.instructor_id_seq OWNED BY public.instructor.id;
          public          postgres    false    202            �            1259    435268    student    TABLE     �   CREATE TABLE public.student (
    id bigint NOT NULL,
    age integer,
    first_name character varying(255),
    last_name character varying(255)
);
    DROP TABLE public.student;
       public         heap    postgres    false            �            1259    435266    student_id_seq    SEQUENCE     w   CREATE SEQUENCE public.student_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 %   DROP SEQUENCE public.student_id_seq;
       public          postgres    false    205            �           0    0    student_id_seq    SEQUENCE OWNED BY     A   ALTER SEQUENCE public.student_id_seq OWNED BY public.student.id;
          public          postgres    false    204            �            1259    435277    students_group    TABLE     e   CREATE TABLE public.students_group (
    student_id bigint NOT NULL,
    group_id bigint NOT NULL
);
 "   DROP TABLE public.students_group;
       public         heap    postgres    false            �            1259    435280    students_instructors    TABLE     p   CREATE TABLE public.students_instructors (
    student_id bigint NOT NULL,
    instructor_id bigint NOT NULL
);
 (   DROP TABLE public.students_instructors;
       public         heap    postgres    false            8           2604    435247 	   groups id    DEFAULT     f   ALTER TABLE ONLY public.groups ALTER COLUMN id SET DEFAULT nextval('public.groups_id_seq'::regclass);
 8   ALTER TABLE public.groups ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    201    200    201            9           2604    435255    instructor id    DEFAULT     n   ALTER TABLE ONLY public.instructor ALTER COLUMN id SET DEFAULT nextval('public.instructor_id_seq'::regclass);
 <   ALTER TABLE public.instructor ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    203    202    203            :           2604    435271 
   student id    DEFAULT     h   ALTER TABLE ONLY public.student ALTER COLUMN id SET DEFAULT nextval('public.student_id_seq'::regclass);
 9   ALTER TABLE public.student ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    204    205    205            �          0    435244    groups 
   TABLE DATA           =   COPY public.groups (id, capacity, instructor_id) FROM stdin;
    public          postgres    false    201   /%       �          0    435252 
   instructor 
   TABLE DATA           @   COPY public.instructor (id, email, instructor_name) FROM stdin;
    public          postgres    false    203   i%       �          0    435268    student 
   TABLE DATA           A   COPY public.student (id, age, first_name, last_name) FROM stdin;
    public          postgres    false    205   �%       �          0    435277    students_group 
   TABLE DATA           >   COPY public.students_group (student_id, group_id) FROM stdin;
    public          postgres    false    206   l&       �          0    435280    students_instructors 
   TABLE DATA           I   COPY public.students_instructors (student_id, instructor_id) FROM stdin;
    public          postgres    false    207   �&       �           0    0    groups_id_seq    SEQUENCE SET     ;   SELECT pg_catalog.setval('public.groups_id_seq', 2, true);
          public          postgres    false    200            �           0    0    instructor_id_seq    SEQUENCE SET     @   SELECT pg_catalog.setval('public.instructor_id_seq', 1, false);
          public          postgres    false    202            �           0    0    student_id_seq    SEQUENCE SET     <   SELECT pg_catalog.setval('public.student_id_seq', 1, true);
          public          postgres    false    204            <           2606    435249    groups groups_pkey 
   CONSTRAINT     P   ALTER TABLE ONLY public.groups
    ADD CONSTRAINT groups_pkey PRIMARY KEY (id);
 <   ALTER TABLE ONLY public.groups DROP CONSTRAINT groups_pkey;
       public            postgres    false    201            >           2606    435260    instructor instructor_pkey 
   CONSTRAINT     X   ALTER TABLE ONLY public.instructor
    ADD CONSTRAINT instructor_pkey PRIMARY KEY (id);
 D   ALTER TABLE ONLY public.instructor DROP CONSTRAINT instructor_pkey;
       public            postgres    false    203            @           2606    435276    student student_pkey 
   CONSTRAINT     R   ALTER TABLE ONLY public.student
    ADD CONSTRAINT student_pkey PRIMARY KEY (id);
 >   ALTER TABLE ONLY public.student DROP CONSTRAINT student_pkey;
       public            postgres    false    205            D           2606    435303 0   students_instructors fk3q3uw012rvx70qfn2hsytfay8    FK CONSTRAINT     �   ALTER TABLE ONLY public.students_instructors
    ADD CONSTRAINT fk3q3uw012rvx70qfn2hsytfay8 FOREIGN KEY (instructor_id) REFERENCES public.instructor(id);
 Z   ALTER TABLE ONLY public.students_instructors DROP CONSTRAINT fk3q3uw012rvx70qfn2hsytfay8;
       public          postgres    false    2878    203    207            E           2606    435308 /   students_instructors fk6vhqwyduuvl1miui61o75529    FK CONSTRAINT     �   ALTER TABLE ONLY public.students_instructors
    ADD CONSTRAINT fk6vhqwyduuvl1miui61o75529 FOREIGN KEY (student_id) REFERENCES public.student(id);
 Y   ALTER TABLE ONLY public.students_instructors DROP CONSTRAINT fk6vhqwyduuvl1miui61o75529;
       public          postgres    false    207    2880    205            A           2606    435313 "   groups fk95sb5lb0gcifw3ndouaow97ti    FK CONSTRAINT     �   ALTER TABLE ONLY public.groups
    ADD CONSTRAINT fk95sb5lb0gcifw3ndouaow97ti FOREIGN KEY (instructor_id) REFERENCES public.instructor(id);
 L   ALTER TABLE ONLY public.groups DROP CONSTRAINT fk95sb5lb0gcifw3ndouaow97ti;
       public          postgres    false    201    2878    203            B           2606    435293 *   students_group fkfk6s5ueceucgnce32i1982cq8    FK CONSTRAINT     �   ALTER TABLE ONLY public.students_group
    ADD CONSTRAINT fkfk6s5ueceucgnce32i1982cq8 FOREIGN KEY (group_id) REFERENCES public.groups(id);
 T   ALTER TABLE ONLY public.students_group DROP CONSTRAINT fkfk6s5ueceucgnce32i1982cq8;
       public          postgres    false    201    2876    206            C           2606    435298 *   students_group fkhk62ybxibo7653r7hgu6b8ow8    FK CONSTRAINT     �   ALTER TABLE ONLY public.students_group
    ADD CONSTRAINT fkhk62ybxibo7653r7hgu6b8ow8 FOREIGN KEY (student_id) REFERENCES public.student(id);
 T   ALTER TABLE ONLY public.students_group DROP CONSTRAINT fkhk62ybxibo7653r7hgu6b8ow8;
       public          postgres    false    2880    205    206            �   *   x�3�����2�4Q����2�4 Q&���F\1z\\\ ��P      �   q   x�M���@�}C�;Cke��s9� 	�w	�r&�9�wl'[8h?Z��y�T��YT��P�j��}��qB)����Gq���u$�d�u��$p��?k��{��K4)      �   r   x�M˱�0����1����1a�0�\�E�%ObR����~�����pd6N:gF)�Jԡ3�p��[vp.j͋4��SJ�j����Ж\�9�����k����~��/jh%H      �      x�3�4����� x!      �      x������ � �     