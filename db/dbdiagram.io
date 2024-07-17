Table "databases" {
  "id" bigint [pk, increment]
  "name" varchar(255)
  "code" varchar(255)
  "url" varchar(255)
  "port" int
  "login" varchar(255)
  "password" varchar(255)
  "is_prod" bool
  "db_tags" varchar(1000)
}

Table "launches" {
  "id" bigint [pk, increment]
  "scheduler_id" int
  "status" varchar(255)
  "start_date_time" timestamp
  "end_date_time" timestamp
}

Table "schedulers" {
  "id" bigint [pk, increment]
  "name" varchar(255)
  "schedule_to_start" timestamp
  "is_active" bool
  "description" varchar(1000)
}

Table "scenarios" {
  "id" bigint [pk, increment]
  "name" varchar(255)
  "code" varchar(255)
  "status" varchar(32)
  "db_tags" varchar(1000)
  "description" varchar(1000)
}

Table "scenarios_instances" {
  "id" bigint [pk, increment]
  "name" varchar(255)
  "code" varchar(255)
  "status" varchar(255)
  "database_id" bigint
  "launch_id" bigint
  "scenario_id" bigint
  "label" varchar(255)
  "start_date_time" timestamp
  "end_date_time" timestamp
  "init_context" varchar(4000)
}

Table "scenarios_settings" {
  "id" bigint [pk, increment]
  "database_id" bigint
  "scheduler_id" bigint
  "scenario_id" bigint
  "init_context" varchar(4000)
  "label" varchar(255)
}

Table "tasks" {
  "id" bigint [pk, increment]
  "task_id" int
  "name" varchar(255)
  "code" varchar(255)
  "scenario_id" bigint
  "action_type" varchar(32)
  "action_script" varchar(4000)
  "expected_result" varchar(1000)
  "flags" varchar(255)
  "status" varchar(255)
  "description" varchar(4000)
}

Table "tasks_instances" {
  "id" bigint [pk, increment]
  "task_id" int
  "name" varchar(255)
  "code" varchar(255)
  "scenario_instance_id" bigint
  "action_type" varchar(32)
  "action_script" varchar(4000)
  "expected_result" varchar(1000)
  "flags" varchar(255)
  "status" varchar(255)
  "description" varchar(4000)
  "task_instance_db_id" varchar(255)
  "start_date_time" timestamp
  "end_date_time" timestamp
  "incoming_context" varchar(1000)
  "context" varchar(1000)
  "info_msg" varchar(4000)
}

Table "ancestors_links" {
  "descendent_task_id" bigint
  "ancestor_task_id" bigint

  Indexes {
    (descendent_task_id, ancestor_task_id) [pk]
  }
}

Table "ancestors_links_instances" {
  "descendent_task_instances_id" bigint
  "ancestor_task_instances_id" bigint

  Indexes {
    (descendent_task_instances_id, ancestor_task_instances_id) [pk]
  }
}

Ref:"tasks"."id" < "ancestors_links"."descendent_task_id"

Ref:"tasks"."id" < "ancestors_links"."ancestor_task_id"

Ref:"tasks_instances"."id" < "ancestors_links_instances"."descendent_task_instances_id"

Ref:"tasks_instances"."id" < "ancestors_links_instances"."ancestor_task_instances_id"

Ref:"schedulers"."id" < "launches"."scheduler_id"

Ref:"databases"."id" < "scenarios_instances"."database_id"

Ref:"scenarios"."id" < "scenarios_instances"."scenario_id"

Ref:"launches"."id" < "scenarios_instances"."launch_id"

Ref:"databases"."id" < "scenarios_settings"."database_id"

Ref:"scenarios"."id" < "scenarios_settings"."scenario_id"

Ref:"schedulers"."id" < "scenarios_settings"."scheduler_id"

Ref:"scenarios"."id" < "tasks"."scenario_id"

Ref:"scenarios_instances"."id" < "tasks_instances"."scenario_instance_id"
