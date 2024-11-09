import 'dart:collection';
import 'package:table_calendar/table_calendar.dart';

/// Example event class.
class Event {
  final String title;

  const Event(this.title);

  @override
  String toString() => title;
}

/// These are the example events.
///
/// Using a [LinkedHashMap] is highly recommended if you decide to use a map.
final kEvents = LinkedHashMap<DateTime, List<Event>>(
  equals: isSameDay,  //Specifies that two keys (dates) are considered equal if they fall on the same day
  hashCode: getHashCode,
)..addAll(_kEventSource);  //cascade operator to add the _kEventSource map to the kEvents map


//put the event data here (these are just examples)
final _kEventSource = Map.fromIterable(List.generate(50, (index) => index),  //Creates a list of 50 elements, each element being its index (from 0 to 49)
    key: (item) => DateTime.utc(kFirstDay.year, kFirstDay.month, item * 5),
    value: (item) => List.generate(
        item % 4 + 1, (index) => Event('Event $item | ${index + 1}'))) // putting Event no. and content

  ..addAll({
    kToday: [
      Event('Part time job'),
      Event('Dinner with O'),
    ],
    DateTime.utc(kToday.year, 11, 13): [
      Event('Algorithm team meeting'),
    ],
    DateTime.utc(kToday.year, 11, 15): [
      Event('network assignment due'),
    ],
  });

int getHashCode(DateTime key) {
  return key.day * 1000000 + key.month * 10000 + key.year;  // make unique values for different dates, which helps in differentiating between the days.
}

/// Returns a list of [DateTime] objects from [first] to [last], inclusive.
List<DateTime> daysInRange(DateTime first, DateTime last) {
  final dayCount = last.difference(first).inDays + 1;
  return List.generate(
    dayCount,
        (index) => DateTime.utc(first.year, first.month, first.day + index),
  );
}

final kToday = DateTime.now();  //Stores today’s date
final kFirstDay = DateTime(kToday.year, kToday.month - 3, kToday.day); //Sets the first day of the calendar range, which is n(now 3) months before today
final kLastDay = DateTime(kToday.year, kToday.month + 3, kToday.day);  //Sets the last day of the calendar range, which is n(now 3) months after today