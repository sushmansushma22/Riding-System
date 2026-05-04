class Subject {
    String name;
    String teacher;
    String room;

    Subject(String name, String teacher, String room) {
        this.name = name;
        this.teacher = teacher;
        this.room = room;
    }
}

public class main2 {

    static int N = 3;

    static Subject[] subjects = {
            new Subject("Math", "A", "R1"),
            new Subject("Science", "B", "R2"),
            new Subject("English", "A", "R3")
    };

    static String[] slots = {"Slot1", "Slot2", "Slot3"};

    static Subject[] result = new Subject[N];
    static boolean[] used = new boolean[N]; // prevent duplicates

    public static void main(String[] args) {

        if (generateTimetable(0)) {
            System.out.println("Timetable Generated:");
            display();
        } else {
            System.out.println("No valid timetable found");
        }
    }


    static boolean generateTimetable(int index) {
        if (index == N) {
            return true;
        }

        for (int i = 0; i < N; i++) {

            if (!used[i] && isSafe(subjects[i], index)) {

                result[index] = subjects[i];
                used[i] = true;

                if (generateTimetable(index + 1)) {
                    return true;
                }


                result[index] = null;
                used[i] = false;
            }
        }
        return false;
    }


    static boolean isSafe(Subject sub, int slot) {
        for (int i = 0; i < slot; i++) {

            if (result[i] != null && result[i].room.equals(sub.room)) {
                return false;
            }
        }
        return true;
    }


    static void display() {
        for (int i = 0; i < N; i++) {
            System.out.println(slots[i] + " -> " +
                    result[i].name +
                    " (Teacher: " + result[i].teacher +
                    ", Room: " + result[i].room + ")");
        }
    }
}
