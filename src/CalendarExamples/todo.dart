import 'package:flutter/material.dart';
import 'package:table_calendar/table_calendar.dart';
import '../utils.dart';

class TableToDoExample extends StatefulWidget {
  @override
  _TableToDoExampleState createState() => _TableToDoExampleState();
}

class _TableToDoExampleState extends State<TableToDoExample> {
  CalendarFormat _calendarFormat = CalendarFormat.month;
  DateTime _focusedDay = DateTime.now();
  DateTime? _selectedDay;

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('Pending Tasks'),  //need change
      ),
      body: Column(
        children: [
          TableCalendar(
            firstDay: kFirstDay,
            lastDay: kLastDay,
            focusedDay: _focusedDay,
            calendarFormat: _calendarFormat,
            selectedDayPredicate: (day) {
              return isSameDay(_selectedDay, day);
            },
            onDaySelected: (selectedDay, focusedDay) {
              setState(() {
                _selectedDay = selectedDay;
                _focusedDay = focusedDay;
              });
            },
            onFormatChanged: (format) {
              if (_calendarFormat != format) {
                setState(() {
                  _calendarFormat = format;
                });
              }
            },
            onPageChanged: (focusedDay) {
              _focusedDay = focusedDay;
            },
          ),
          const SizedBox(height: 8.0),
          Expanded(
            child: _buildTodoList(),
          ),
        ],
      ),
    );
  }

  Widget _buildTodoList() {
    final today = DateTime.utc(_focusedDay.year, _focusedDay.month, _focusedDay.day);
    final selectedDay = _selectedDay ?? today;

    // Display tasks for all dates from the selected day up to the deadline
    final selectedTasks = _todoList.entries
        .where((entry) {
      final deadline = entry.key;
      return (selectedDay.isBefore(deadline) || isSameDay(selectedDay, deadline)) &&
          (selectedDay.isAfter(today) || isSameDay(selectedDay, today));
    })
        .expand((entry) => entry.value)
        .toList();

    if (selectedTasks.isEmpty) {
      return Center(child: Text('No pending tasks for the selected day.'));
    }

    return ListView.builder(
      itemCount: selectedTasks.length,
      itemBuilder: (context, index) {
        final task = selectedTasks[index];
        return ListTile(
          title: Text(task.description),
          trailing: Checkbox(
            value: task.isComplete,
            onChanged: (value) {
              setState(() {
                task.isComplete = value ?? false;
              });
            },
          ),
        );
      },
    );
  }
}

class TodoEvent {
  final String description;
  bool isComplete;

  TodoEvent(this.description, {this.isComplete = false});
}

// Example of tasks with deadlines
final _todoList = {
  DateTime.utc(DateTime.now().year, 11, 15): [
    TodoEvent('Complete algorithm assignment'),
  ],
};

void markTaskAsComplete(DateTime date, int index) {
  _todoList[date]?[index].isComplete = true;
}
