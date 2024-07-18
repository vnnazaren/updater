-- https://dbdiagram.io/d/updater-65e07ba0cd45b569fb35e2d1

drop table if exists databases, launches,schedulers,task_lists,job_lists,
    scenarios,tasks, jobs, ancestor_task_links, ancestor_job_links cascade;

create table if not exists databases
(
    id       bigint generated by default as identity primary key not null,
    name     varchar(255),
    url      varchar(255)                                        not null,
    port     int                                                 not null,
    login    varchar(255)                                        not null,
    password varchar(255)                                        not null,
    is_prod  bool
);

create table if not exists launches
(
    id              bigint generated by default as identity primary key not null,
    scheduler_id    int                                                 not null,
    status          varchar(255),
    start_date_time timestamp,
    end_date_time   timestamp
);

create table if not exists schedulers
(
    id                bigint generated by default as identity primary key not null,
    name              varchar(255),
    schedule_to_start timestamp,
    is_active         bool,
    description       varchar(1000)
);

create table if not exists task_lists
(
    id          bigint generated by default as identity primary key not null,
    name        varchar(255),
    status      varchar(32)                                         not null,
    db_tags     varchar(1000),
    description varchar(1000)
);

create table if not exists job_lists
(
    id              bigint generated by default as identity primary key not null,
    name            varchar(255),
    status          varchar(255)                                        not null,
    database_id     bigint                                              not null,
    launch_id       bigint                                              not null,
    task_list_id    bigint                                              not null,
    start_date_time timestamp                                           not null,
    end_date_time   timestamp,
    init_context    varchar(4000)
);

create table if not exists scenarios
(
    id           bigint generated by default as identity primary key not null,
    scheduler_id bigint                                              not null,
    database_id  bigint                                              not null,
    task_list_id bigint                                              not null,
    init_context varchar(4000),
    label        varchar(255)
);

create table if not exists tasks
(
    id              bigint generated by default as identity primary key not null,
    name            varchar(255),
    task_list_id    bigint                                              not null,
    action_type     varchar(32)                                         not null,
    action_script   varchar(4000)                                       not null,
    expected_result varchar(1000),
    flags           varchar(255),
    status          varchar(255)                                        not null,
    description     varchar(4000)
);

create table if not exists jobs
(
    id               bigint generated by default as identity primary key not null,
    task_id          int                                                 not null,
    name             varchar(255),
    job_list_id      bigint                                              not null,
    action_type      varchar(32)                                         not null,
    action_script    varchar(4000)                                       not null,
    expected_result  varchar(1000),
    flags            varchar(255),
    status           varchar(255)                                        not null,
    description      varchar(4000),
    db_proc_id       varchar(255),
    start_date_time  timestamp                                           not null,
    end_date_time    timestamp,
    incoming_context varchar(1000),
    context          varchar(1000),
    info_msg         varchar(4000)
);

create table if not exists ancestor_task_links
(
    descendent_task_id bigint not null,
    ancestor_task_id   bigint not null,
    primary key (descendent_task_id, ancestor_task_id)
);

create table if not exists ancestor_job_links
(
    descendent_job_id bigint not null,
    ancestor_job_id   bigint not null,
    primary key (descendent_job_id, ancestor_job_id)
);

alter table ancestor_task_links
    add foreign key (descendent_task_id) references tasks (id);

alter table ancestor_task_links
    add foreign key (ancestor_task_id) references tasks (id);

alter table ancestor_job_links
    add foreign key (descendent_job_id) references jobs (id);

alter table ancestor_job_links
    add foreign key (ancestor_job_id) references jobs (id);

alter table launches
    add foreign key (scheduler_id) references schedulers (id);

alter table job_lists
    add foreign key (database_id) references databases (id);

alter table job_lists
    add foreign key (task_list_id) references task_lists (id);

alter table job_lists
    add foreign key (launch_id) references launches (id);

alter table scenarios
    add foreign key (database_id) references databases (id);

alter table scenarios
    add foreign key (task_list_id) references task_lists (id);

alter table scenarios
    add foreign key (scheduler_id) references schedulers (id);

alter table tasks
    add foreign key (task_list_id) references task_lists (id);

alter table jobs
    add foreign key (job_list_id) references job_lists (id);