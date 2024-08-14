Table "database_groups" {
  "id" bigint [pk, not null, increment]
  "name" varchar(255) [not null]
  "description" varchar(4000)
}

Table "database_groups_links" {
  "database_group_id" bigint [not null]
  "database_id" bigint [not null]

    Indexes {
    (database_group_id, database_id) [pk]
  }
}

Table "task_list_groups_links" {
  "database_group_id" bigint [not null]
  "task_list_id" bigint [not null]

    Indexes {
    (database_group_id, task_list_id) [pk]
  }
}

Table "databases" {
  "id" bigint [pk, not null, increment]
  "name" varchar(255)
  "url" varchar(255) [not null]
  "port" int [not null]
  "login" varchar(255) [not null]
  "password" varchar(255) [not null]
  "is_prod" bool
}

Table "launches" {
  "id" bigint [pk, not null, increment]
  "scheduler_id" int [not null]
  "status" varchar(255)
  "start_date_time" timestamp
  "end_date_time" timestamp
}

Table "schedulers" {
  "id" bigint [pk, not null, increment]
  "name" varchar(255)
  "schedule_to_start" timestamp
  "is_active" bool
  "description" varchar(4000)
}

Table "task_lists" {
  "id" bigint [pk, not null, increment]
  "name" varchar(255)
  "status" varchar(32) [not null]
  "description" varchar(4000)
}

Table "job_lists" {
  "id" bigint [pk, not null, increment]
  "name" varchar(255)
  "status" varchar(255) [not null]
  "database_id" bigint [not null]
  "launch_id" bigint [not null]
  "task_list_id" bigint [not null]
  "start_date_time" timestamp [not null]
  "end_date_time" timestamp
  "init_context" varchar(4000)
}

Table "scenarios" {
  "id" bigint [pk, not null, increment]
  "name" varchar(255)
  "scheduler_id" bigint [not null]
  "database_group_id" bigint [not null]
  "task_list_id" bigint [not null]
  "init_context" varchar(4000)
  "label" varchar(255)
}

Table "tasks" {
  "id" bigint [pk, not null, increment]
  "name" varchar(255)
  "task_list_id" bigint [not null]
  "action_type" varchar(32) [not null]
  "action_script" varchar(4000) [not null]
  "expected_result" varchar(1000)
  "flags" varchar(255)
  "status" varchar(255) [not null]
  "description" varchar(4000)
}

Table "jobs" {
  "id" bigint [pk, not null, increment]
  "task_id" int [not null]
  "name" varchar(255)
  "job_list_id" bigint [not null]
  "action_type" varchar(32) [not null]
  "action_script" varchar(4000) [not null]
  "expected_result" varchar(1000)
  "flags" varchar(255)
  "status" varchar(255) [not null]
  "description" varchar(4000)
  "db_proc_id" varchar(255)
  "start_date_time" timestamp [not null]
  "end_date_time" timestamp
  "incoming_context" varchar(1000)
  "context" varchar(1000)
  "info_msg" varchar(4000)
}

Table "ancestor_task_links" {
  "descendent_task_id" bigint [not null]
  "ancestor_task_id" bigint [not null]

  Indexes {
    (descendent_task_id, ancestor_task_id) [pk]
  }
}

Table "ancestor_job_links" {
  "descendent_job_id" bigint [not null]
  "ancestor_job_id" bigint [not null]

  Indexes {
    (descendent_job_id, ancestor_job_id) [pk]
  }
}

Ref:"tasks"."id" < "ancestor_task_links"."descendent_task_id"

Ref:"tasks"."id" < "ancestor_task_links"."ancestor_task_id"

Ref:"jobs"."id" < "ancestor_job_links"."descendent_job_id"

Ref:"jobs"."id" < "ancestor_job_links"."ancestor_job_id"

Ref:"schedulers"."id" < "launches"."scheduler_id"

Ref:"databases"."id" < "job_lists"."database_id"

Ref:"task_lists"."id" < "job_lists"."task_list_id"

Ref:"launches"."id" < "job_lists"."launch_id"

Ref:"database_groups"."id" < "scenarios"."database_group_id"

Ref:"task_lists"."id" < "scenarios"."task_list_id"

Ref:"schedulers"."id" < "scenarios"."scheduler_id"

Ref:"task_lists"."id" < "tasks"."task_list_id"

Ref:"job_lists"."id" < "jobs"."job_list_id"

Ref:"databases"."id" < "database_groups_links"."database_id"

Ref:"task_lists"."id" < "task_list_groups_links"."task_list_id"

Ref:"database_groups"."id" < "database_groups_links"."database_group_id"

Ref:"database_groups"."id" < "task_list_groups_links"."database_group_id"
