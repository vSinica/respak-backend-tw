insert into public.areas ( name, code, archive)
values  ('area1', 10, false),
        ('area2', 20, false),
        ('area3', 30, false),
        ('area4', 40, false),
        ('area5', 50, true);

insert into public.companys (name, company_type, inn, kpp, ogrn, reg_area_id, create_date, archive)
values  ('company1', 'FL', '654321', '321645', '546412', 5, '2024-05-14', false),
        ('company2', 'IP', '643321', '321645', '546412', 4, '2024-05-14', false),
        ('company3', 'YL', '654321', '321645', '546412', 3, '2024-05-14', false),
        ('company4', 'YL', '654321', '321645', '546412', 2, '2024-05-14', false),
        ('company5', 'FL', '654321', '321645', '546412', 1, '2024-05-14', true);

insert into public.company2area (area_id, company_id)
values  (1, 1),
        (1, 2),
        (1, 3),
        (1, 4),
        (5, 5),
        (4, 6),
        (2, 3),
        (3, 3);