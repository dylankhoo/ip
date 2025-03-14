# GROOT User Guide

Groot is the individual project component for the NUS course CS2113

```
I am
  ____ ____   ___   ___ _____
 / ___|  _ \ / _ \ / _ \_   _|     /\
| |  _| |_) | | | | | | || |      /  \
| |_| |  _ <| |_| | |_| || |     /____\
 \____|_| \_\\___/ \___/ |_|       ||
```

Groot is a personal task tracker that helps you manage your tasks efficiently

## Table of Contents

- [Groot User Guide](#groot-user-guide)
  - [Task types](#task-types)
  - [Features](#features)
    - [`todo` - Add a TODO task](#todo---add-a-todo-task)
    - [`deadline` - Add a Deadline Task](#deadline---add-a-deadline-task)
    - [`event` - Add an Event Task](#event---add-an-event-task)
    - [`list` - Get your Task List](#list---list-out-current-tasks)
    - [`mark` - Tag task as done](#mark---tag-tasks-as-complete)
    - [`unmark` - Tag task as undone](#unmark---tag-task-as-incomplete)
    - [`delete` - Remove a Task](#delete---remove-a-task)
    - [`find` - Query for a task](#find---search-for-a-task)
    - [`bye` - Exit Groot](#bye---exit-groot)

## Task Types

Groot provides four types of tasks for creation:

1. `Todo`, for all your daily task needs
2. `Deadlines`, for tasks that need to be done fast
3. `Event`, to manage all sorts of appointments and meetings

## Features

Groot supports a number of features over CLI.

### `todo` - Add a TODO task

Create a `todo` task.

Usage:  
`todo (description)`

Expected outcome:

Groot will read and parse your `todo`, then echo it and add it to your task list.

```
I am Groot!
added: [T][ ] (description)
```

### `deadline` - Add a Deadline Task

Create a `deadline` task.  
Deadlines have a compulsory `by` parameter to indicate the deadline date.

Usage:

`deadline (description) /by (when)`

Expected outcome:

Groot will read and parse your `deadline`, then echo it and add it to your task list.

```
I am Groot!
added: [D][ ] (description) (by: (when))
```

### `event` - Add an Event Task

Create a `event` task.  
Events have a compulsory `from` and `to` parameters to indicate the start and end of event.

Usage:

`event (description) /from (start) /to (end)`

Expected outcome:

Groot will read and parse your `event`, then echo it and add it to your task list.

```
I am Groot!
 added: [E][ ] (description) (from: (start) to: (end))
```

### `list` - List out current tasks

Lists out all saved tasks. The list also indicates which task is completed.

Usage:

`list`

Expected outcome:

Groot recites your task list in order of tasks added.

```
I am Groot!
    Here is what you have to do:
        1.[E][ ] dylan attend CS2113 lecture Friday 21 Feb 2025 (from: 4 to: 6pm)
        2.[T][ ] homework
        3.[D][ ] cry about homework (by: April)
    You have 3 tasks
```

### `mark` - Tag tasks as complete

Marks a task in the tasklist as complete, indicated by a `[X]`.

Usage:  
`mark (task number)`

Expected outcome:

Groot tags the corresponding task. If the task number is invalid, Groot will remind you to use a valid task number.

```
I am Groot!
    I've marked this task as done.
        [E][X] dylan attend CS2113 lecture Friday 21 Feb 2025 (from: 4 to: 6pm)
```

### `unmark` - Tag task as incomplete

Marks a task as incomplete, indicated by a `[ ]

Usage:  
`unmark (task number)`

Expected outcome:

Groot untags the corresponding task. If the task number is invalid, Groot will remind you to use a valid task number.

```
I am Groot!
    I've marked this task as not done.
        [E][ ] dylan attend CS2113 lecture Friday 21 Feb 2025 (from: 4 to: 6pm)
```

### `delete` - Remove a Task

Removes a task from the task list.

Usage:  
`delete (task number)`

Expected outcome:

Groot removes the corresponding task from the list and echos the description of the removed task.

```
I have deleted this task:
    [E][ ] dylan attend CS2113 lecture Friday 21 Feb 2025 (from: 4 to: 6pm)
You now have 8 tasks
```

### `find` - Search for a task

Groot searches for an existing task based on user key.

Usage:

`find (key)`

Expected outcome:

Groot echos out all existing tasks containing the search key.

```
I am Groot!
    Here are the matching tasks:
        1.[T][ ] homework
        3.[D][ ] math homework (by: 6 jan)
```

### `bye` - Exit Groot

Exits Groot, saying goodbye to your helpful companion.

Usage:

`bye`

Expected outcome:

Groot says goodbye to you.

```
I am Groot!
Goodbye! See you again soon.
```
